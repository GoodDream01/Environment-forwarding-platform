package com.dt.sjcs.mvc.controller;

import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import java.util.Scanner;

public class ServiceMQTT {

    public static final String HOST = "tcp://0.0.0.0:61613";
    private String ServiceID = "ServiceFirst";
    private String topic;
    private MqttClient client;
    private MqttTopic mqttTopic;
    private MqttConnectOptions options;
    private String user = "admin";
    private String password = "password";

    private MqttMessage message;

    public ServiceMQTT() throws MqttException {
        //创建连接
        client = new MqttClient(HOST,ServiceID,new MemoryPersistence());
        options = new MqttConnectOptions();
        options.setCleanSession(false);
        options.setKeepAliveInterval(20);
        options.setConnectionTimeout(50);
        options.setUserName(user);
        options.setPassword(password.toCharArray());
        message = new MqttMessage();
    }

    public void getConnect(){
        try {
            client.setCallback(new PublishCallBack());
            client.connect(options);
            mqttTopic = client.getTopic(topic);
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    public void publish(MqttTopic topic, MqttMessage message) throws MqttException {
        if(null != client&& client.isConnected()){
            MqttDeliveryToken token = topic.publish(message);
            token.waitForCompletion();
            System.out.println("消息推送的状态--->"+token.isComplete());
        }
    }


    public static void main(String[] args) throws MqttException {
        ServiceMQTT service = new ServiceMQTT();

        service.topic = "测试";
        String messageVal = "测试内容";

        service.getConnect();

        service.message.setQos(1);
        service.message.setRetained(true);
        service.message.setPayload(messageVal.getBytes());

        service.publish(service.mqttTopic,service.message);
        System.out.println("消息的保持状态："+service.message.isRetained());
    }
}

