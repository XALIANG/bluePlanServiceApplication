package com.blue.blueplanserviceapplicationpc.config;

import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.annotation.SpringAnnotationScanner;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class NettySocketConfig {
    @Value("${socketio.host}")
    private String host;
    //    private String ipLinxu;
    @Value("${socketio.port}")
    private int port;

    @Bean
    public SocketIOServer socketIOServer() throws Exception {

        com.corundumstudio.socketio.Configuration config = new com.corundumstudio.socketio.Configuration();
        String os = System.getProperty("os.name");
        System.out.println("os" + os);
        if (os.toLowerCase().startsWith("win")) {
            //在本地window环境测试时用localhost
            config.setHostname(host);
        } else {
            //公网ip
//            config.setHostname(ipLinxu);
        }
        // 端口，任意
        config.setPort(port);
        config.setMaxFramePayloadLength(1024 * 1024);
        config.setMaxHttpContentLength(1024 * 1024);
        //该处进行身份验证h
        config.setAuthorizationListener(handshakeData -> {
            //http://localhost:8081?username=test&password=test
            //例如果使用上面的链接进行connect，可以使用如下代码获取用户密码信息
            String userId = handshakeData.getSingleUrlParam("userId");
//            String password = handshakeData.getSingleUrlParam("password");
            return true;
        });
        final SocketIOServer server = new SocketIOServer(config);
        return server;
    }

    @Bean
    public SpringAnnotationScanner springAnnotationScanner(SocketIOServer socketServer) {
        return new SpringAnnotationScanner(socketServer);
    }
}
