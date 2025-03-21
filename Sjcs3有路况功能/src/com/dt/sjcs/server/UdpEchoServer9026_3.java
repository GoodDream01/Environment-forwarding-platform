package com.dt.sjcs.server;

import java.math.BigInteger;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.bind.DatatypeConverter;

import com.dt.common.dao.ConnDataBase;
import com.dt.common.utils.ComUtils;

public class UdpEchoServer9026_3 {

	//创建socket对象（数据报套接字）
	private DatagramSocket socket = null;

	//服务器启动时需绑定一个端口号，收到数据时用于发送响应到某一个进程
	//端口号实际上是两个字节的无符号整型数据，0-65535
	public UdpEchoServer9026_3(int port) throws SocketException {
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

	public static String process(String request) throws Exception {
		String str = request.replace(" ", "");
		String response = "";
		if (str.length() > 12) {
			if (str.substring(10, 12).equals("12")) {//定时数据
				//SOH	发送ID	目标ID	命令字	数据长度	STX	数据内容	ETX	CRC	EOT
				//01	0030	0010	18	0001	02	00	03	E0AE	04
				String datastr = "01" + str.substring(6, 10) + str.substring(2, 6) + "180001020003";
				response = Crcjs(datastr).replace(" ", "") + "04";
				Sjbc(request);
			} else if (str.toUpperCase().startsWith("7B")) {//心跳
				//设备心跳包：7B 01 00 16 31 33 39 31 32 33 34 35 36 37 38 01 00 00 00 00 00 7B
				//服务器回复心跳包：7B 81 00 10 31 33 39 31 32 33 34 35 36 37 38 7B
				response = "7B810010" + str.substring(8, 30) + "7B";
			} else if (str.substring(10, 12).equals("12")) {//对时
				//SOH	目标ID	设备ID	命令字	数据长度	STX	数据内容	ETX	CRC	 EOT
				//01	0010	0001	03	    0001	02	00	    03	BBBB 04
				//SOH	目标ID	设备ID	命令字	数据长度	STX	时间BCD	       ETX	CRC	   EOT
				//01	0001	0010	03	    0006	02	161030094450	03	BBBB	04
				SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmmss");
				String datastr = "01" + str.substring(6, 10) + str.substring(2, 6) + "03000602" + sdf.format(new Date()) + "03";
				response = Crcjs(datastr).replace(" ", "") + "04";
			}
		}else {
				response = str;
			}
		return response;
	}

	public static String Crcjs(String datastr) {
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
		String sbid = new BigInteger(str.substring(6,10),16).toString(10);//发送ID

		int tdgs = new BigInteger(str.substring(18,20),16).intValue();//通道个数

		String date = "20" + str.substring(26, 28) + "-" + str.substring(28, 30) + "-" + str.substring(30, 32) + " "
				+ str.substring(32, 34) + ":" + str.substring(34, 36) + ":" + str.substring(36, 38);//日期

		String dy = ""+Double.parseDouble(new BigInteger(str.substring(44,46),16).toString(10))/10;//电压

		String dataarr[] = new String[tdgs-3];
		int count = 0;

		for(int i=0;i<(tdgs-3);i++) {
			dataarr[count] = str.substring(54+i*10,64+i*10);
			count++;
		}
		String fs[] = {"","","","","","","","","",""};
		String fx[] = {"","","","","","","","","",""};
		String wd[] = {"","","","","","","","","",""};
		String sd[] = {"","","","","","","","","",""};
		String qy[] = {"","","","","","","","","",""};
		String yl[] = {"","","","","","","","","",""};
		String fsz[] = {"","","","","","","","","",""};
		String zw[] = {"","","","","","","","","",""};
		String zfs[] = {""};
		String yxfs[] = {""};
		for(int i=0;i<tdgs-3;i++) {
			String data = dataarr[i];
			if(data.substring(0,2).equals("82")) {//风速
				fs[Integer.valueOf(data.substring(2,3))] = ""+Double.parseDouble(new BigInteger(data.substring(6,10),16).toString(10))/10;
			}
			if(data.substring(0,2).equals("83")) {//风向
				fx[Integer.valueOf(data.substring(2,3))] = ""+Double.parseDouble(new BigInteger(data.substring(6,10),16).toString(10))/10;
			}
			if(data.substring(0,2).equals("01")) {//温度
				wd[Integer.valueOf(data.substring(2,3))] = ""+Double.parseDouble(String.valueOf(hexadecimal16Conversion(data.substring(6,10))))/10;
			}
			if(data.substring(0,2).equals("02")) {//湿度
				sd[Integer.valueOf(data.substring(2,3))] = ""+Double.parseDouble(new BigInteger(data.substring(6,10),16).toString(10))/10;
			}
			if(data.substring(0,2).equals("03")) {//气压
				qy[Integer.valueOf(data.substring(2,3))] = ""+Double.parseDouble(new BigInteger(data.substring(6,10),16).toString(10))/10;
			}
			if(data.substring(0,2).equals("06")) {//雨量
				yl[Integer.valueOf(data.substring(2,3))] = ""+Double.parseDouble(new BigInteger(data.substring(6,10),16).toString(10))/10;
			}
			if(data.substring(0,2).equals("08")) {//辐射改为土壤湿度
				fsz[Integer.valueOf(data.substring(2,3))] = ""+Double.parseDouble(new BigInteger(data.substring(6,10),16).toString(10))/10;
			}
			if(data.substring(0,2).equals("07")) {//紫外改为土壤温度
				zw[Integer.valueOf(data.substring(2,3))] = ""+Double.parseDouble(String.valueOf(hexadecimal16Conversion(data.substring(6,10))))/10;
			}
			if(data.substring(0,2).equals("0A")) {//总辐射
				zfs[Integer.valueOf(data.substring(2,3))] = ""+Double.parseDouble(new BigInteger(data.substring(6,10),16).toString(10))/10;
			}
			if(data.substring(0,2).equals("0C")) {//光合有效辐射
				yxfs[Integer.valueOf(data.substring(2,3))] = ""+Double.parseDouble(new BigInteger(data.substring(6,10),16).toString(10))/10;
			}
		}
		String id = ComUtils.getUniqueString();
		ConnDataBase bean = new ConnDataBase();
		try {
			String sql = "insert into sj_sjpz(id,sbid,date,dy,xtsj,dqwdz,dqsdz,dqylz,fspjz,"
					+ "fs0,fs1,fs2,fs3,fs4,fs5,fs6,fs7,fs8,fs9,"
					+ "fx0,fx1,fx2,fx3,fx4,fx5,fx6,fx7,fx8,fx9,"
					+ "wd0,wd1,wd2,wd3,wd4,wd5,wd6,wd7,wd8,wd9,"
					+ "sd0,sd1,sd2,sd3,sd4,sd5,sd6,sd7,sd8,sd9,"
					+ "qy0,qy1,qy2,qy3,qy4,qy5,qy6,qy7,qy8,qy9,"
					+ "yl0,yl1,yl2,yl3,yl4,yl5,yl6,yl7,yl8,yl9,"
					+ "fsz0,fsz1,fsz2,fsz3,fsz4,fsz5,fsz6,fsz7,fsz8,fsz9,"
					+ "zw0,zw1,zw2,zw3,zw4,zw5,zw6,zw7,zw8,zw9,zfs1,yxfs1)values("
					+ "'"+id+"','"+sbid+"',STR_TO_DATE('"+date+"','%Y-%m-%d %T'),'"+dy+"',NOW(),'"+wd[0]+"','"+sd[0]+"','"+qy[0]+"','"+fs[0]+"',"
					+ "'"+fs[0]+"','"+fs[1]+"','"+fs[2]+"','"+fs[3]+"','"+fs[4]+"','"+fs[5]+"','"+fs[6]+"','"+fs[7]+"','"+fs[8]+"','"+fs[9]+"',"
					+ "'"+fx[0]+"','"+fx[1]+"','"+fx[2]+"','"+fx[3]+"','"+fx[4]+"','"+fx[5]+"','"+fx[6]+"','"+fx[7]+"','"+fx[8]+"','"+fx[9]+"',"
					+ "'"+wd[0]+"','"+wd[1]+"','"+wd[2]+"','"+wd[3]+"','"+wd[4]+"','"+wd[5]+"','"+wd[6]+"','"+wd[7]+"','"+wd[8]+"','"+wd[9]+"',"
					+ "'"+sd[0]+"','"+sd[1]+"','"+sd[2]+"','"+sd[3]+"','"+sd[4]+"','"+sd[5]+"','"+sd[6]+"','"+sd[7]+"','"+sd[8]+"','"+sd[9]+"',"
					+ "'"+qy[0]+"','"+qy[1]+"','"+qy[2]+"','"+qy[3]+"','"+qy[4]+"','"+qy[5]+"','"+qy[6]+"','"+qy[7]+"','"+qy[8]+"','"+qy[9]+"',"
					+ "'"+yl[0]+"','"+yl[1]+"','"+yl[2]+"','"+yl[3]+"','"+yl[4]+"','"+yl[5]+"','"+yl[6]+"','"+yl[7]+"','"+yl[8]+"','"+yl[9]+"',"
					+ "'"+fsz[0]+"','"+fsz[1]+"','"+fsz[2]+"','"+fsz[3]+"','"+fsz[4]+"','"+fsz[5]+"','"+fsz[6]+"','"+fsz[7]+"','"+fsz[8]+"','"+fsz[9]+"',"
					+ "'"+zw[0]+"','"+zw[1]+"','"+zw[2]+"','"+zw[3]+"','"+zw[4]+"','"+zw[5]+"','"+zw[6]+"','"+zw[7]+"','"+zw[8]+"','"+zw[9]+"','"+zfs[0]+"','"+yxfs[0]+"')";
			bean.executeUpdate(sql);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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

	public static void main(String[] args) throws Exception {
		System.out.println("打开一个9026端口服务");
		UdpEchoServer9026_3 server = new UdpEchoServer9026_3(9026);
		server.start();
	}

}
