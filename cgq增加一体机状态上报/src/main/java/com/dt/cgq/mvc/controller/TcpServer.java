package com.dt.cgq.mvc.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.dt.common.db.ConnDataBase;

public class TcpServer {

    private boolean isFinished;
    private ServerSocket serverSocket;
    private ArrayList<SocketThread> socketThreads;
    private String devid;

    public TcpServer(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
        socketThreads = new ArrayList<>();
    }

    public void start(String devid) {
    	if(devid==null || devid.equals("")) devid = "00000";
    	this.devid = devid;
        isFinished = false;
        try {
            //等待客户端连接
            while (!isFinished) {
                Socket socket = serverSocket.accept();//接受连接
                //创建线程处理连接
                SocketThread socketThread = new SocketThread(socket);
                socketThreads.add(socketThread);
                socketThread.start();
            }
        } catch (IOException e) {
            isFinished = true;
        }
    }

    public void stop() {
        isFinished = true;
        for (SocketThread socketThread : socketThreads) {
            socketThread.interrupt();
            socketThread.close();
        }
        try {
            if (serverSocket != null) {
                serverSocket.close();
                serverSocket = null;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private class SocketThread extends Thread {

        private Socket socket;
        private InputStream in;
        private OutputStream out;

        SocketThread(Socket socket) {
            this.socket = socket;
            try {
                in = socket.getInputStream();
                out = socket.getOutputStream();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void run() {
            while (!isInterrupted()) {
                if (in == null) {
                    return;
                }
                try {
                    int available = in.available();
                    if (available > 0) {
                        byte[] buffer = new byte[available];
                        int size = in.read(buffer);
                        if (size > 0) {
                            String data = new String(buffer,0,size);
                            System.out.println("TCPClient say :" + data);
            			    
        			    	if (data.toUpperCase().startsWith("C28C0DB26D39331A")) {
        			    		
        			    		data = data.replaceAll("C28C0DB26D39331A", ""); 
       			    		    data = data.replaceAll("15B86F2D013B2618", "");
       						    JSONObject obj = JSONObject.parseObject(data);
       						    String msg_type = obj.get("msg_type").toString();
       						    String device_id = obj.get("device_id").toString();
       						 
       						    System.out.println("devid"+devid);
       						    if(device_id.equals(devid)) {
       						    	String str = "C28C0DB26D39331A";
       						    	JSONObject jsonObject = new JSONObject();
                			    	jsonObject.put("msg_type", 12);
                			    	jsonObject.put("timestamp", new Date().getTime());
                			    	jsonObject.put("action", 0);
                			    	jsonObject.put("channel", 1);
                			    	jsonObject.put("http_url", "http://36.139.74.97:9080/zzytpt/upload/uploadfile.do");
                			    	out.write((str + jsonObject.toString() + "15B86F2D013B2618").getBytes());
                			    	out.flush();
                			    	System.out.println(str + jsonObject.toString() + "15B86F2D013B2618");
                			    	devid = "00000";
                			    	System.out.println("devid"+devid);
       						    }
       						    
       						    if(msg_type.equals("1")) {
       						    	String str = "C28C0DB26D39331A";
                    				JSONObject jsonObject1 = new JSONObject();
                			    	jsonObject1.put("msg_type", 2);
                			    	jsonObject1.put("timestamp", new Date().getTime());
                			    	out.write((str + jsonObject1.toString() + "15B86F2D013B2618").getBytes());
                			    	out.flush();
                			    	System.out.println(str + jsonObject1.toString() + "15B86F2D013B2618");
                			    	
                			    	ConnDataBase bean = new ConnDataBase();
            						String sql1 = "select * from cgq_sj where time between DATE_ADD(NOW(), INTERVAL - 11 MINUTE) and NOW() and sbbh in("
            									+ "select sbbh from cgq_sbgl where deviceid ='"+device_id+"' and sffs1='0') and CONVERT(pjz,DECIMAL(9,1)) >= 10.8";
            						List list1 = bean.executeQuery(sql1);
            						String sql2 = "select * from cgq_sj where time between DATE_ADD(NOW(), INTERVAL - 20 MINUTE) and NOW() and sbbh in("
        									+ "select sbbh from cgq_sbgl where deviceid ='"+device_id+"' and sffs1='1') and CONVERT(pjz,DECIMAL(9,1)) <= 8.0";
        						    List list2 = bean.executeQuery(sql2);
    								if(list1.size()>2 || list2.size()>5) {
    									 JSONObject jsonObject2 = new JSONObject();
    			        			     jsonObject2.put("msg_type", 4);
    			        			     jsonObject2.put("channel", 1);
    			        			     jsonObject2.put("action", 1);
    			        			     jsonObject2.put("http_url", "http://36.139.74.97:9080/zzytpt/upload/uploadfile.do");
    			        			     jsonObject2.put("count", 5);
    			        			     jsonObject2.put("width", 640);
    			        			     jsonObject2.put("height", 480);
    			        			     jsonObject2.put("codec", 1);
    			        			     out.write((str + jsonObject2.toString() + "15B86F2D013B2618").getBytes());
    			        			     out.flush();
    			        			     System.out.println(str + jsonObject2.toString() + "15B86F2D013B2618"); 
    								 }
       						    }
        			    	}
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        }

        void close() {
            try {
                if (in != null) {
                    in.close();
                }

                if (out != null) {
                    out.close();
                }

                if (socket != null) {
                    socket.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }           

}
