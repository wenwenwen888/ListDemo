package com.wyt.list.util;

import android.content.Context;

import com.wyt.list.socketclient.SocketClient;


/**
 * Created by hcy on 2016/11/9.
 */

public class SingleSocket {

    private static SingleSocket uniqueSocket;

    private ClientSocket socket;

    private SocketClient client;

    private SingleSocket(Context context) {

        socket = new ClientSocket(context);
        socket.connect();
        socket.setListener(new ClientSocket.IsConnectedListener() {
            @Override
            public void sendMes(SocketClient socketClient) {
                client = socketClient;
            }
        });

    }

    public static synchronized SingleSocket getUniqueSocket(Context context) {
        if (uniqueSocket == null) {
            uniqueSocket = new SingleSocket(context);
        }
        return uniqueSocket;
    }

    public void sendMessage(String str) {
        if (client != null) {
            client.sendString(str);
        }
    }

    /**
     * 重新登录
     */
    public void refreshLogin(Context context) {
        socket.disconnect();
        uniqueSocket = null;
        new SingleSocket(context);
    }

    /**
     * 退出登录
     */
    public void exitLogin() {
        socket.disconnect();
        uniqueSocket = null;
    }
}
