package com.test;

import java.util.HashMap;

import org.json.JSONObject;

import com.baidu.aip.ocr.AipOcr;

public class BaiduTest {
    //设置APPID/AK/SK
    public static final String APP_ID = "18933297";
    public static final String API_KEY = "C0GqZvI06qGVnvuu8R1x1RN7";
    public static final String SECRET_KEY = "aodujDSbXeGocTRa1Rkb6fvgCx2t5fR5";

    public static void main(String[] args) {
        // 初始化一个AipOcr
        AipOcr client = new AipOcr(APP_ID, API_KEY, SECRET_KEY);

        // 可选：设置网络连接参数
        client.setConnectionTimeoutInMillis(2000);
        client.setSocketTimeoutInMillis(60000);

        // 可选：设置代理服务器地址, http和socket二选一，或者均不设置
       // client.setHttpProxy("proxy_host", proxy_port);  // 设置http代理
        //client.setSocketProxy("proxy_host", proxy_port);  // 设置socket代理

        // 可选：设置log4j日志输出格式，若不设置，则使用默认配置
        // 也可以直接通过jvm启动参数设置此环境变量
        //System.setProperty("aip.log4j.conf", "path/to/your/log4j.properties");

        // 调用接口
        //String path = "D:\\face\\sfz.jpg";
        //JSONObject res = client.basicGeneral(path, new HashMap<String, String>());
        //System.out.println(res.toString(2));
        
        HashMap<String, String> options = new HashMap<String, String>();
        options.put("detect_direction", "true");
        options.put("detect_risk", "true");
        
        String idCardSide = "front";
        
        // 参数为本地图片路径
        String image = "D:\\face\\sfz.jpg";
        JSONObject res = client.idcard(image, idCardSide, options);
        System.out.println(res.toString(2));

        
    }
}
