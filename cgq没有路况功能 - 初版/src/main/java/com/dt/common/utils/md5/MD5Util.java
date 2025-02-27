package com.dt.common.utils.md5; 
import java.security.MessageDigest; 
import java.security.NoSuchAlgorithmException; 
/** 
 * MD5加密工具类 
 * @author james 
 * @time   2006-12-12 10:11:49 
 */ 
public class MD5Util { 
private static String byte2hex(byte[] bytes){ 
int i; 
StringBuffer buf=new StringBuffer(""); 
String t="";   // temporary 
for (i=0;i<bytes.length;i++){ 
t=Integer.toHexString(bytes[i]>=0?bytes[i]:(bytes[i]+256)); 
if(t.length()<2) t="0"+t; 
buf.append(t.toUpperCase()); 
} 
return buf.toString(); 
} 

/** 
 * 对字符串进行md5加密 
 * @param s 
 * @return 
 */ 
public static String md5(String s){ 
MessageDigest md5_dig = null; 
try { 
md5_dig = MessageDigest.getInstance("md5"); 
} catch (NoSuchAlgorithmException e) { 
// do nothing , it is impossible!! 
} 
byte[] bytes=s.getBytes(); 
md5_dig.update(bytes); 
return byte2hex(md5_dig.digest()); 
} 


public static void main(String args[]){ 
String s=new String("123456"); 
s=MD5Util.md5(s); 
System.out.println("the md5str:**"+s+"**"); 
} 
} 
