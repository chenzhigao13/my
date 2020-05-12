package com.liandi.common.netty.nio;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 传统的IO
 * 
 * @author czg
 * @date 2020/3/9 14:20
 */
public class OioServer {

    public static void main(String[] args) throws IOException {

        ServerSocket serverSocket = new ServerSocket(9999);

        while (true) {
            Socket socket = serverSocket.accept();

            System.out.println("有新的客服端连接了。");

            handler(socket);
        }

    }

    public static void handler(Socket socket) throws IOException {

        while (true) {

            InputStream is = socket.getInputStream();
            byte[] bytes = new byte[1024];
            int read = is.read(bytes);
            while (read < 0) {

            }

        }

    }

}
