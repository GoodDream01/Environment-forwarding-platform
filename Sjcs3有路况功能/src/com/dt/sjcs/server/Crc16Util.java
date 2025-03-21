package com.dt.sjcs.server;

public class Crc16Util {
	
	/**
     * 获取源数据和验证码的组合byte数组
     * @param strings 可变长度的十六进制字符串
     * @return
     */
    public static byte[] getData(String...strings) {
        byte[] data = new byte[]{};
        for (int i = 0; i<strings.length;i++) {
            int x = Integer.parseInt(strings[i], 16);
            byte n = (byte)x;
            byte[] buffer = new byte[data.length+1];
            byte[] aa = {n};
            System.arraycopy( data,0,buffer,0,data.length);
            System.arraycopy( aa,0,buffer,data.length,aa.length);
            data = buffer;
        }
        return getData(data);
    }
    public static String Crcjs(String datastr) {
        String[] dataarr = new String[datastr.length() / 2];
        int count = 0;
        for (int i = 0; i < datastr.length() - 1; i += 2) {
            dataarr[count] = datastr.substring(i, i + 2);
            count++;
        }
        byte[] dd = Crc16Util.getData(dataarr);
        return Crc16Util.byteTo16String(dd).substring(dd.length * 3 - 4).toUpperCase().replace(" ", "");
    }
    /**
     * 获取源数据和验证码的组合byte数组
     * @param aa 字节数组
     * @return
     */
    private static byte[] getData(byte[] aa) {
        byte[] bb = getCrc16(aa);
        byte[] cc = new byte[aa.length+bb.length];
        System.arraycopy(aa,0,cc,0,aa.length);
        System.arraycopy(bb,0,cc,aa.length,bb.length);
        return cc;
    }
    /**
     * 获取验证码byte数组，基于Modbus CRC16的校验算法
     */
    //替换为CRC-16算法
  /*  private static byte[] getCrc16(byte[] arr_buff) {
        short wcrc = 0;
		for (int i = 0; i < arr_buff.length; i++){
			byte c = arr_buff[i];
			for (int j = 0; j < 8; j++){
				char treat = (char) (c & 0x80);
		        c <<= 1;
		        char bcrc = (char) ((wcrc >> 8) & 0x80);
		        wcrc <<= 1;
		        if (treat != bcrc)
		        	wcrc ^= 0x1021;
		        }
		}
        return intToBytes(wcrc);
    }
    */
    private static byte[] getCrc16(byte[] arr_buff) {
        int crc = calcCRC(arr_buff, 0, arr_buff.length);
        return intToBytes(crc);
    }
    // 修改 calcCRC 方法，使用非反射方式计算后，再对输入字节和输出 CRC 分别做反转处理
    public static int calcCRC(byte[] data, int pos, int length) {
        int crc = 0xFFFF;
        for (int i = pos; i < pos + length; i++) {
            int b = data[i] & 0xFF;
            // 反转输入字节（INPUT REFIN）
            b = reverse8(b);
            crc ^= (b << 8);
            for (int j = 0; j < 8; j++) {
                if ((crc & 0x8000) != 0) {
                    crc = (crc << 1) ^ 0x1021;
                } else {
                    crc <<= 1;
                }
            }
        }
        crc &= 0xFFFF;
        // 输出数据反转（REFOUT）
        crc = reverse16(crc);
        return crc;
    }

    private static int reverse8(int b) {
        int rev = 0;
        for (int i = 0; i < 8; i++) {
            rev = (rev << 1) | (b & 1);
            b >>= 1;
        }
        return rev;
    }

    private static int reverse16(int crc) {
        int rev = 0;
        for (int i = 0; i < 16; i++) {
            rev = (rev << 1) | (crc & 1);
            crc >>= 1;
        }
        return rev;
    }


    /**
     * 将int转换成byte数组，低位在前，高位在后
     * 改变高低位顺序只需调换数组序号
     */

    private static byte[] intToBytes(int value)  {
        byte[] src = new byte[2];
        src[0] =  (byte) ((value>>8) & 0xFF);
        src[1] =  (byte) (value & 0xFF);
        return src;
    }
 
    /**
     * 将字节数组转换成十六进制字符串
     */
    public static String byteTo16String(byte[] data) {
        StringBuffer buffer = new StringBuffer();
        for (byte b : data) {
            buffer.append(byteTo16String(b));
        }
        return buffer.toString();
    }
    /**
     * 将字节转换成十六进制字符串
     * int转byte对照表
     * [128,255],0,[1,128)
     * [-128,-1],0,[1,128)
     */
    public static String byteTo16String(byte b) {
        StringBuffer buffer = new StringBuffer();
        int aa = (int)b;
        if (aa<0) {
            buffer.append(Integer.toString(aa+256, 16)+" ");
        }else if (aa==0) {
            buffer.append("00 ");
        }else if (aa>0 && aa<=15) {
            buffer.append("0"+Integer.toString(aa, 16)+" ");
        }else if (aa>15) {
            buffer.append(Integer.toString(aa, 16)+" ");
        }
        return buffer.toString();
    }
    
    public static void main(String[] args) {
    	String datastr = "01000100100300060216103009445003";
    	String dataarr[] = new String[datastr.length()/2];
        int count = 0;
        for(int i=0;i<datastr.length()-1;i=i+2) {
        	dataarr[count] = datastr.substring(i, i+2);
          	count++;
        }
        byte[] dd = Crc16Util.getData(dataarr);
        String str = Crc16Util.byteTo16String(dd).toUpperCase();
        System.out.println(str);
    }   
}


