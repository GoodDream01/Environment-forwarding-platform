package com.dt.common.utils;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;

/**
 * 在线语音合成 WebAPI 接口调用示例
 * 
 * 运行方法：直接运行 main()
 * 
 * 结果： 控制台输出接口返回值信息
 * 
 * @author iflytek_aiui
 * 
 */
public class OnlineTTSUtils {
	private static final String URL = "http://api.xfyun.cn/v1/service/v1/tts";
	private static final String APPID = "5bce847d";
	private static final String API_KEY = "ec085e94ffcd092f218235118be6a4ce";
	private static final String AUE = "raw";
	private static final String AUEF = "audio/L16;rate=16000";
	private static final String VOICE_NAME = "xiaoyan";
	private static final String ENGINE_TYPE = "intp65";
	private static final String FILE_PATH = SystemConstant.SysBaseDir;
	
	public static void main(String[] args) throws IOException,ParseException, InterruptedException{
		Map<String, String> header = buildHeader();
		//byte[] dataByteArray = readFile(FILE_PATH);
		String data = getBody("有道翻译提供即时免费的中文、英语、日语、韩语、法语、德语、俄语、西班牙语、葡萄牙语、越南语、印尼语全文翻译、网页翻译、文档翻译服务。");
		String audioFile = "E:/a.wav";
		String result = httpPost(URL, header, data, audioFile);
		System.out.println(result);		
	}
	
	public static TTSResult CreateVoice(String text){
		TTSResult result = new TTSResult(false, "");
		try {
			Map<String, String> header = buildHeader();
			String data = getBody(text);
			String fileName = getAudioUrl();
			String audioFile = FILE_PATH+"/"+fileName;
			
			String res = httpPost(URL, header, data, audioFile);
			
			boolean flag = false;
			if(res.equals("")){
				flag = true;
			}
			result = new TTSResult(flag, fileName);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}

	private static String getBody(String text){
		String data = "text="+text+"";
		return data;
	}
	private static Map<String, String> buildHeader() throws UnsupportedEncodingException, ParseException {
		String curTime = System.currentTimeMillis() / 1000L + "";
		String param = "{\"aue\":\""+AUE+"\",\"auf\":\""+AUEF+"\",\"voice_name\":\""+VOICE_NAME+"\",\"engine_type\":\""+ENGINE_TYPE+"\"}";		
		//使用个性化参数时参数格式如下：
		//String param = "{\"aue\":\""+AUE+"\",\"sample_rate\":\""+SAMPLE_RATE+"\",\"auth_id\":\""+AUTH_ID+"\",\"data_type\":\""+DATA_TYPE+"\",\"scene\":\""+SCENE+"\",\"pers_param\":\""+PERS_PARAM+"\"}";
		String paramBase64 = new String(Base64.encodeBase64(param.getBytes("UTF-8")));
		String checkSum = DigestUtils.md5Hex(API_KEY + curTime + paramBase64);

		Map<String, String> header = new HashMap<String, String>();
		header.put("X-Param", paramBase64);
		header.put("X-CurTime", curTime);
		header.put("X-CheckSum", checkSum);
		header.put("X-Real-Ip", "127.0.0.1");
		header.put("X-Appid", APPID);
		header.put("Content-Type", "application/x-www-form-urlencoded; charset=utf-8");
		return header;
	}
	
	private static byte[] readFile(String filePath) throws IOException {
		InputStream in = new FileInputStream(filePath);
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024 * 4];
		int n = 0;
		while ((n = in.read(buffer)) != -1) {
			out.write(buffer, 0, n);
		}
		byte[] data = out.toByteArray();
		in.close();
		return data;
	}
	
	private static String httpPost(String url, Map<String, String> header, String data,String audioFile) {
		String result = "";
		BufferedReader in = null;
		OutputStream out = null;
		try {
			URL realUrl = new URL(url);
			HttpURLConnection connection = (HttpURLConnection)realUrl.openConnection();
			for (String key : header.keySet()) {
				connection.setRequestProperty(key, header.get(key));
			}
			connection.setDoOutput(true);
			connection.setDoInput(true);
			
			connection.setConnectTimeout(60000);
			connection.setReadTimeout(60000);
			try {
				out = connection.getOutputStream();
				out.write(data.getBytes("UTF-8"));
				out.flush();
				
	            //读取响应	            
				BufferedInputStream reader = new BufferedInputStream(connection.getInputStream());
				String headerField = connection.getHeaderField("Content-type");
				System.out.println(headerField);
				out.close();
				if (headerField.equalsIgnoreCase("text/plain")) {
					System.out.println("错误");
					in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
					
					String line;
					String res = "";
					while ((line = in.readLine()) != null) {
						res += line;
					}
					System.out.println(res);
				} else {
					OutputStream outs = new FileOutputStream(audioFile);
					int size = 0, len = 0;
					byte[] buf = new byte[1024];
					while ((size = reader.read(buf)) != -1) {
						len += size;
						outs.write(buf, 0, size);
					}
					outs.close();
					reader.close();
				}
				connection.disconnect();

			} catch (Exception e) {
				e.printStackTrace();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public static String getAudioUrl(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String dayfolder = sdf.format(new Date());
		String ext = ".wav";
		if(AUE.equals("lame")){
			ext = ".map3";
		}
		String filePath = SystemConstant.AudioPath+"/"+dayfolder;
		String path = FILE_PATH+"/"+filePath;
		
		File file = new File(path);
		file.mkdirs();
		
		String url = filePath+"/"+ComUtils.getUniqueString()+ext;
		return url;
	}
}