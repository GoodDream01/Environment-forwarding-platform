package com.dt.sjcs.mvc.controller;

import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import java.util.Scanner;

public class ClientMQTT {

    public static final String HOST = "tcp://0.0.0.0:61613";
    private static final String clientID = "clientFirst";
    private String TOPIC;
    private MqttClient client;
    private MqttConnectOptions options;
    private String user = "admin";
    private String password = "password";


    public void clientStart(){
        try {
            client = new MqttClient(HOST,clientID,new MemoryPersistence());
            options = new MqttConnectOptions();
            options.setCleanSession(true);
            options.setKeepAliveInterval(10);
            options.setConnectionTimeout(50);
            options.setUserName(user);
            options.setPassword(password.toCharArray());
            client.setCallback(new PublishCallBack());
            TOPIC = "测试";
            MqttTopic topic = client.getTopic(TOPIC);
            //setWill方法，如果项目中需要知道客户端是否掉线可以调用该方法。设置最终端口的通知消息
            options.setWill(topic,"close".getBytes(),1,true);
            client.connect(options);
            int[] Qos = {1};
            String[] topic1 = {TOPIC};
            client.subscribe(topic1,Qos);
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws MqttException {
        ClientMQTT clientMQTT = new ClientMQTT();
        clientMQTT.clientStart();
    }
}

