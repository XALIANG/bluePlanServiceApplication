package com.blue.blueplanserviceapplicationpc.config;

import com.blue.blueplanserviceapplicationpc.utils.SocketIoServerMapUtil;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(value=1)
public class ServerRunner  implements CommandLineRunner {
    private final SocketIOServer server;

    @Autowired
    public ServerRunner(SocketIOServer server) {
        this.server = server;
    }

    @Override
    public void run(String... args) throws Exception {
        server.start();
        System.out.println("socket.io启动成功----------------！ aliang test");
        System.out.println("发 info--------");


    }
}
