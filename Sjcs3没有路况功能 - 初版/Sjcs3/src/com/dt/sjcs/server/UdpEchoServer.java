package com.dt.sjcs.server;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.xml.bind.DatatypeConverter;

import com.dt.common.dao.ConnDataBase;
import com.dt.common.utils.ComUtils;

public class UdpEchoServer {
    
	//创建socket对象（数据报套接字）
    private DatagramSocket socket = null;

    //服务器启动时需绑定一个端口号，收到数据时用于发送响应到某一个进程
    //端口号实际上是两个字节的无符号整型数据，0-65535
    public UdpEchoServer(int port) throws SocketException {
    	socket = new DatagramSocket(port);
    }

    //启动服务器
    public void start() throws Exception {
        System.out.println("启动程序！");
        //服务器一般是持续运行(24h*7)
        while(true){
        	//1.读取请求，服务器一般不知道客户端啥时候发来请求
            //receive()参数DatagramPacket是一个输出型参数，socket中读到的数据会设置到这个参数的对象中
            //DatagramPacket在构造的时候需要一个缓冲区（实际上是一段内存空间, 通常使用byte[]）
        	DatagramPacket requestPacket = new DatagramPacket(new byte[1024], 1024);
            socket.receive(requestPacket); //收到请求之前，receive()操作在阻塞等待！

            //把requestPacket中的内容取出来,作为一个字符串
            String request = DatatypeConverter.printHexBinary(requestPacket.getData()).substring(0,requestPacket.getLength()*2);
            System.out.println("request="+request);
            
            //2.根据请求计算响应
            String response = process(request);
            System.out.println("response="+response);
            //3.构造responsePacket响应
            //此处设置的参数长度必须是字节的长度个数！response.getBytes().length
            //如果直接取response.length,则是字符串的长度，也就是字符串的个数
            //当前的responsePacket在构造时，需要指定这个包要发给谁；发送给的目标即发来请求的一方
            byte buf[] = DatatypeConverter.parseHexBinary(response);
            DatagramPacket responsePacket = new DatagramPacket(buf,buf.length,requestPacket.getSocketAddress());
            socket.send(responsePacket); 
        }
    }

    //此处的process()方法负责的功能是根据请求来计算响应
    private static String process(String request) throws Exception {
    	String str = request.replace(" ", "");
    	String response = "";
        if(str.substring(10, 12).equals("12")) {//定时数据
           	//SOH	发送ID	目标ID	命令字	数据长度	STX	数据内容	ETX	CRC	EOT
           	//01	0030	0010	18	0001	02	00	03	E0AE	04
        	String datastr = "01"+str.substring(6, 10)+str.substring(2, 6)+"180001020003";
        	response = Crcjs(datastr).replace(" ", "")+"04";
           	Sjbc(request);
        }else if(str.toUpperCase().startsWith("7B")) {//心跳
           	//设备心跳包：7B 01 00 16 31 33 39 31 32 33 34 35 36 37 38 01 00 00 00 00 00 7B             
			//服务器回复心跳包：7B 81 00 10 31 33 39 31 32 33 34 35 36 37 38 7B
        	response = "7B810010"+str.substring(8, 30)+"7B";
        }else if(str.substring(10, 12).equals("03")) {//对时
           	//SOH	目标ID	设备ID	命令字	数据长度	STX	数据内容	ETX	CRC	 EOT
           	//01	0010	0001	03	    0001	02	00	    03	BBBB 04
           	//SOH	目标ID	设备ID	命令字	数据长度	STX	时间BCD	       ETX	CRC	   EOT
           	//01	0001	0010	03	    0006	02	161030094450	03	BBBB	04
           	SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmmss");
           	String datastr = "01"+str.substring(6, 10)+str.substring(2, 6)+"03000602"+sdf.format(new Date())+"03";
           	response = Crcjs(datastr).replace(" ", "")+"04";
        }else if(str.substring(10, 12).equals("40")) {//状态上报
        	int sjcd = 14;//数据长度
        	int tdgs = 2;//通道个数
        	SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmmss");
        	String datatime = "020506"+sdf.format(new Date());
        	String dataml = "01";
        	String sbid = new BigInteger(str.substring(6,10),16).toString(10);//发送ID
        	ConnDataBase bean = new ConnDataBase();
        	List list = bean.executeQuery("select * from sj_mlpz where sbid='"+sbid+"' order by xtsj desc");
        	String ym = "",dk = "";
        	if(list.size()>0) {
        		Map map = (Map)list.get(0);
        		dataml = map.get("value").toString();
        		ym = map.get("ym").toString();
        		dk = map.get("dk").toString();
        	}
        	if(dataml.equals("02")) {
        		if(!dk.equals("")) {
        			sjcd = sjcd + 7;
        			tdgs++;
        			String dkHexg = Integer.toHexString(Integer.parseInt(dk));//端口16进制高字节在前
    				while(dkHexg.length()<8) {
    					dkHexg = "0"+dkHexg;
    				}
    				String dkHexd = dkHexg.substring(6, 8)+dkHexg.substring(4, 6)+dkHexg.substring(2, 4)+dkHexg.substring(0, 2);
        			dataml = dataml +"0E0304"+dkHexd;
        			if(!ym.equals("")) {
        				String ymHex = convertStringToHex(ym);
        				sjcd = sjcd + 3 + ymHex.length()/2;
        				tdgs++;
        				String ymcdHex = Integer.toHexString(ymHex.length()/2);
        				if(ymcdHex.length()==1) {
        					ymcdHex = "0"+ymcdHex;
        				}
        				dataml = dataml +"0D05"+ymcdHex+ymHex;
        			}
        		}
        	}
        	String sjcdHex = Integer.toHexString(sjcd);
        	while(sjcdHex.length()<4) {
        		sjcdHex = "0"+sjcdHex;
			}
        	String tdgsHex = Integer.toHexString(tdgs);
        	while(tdgsHex.length()<2) {
        		tdgsHex = "0"+tdgsHex;
			}
        	String datastr = "010009"+str.substring(6, 10)+"40"+sjcdHex+"02"+tdgsHex+datatime+"060101"+dataml+"03";
        	response = Crcjs(datastr).replace(" ", "")+"04";
        	Ztbc(request);
        }
        return response;
    }
    
    public static String Crcjs(String datastr) {//CRC算法
    	String dataarr[] = new String[datastr.length()/2];
        int count = 0;
        for(int i=0;i<datastr.length()-1;i=i+2) {
        	dataarr[count] = datastr.substring(i, i+2);
          	count++;
        }
        byte[] dd = Crc16Util.getData(dataarr);
        String str = Crc16Util.byteTo16String(dd).toUpperCase();
        return str;
    }
    
    public static void Sjbc(String str) throws ParseException {
		if(str.length()==232) {//长度为232证明接收数据为定时数据，需要解析
			
			String sbid = new BigInteger(str.substring(6,10),16).toString(10);//发送ID
			
			String date = "20" + str.substring(26, 28) + "-" + str.substring(28, 30) + "-" + str.substring(30, 32) + " "
				    + str.substring(32, 34) + ":" + str.substring(34, 36) + ":" + str.substring(36, 38);//日期数据
			
			String dy = ""+Double.parseDouble(new BigInteger(str.substring(44,46),16).toString(10))/10;//电压
			
			String dqwdz = ""+Double.parseDouble(String.valueOf(hexadecimal16Conversion(str.substring(60,64))))/10;//大气温度值
			
			String qwxszdz = ""+Double.parseDouble(String.valueOf(hexadecimal16Conversion(str.substring(70,74))))/10;//气温小时最大值
			
			String qwxszxz = ""+Double.parseDouble(String.valueOf(hexadecimal16Conversion(str.substring(80,84))))/10;//气温小时最小值
			
			String dqsdz = ""+Double.parseDouble(new BigInteger(str.substring(90,94),16).toString(10))/10;//大气湿度值
			
			String sdxszdz = ""+Double.parseDouble(new BigInteger(str.substring(100,104),16).toString(10))/10;//湿度小时最大值
			
			String sdxszxz = ""+Double.parseDouble(new BigInteger(str.substring(110,114),16).toString(10))/10;//湿度小时最小值
			
			String dqylz = ""+Double.parseDouble(new BigInteger(str.substring(120,124),16).toString(10))/10;//大气压力值
			
			String ylxszdz = ""+Double.parseDouble(new BigInteger(str.substring(130,134),16).toString(10))/10;//压力小时最大值
			
			String ylxszxz = ""+Double.parseDouble(new BigInteger(str.substring(140,144),16).toString(10))/10;//压力小时最小值
			
			String fsssz = ""+Double.parseDouble(new BigInteger(str.substring(150,154),16).toString(10))/10;//风速瞬时值
			
			String fspjz = ""+Double.parseDouble(new BigInteger(str.substring(160,164),16).toString(10))/10;//风速平均值
			
			String fszdz = ""+Double.parseDouble(new BigInteger(str.substring(170,174),16).toString(10))/10;//风速最大值
			
			String fszxz = ""+Double.parseDouble(new BigInteger(str.substring(180,184),16).toString(10))/10;//风速最小值
			
			String fxssz = new BigInteger(str.substring(190,194),16).toString(10);//风向瞬时值
			
            String fxpjz = new BigInteger(str.substring(200,204),16).toString(10);//风向平均值
            
            String dqjyz = ""+Double.parseDouble(new BigInteger(str.substring(210,214),16).toString(10))/10;//当前降雨值
            
            String zfsz = new BigInteger(str.substring(220,224),16).toString(10);//总辐射值
			
			String id = ComUtils.getUniqueString();
	        ConnDataBase bean = new ConnDataBase();
			try {
				String sql = "insert into sj_sjpz(id,sbid,date,dy,dqwdz,qwxszdz,qwxszxz,dqsdz,sdxszdz,sdxszxz,"
						+ "dqylz,ylxszdz,ylxszxz,fsssz,fspjz,fszdz,fszxz,fxssz,fxpjz,dqjyz,zfsz,xtsj)values("
						+ "'"+id+"','"+sbid+"',STR_TO_DATE('"+date+"','%Y-%m-%d %T'),'"+dy+"','"+dqwdz+"','"+qwxszdz+"','"+qwxszxz+"','"+dqsdz+"','"+sdxszdz+"','"+sdxszxz+"',"
						+ "'"+dqylz+"','"+ylxszdz+"','"+ylxszxz+"','"+fsssz+"','"+fspjz+"','"+fszdz+"','"+fszxz+"','"+fxssz+"','"+fxpjz+"','"+dqjyz+"','"+zfsz+"',NOW())";
				bean.executeUpdate(sql);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
    
    public static void Ztbc(String str) throws ParseException {
		String sbid = new BigInteger(str.substring(6,10),16).toString(10);//发送ID
    	int tdgs = new BigInteger(str.substring(18,20),16).intValue();//通道个数
    	String snh="",rjbbh="",xtsj="",zdsc="",dqsc="",sbsj="",dy="",kzt="",mnzt="",szzt="",sbwd="",sim="";
    	int count = 20;
    	for(int i=0;i<tdgs;i++) {
    		int sum = new BigInteger(str.substring(count+4,count+6),16).intValue();
    		if(str.substring(count,count+2).equals("00")) {//设备SN号
    			if(str.substring(count+6, count+6+sum*2).startsWith("FFFFFFFFFF")) {
    				snh="FF";
    			}else {
    				snh = convertHexToString(str.substring(count+6, count+6+sum*2));
    			}
    		}
    		if(str.substring(count,count+2).equals("01")) {//软件版本号
    			rjbbh = convertHexToString(str.substring(count+6, count+6+sum*2));
    		}
    		if(str.substring(count,count+2).equals("02")) {//系统时间
    			xtsj = "20" + str.substring(count+6, count+8) + "-" + str.substring(count+8, count+10) + "-" + str.substring(count+10, count+12) + " "
					    + str.substring(count+12, count+14) + ":" + str.substring(count+14, count+16) + ":" + str.substring(count+16, count+18);
    		}
    		if(str.substring(count,count+2).equals("03")) {//站点运行总时长
    			String data = str.substring(count+12, count+14)+str.substring(count+10, count+12)+str.substring(count+8, count+10)+str.substring(count+6, count+8);
    			BigDecimal b = new BigDecimal(Double.parseDouble(Long.valueOf(data,16).toString())/1440);
    	    	zdsc = b.setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue()+"";
    		}
			if(str.substring(count,count+2).equals("04")) {//当前运行总时长
				String data = str.substring(count+12, count+14)+str.substring(count+10, count+12)+str.substring(count+8, count+10)+str.substring(count+6, count+8);
				BigDecimal b = new BigDecimal(Double.parseDouble(Long.valueOf(data,16).toString())/1440);
				dqsc = b.setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue()+"";
			}
			if(str.substring(count,count+2).equals("05")) {//最近上报时间
				sbsj = "20" + str.substring(count+6, count+8) + "-" + str.substring(count+8, count+10) + "-" + str.substring(count+10, count+12) + " "
					    + str.substring(count+12, count+14) + ":" + str.substring(count+14, count+16) + ":" + str.substring(count+16, count+18);
			}
			if(str.substring(count,count+2).equals("07")) {//系统电压
				dy = ""+Double.parseDouble(new BigInteger(str.substring(count+6, count+8),16).toString(10))/10;
			}
			if(str.substring(count,count+2).equals("08")) {//SD卡状态
				if(str.substring(count+6, count+8).equals("00")) kzt = "正常";
				if(str.substring(count+6, count+8).equals("01")) kzt = "无卡";
				if(str.substring(count+6, count+8).equals("02")) kzt = "初始化失败";
				if(str.substring(count+6, count+8).equals("03")) kzt = "写失败";
			}
			if(str.substring(count,count+2).equals("09")) {//模拟通道状态
				for(int j=0;j<sum;j++) {
					if(str.substring(count+6+2*j, count+8+2*j).equals("00")) mnzt += j+1+"正常；";
					if(str.substring(count+6+2*j, count+8+2*j).equals("01")) mnzt += j+1+"极值状态；";
					if(str.substring(count+6+2*j, count+8+2*j).equals("02")) mnzt += j+1+"异常状态；";
					if(str.substring(count+6+2*j, count+8+2*j).equals("FF")) mnzt += j+1+"通道无启用；";
				}
			}
			if(str.substring(count,count+2).equals("0A")) {//数字通道状态
				for(int j=0;j<sum;j++) {
					if(str.substring(count+6+2*j, count+8+2*j).equals("00")) szzt += j+1+"正常；";
					if(str.substring(count+6+2*j, count+8+2*j).equals("01")) szzt += j+1+"极值状态；";
					if(str.substring(count+6+2*j, count+8+2*j).equals("02")) szzt += j+1+"异常状态；";
					if(str.substring(count+6+2*j, count+8+2*j).equals("03")) szzt += j+1+"通信故障；";
					if(str.substring(count+6+2*j, count+8+2*j).equals("FF")) szzt += j+1+"通道无启用；";
				}
			}
			if(str.substring(count,count+2).equals("0B")) {//设备温度状态
				String wd = str.substring(count+12, count+14)+str.substring(count+10, count+12)+str.substring(count+8, count+10)+str.substring(count+6, count+8);
				sbwd = ""+Float.intBitsToFloat(new BigInteger(wd, 16).intValue());
			}
			if(str.substring(count,count+2).equals("0C")) {//设备SIM号
				if(str.substring(count+6, count+6+sum*2).startsWith("FFFFFFFFFF")) {
					sim="FF";
    			}else {
    				sim = convertHexToString(str.substring(count+6, count+6+sum*2));
    			}
			}
    		count = count + 6 + 2*sum;
    	}
		String id = ComUtils.getUniqueString();
        ConnDataBase bean = new ConnDataBase();
		try {
			String sql = "insert into sj_sbzt(id,sbid,snh,rjbbh,xtsj,zdsc,dqsc,sbsj,"
					+ "dy,kzt,mnzt,szzt,sbwd,sim,time)values("
					+ "'"+id+"','"+sbid+"','"+snh+"','"+rjbbh+"','"+xtsj+"','"+zdsc+"','"+dqsc+"','"+sbsj+"',"
					+ "'"+dy+"','"+kzt+"','"+mnzt+"','"+szzt+"','"+sbwd+"','"+sim+"',NOW())";
			bean.executeUpdate(sql);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
    
    public static String convertStringToHex(String str){//ASCII码转换为16进制
    	char[] chars = str.toCharArray();
  	    StringBuffer hex = new StringBuffer();
  	    for(int i = 0; i < chars.length; i++){
  	    	hex.append(Integer.toHexString((int)chars[i]));
  	    }
  	    return hex.toString();
  	}

    public static String convertHexToString(String hex){//16进制转换为ASCII
  	  StringBuilder sb = new StringBuilder();
  	  StringBuilder temp = new StringBuilder();
  	  for( int i=0; i<hex.length()-1; i+=2 ){
  	      String output = hex.substring(i, (i + 2));
  	      int decimal = Integer.parseInt(output, 16);
  	      sb.append((char)decimal);
  	      temp.append(decimal);
  	  }
  	  return sb.toString();
    }
    
    public static int hexadecimal16Conversion(String hexadecimalStr) {
    	int getDataDecimal = 0;//转化得到的目标数据
    	//16进制代表数据 4位数字
    	if (hexadecimalStr.length() == 4) {
	    	int bit1Num = Integer.parseInt(hexadecimalStr.substring(0, 1), 16);//获取第一位。判断是正数还是负数
	    	if (bit1Num < 8) { //小于8是正数
	    		getDataDecimal = Integer.parseInt(hexadecimalStr, 16);
	    	} else { //负数
	    		hexadecimalStr = "FFFF" + hexadecimalStr; //先不全八位
	    		getDataDecimal = new BigInteger(hexadecimalStr, 16).intValue();
	    	}
	    	return getDataDecimal;
    	}
    	return 0;
    }
    
	public static String getFLDJ(String str) {
		double fl = Double.parseDouble(str);
		if(fl>=0.0&&fl<=0.2){
			return "0";
		}else if(fl>=0.3&&fl<=1.5){
			return "1";
		}else if(fl>=1.6&&fl<=3.3){
			return "2";
		}else if(fl>=3.4&&fl<=5.4){
			return "3";
		}else if(fl>=5.5&&fl<=7.9){
			return "4";
		}else if(fl>=8.0&&fl<=10.7){
			return "5";
		}else if(fl>=10.8&&fl<=13.8){
			return "6";
		}else if(fl>=13.9&&fl<=17.1){
			return "7";
		}else if(fl>=17.2&&fl<=20.7){
			return "8";
		}else if(fl>=20.8&&fl<=24.4){
			return "9";
		}else if(fl>=24.5&&fl<=28.4){
			return "10";
		}else if(fl>28.5&&fl<=32.6){
			return "11";
		}else if(fl>32.7&&fl<=36.9){
			return "12";
		}else{
			return "12";
		}
    }

    public static void main(String[] args) throws Exception {
    	System.out.println("打开一个1000端口服务");
        UdpEchoServer server = new UdpEchoServer(1000);
        server.start();
    }
    	
}
