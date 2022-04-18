package com.blue.blueplanserviceapplicationpc.utils;

import com.alibaba.fastjson.JSONObject;
import com.blue.blueplanserviceapplicationpc.Model.User;
import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.annotation.OnConnect;
import com.corundumstudio.socketio.annotation.OnDisconnect;
import com.corundumstudio.socketio.annotation.OnEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;


@Component
public class SocketIoServer {
    public static SocketIOServer socketIoServer;

    private static JSONObject result = new JSONObject();

    public int currentPersonNum = 0;

    @Autowired
    public SocketIoServer(SocketIOServer server) {
        this.socketIoServer = server;
    }

    @OnConnect
    public void onConnect(SocketIOClient client) {
        // TODO Auto-generated method stub

        String sa = client.getRemoteAddress().toString();
        String clientIp = sa.substring(1, sa.indexOf(":"));// 获取设备ip
        System.out.println(clientIp + "-------------------------" + "客户端已连接");  //真实环境存ip
        Map<String, List<String>> params = client.getHandshakeData().getUrlParams();
        System.out.println("----------路------" + params.get("userId") + "--------径----------");
        for (String list : params.get("userId")) {
            SocketIoServerMapUtil.put(list, client);
        }
        currentPersonNum++;

    }

    @OnDisconnect
    public void onDisconnect(SocketIOClient client) {
        // TODO Auto-generated method stub
        currentPersonNum--;
        String sa = client.getRemoteAddress().toString();
        String clientIp = sa.substring(1, sa.indexOf(":"));// 获取设备ip
        System.out.println(clientIp + "-------------------------" + "客户端已断开连接");
        SocketIoServerMapUtil.remove(clientIp);
        this.onPublicText("text");
    }

    @OnEvent(value = "text")
    public void onEvent(SocketIOClient client, AckRequest ackRequest, String data) {
        // TODO Auto-generated method stub
        // 客户端推送advert_info事件时，onData接受数据，这里是string类型的json数据，还可以为Byte[],object其他类型
        String sa = client.getRemoteAddress().toString();
        String clientIp = sa.substring(1, sa.indexOf(":"));// 获取客户端连接的ip
        Map<String, List<String>> params = client.getHandshakeData().getUrlParams();// 获取客户端url参数
        System.out.println(clientIp + "：客户端：************" + data);
        JSONObject gpsData = (JSONObject) JSONObject.parse(data);
        String userIds = gpsData.get("userName") + "";
        String taskIds = gpsData.get("password") + "";
        client.sendEvent("text1", "后台得到了数据");
    }

    //统计在线
    @OnEvent(value = "public_text_message")
    public void onPublicText(String data) {
        for (SocketIOClient client : SocketIoServerMapUtil.getValues()) {
            client.sendEvent("currentPersonList", currentPersonNum);

        }
    }

    //群聊
    @OnEvent(value = "public_message")
    public void publicMessage(String data) {
        System.out.println("-----群聊信息-----" + data + "----------");
        for (SocketIOClient client : SocketIoServerMapUtil.getValues()) {
            client.sendEvent("currentTextMsg", data);
        }
    }
}
