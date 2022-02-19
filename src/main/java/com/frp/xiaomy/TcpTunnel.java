package com.frp.xiaomy;

import com.aio.portable.swiss.design.singleton.SingletonProvider;
import com.alibaba.fastjson.JSONObject;
import com.frp.xiaomy.common.Config;
import com.frp.xiaomy.utility.Packet;
import com.frp.xiaomy.utility.ReadWriteRunnable;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.concurrent.ThreadPoolExecutor;

public class TcpTunnel extends Thread {
    private Socket server_socket = null;
    private InputStream inputStream;
    private boolean flag = true;
    private boolean heatFlag = true;
    private ThreadPoolExecutor cachedThreadPool;
    private Config config = SingletonProvider.instance(Config.class);
    private Long time = Long.valueOf(System.currentTimeMillis());

    public TcpTunnel(ThreadPoolExecutor executorService) {
        this.cachedThreadPool = executorService;
    }

    public void start() {
        startTunnel();
        heart();
    }

    private void startTunnel() {
        stopTunnel();
        try {
            this.server_socket = new Socket(config.getServerIp(), 8888);
            System.out.println("启动成功！");
            this.inputStream = this.server_socket.getInputStream();
        } catch (Exception e) {
            stopTunnel();
            return;
        }


        JSONObject jsonObject = new JSONObject();
        jsonObject.put("userid", config.getUserId());
        jsonObject.put("token", config.getToken());
        jsonObject.put("id", config.getTunnelId());
        jsonObject.put("version", "3");
        jsonObject.put("type", "LOGIN");
        if (config.getIsFirst() != 0) {
            jsonObject.put("ChangeToken", "false");
        }
        this.flag = true;
        this.cachedThreadPool.execute(clientListen());

        Packet p = new Packet(jsonObject, "login".getBytes());
        if (!p.Send(this.server_socket)) ;
    }

    public void stopTunnel() {
        this.flag = false;
        try {
            if (this.server_socket != null) {
                this.inputStream.close();
                this.server_socket.close();
                this.server_socket = null;
                System.out.println("停止");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void heart() {
        Runnable runnable = new Runnable() {
            public void run() {
                try {
                    Thread.sleep(3000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                TcpTunnel.this.time = Long.valueOf(System.currentTimeMillis());
                while (TcpTunnel.this.heatFlag) {
                    try {
                        JSONObject jsonObject = new JSONObject();
                        jsonObject.put("type", "HEART");
                        Packet packet = new Packet(jsonObject, "heart".getBytes());
                        if (!packet.Send(TcpTunnel.this.server_socket)) {
                            System.out.println("本次发送心跳失败");
                        }
                        if (System.currentTimeMillis() - TcpTunnel.this.time.longValue() > 8000L) {
                            TcpTunnel.this.startTunnel();
                        }
                        Thread.sleep(6000L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        this.cachedThreadPool.execute(runnable);
    }

    public Runnable clientListen() {
        Runnable runnable = new Runnable() {
            public void run() {
                try {
                    while (TcpTunnel.this.flag && !TcpTunnel.this.server_socket.isClosed()) {
                        Packet packet = new Packet(TcpTunnel.this.inputStream);
                        if (packet.getHeadObject().getString("type").equals("LOGIN")) {
                            if (packet.getHeadObject().getString("msg").equals("LOGINOK")) {
                                if (config.getIsFirst() == 0) {
                                    config.setIsFirst(1);
                                }
                                TcpTunnel.this.time = Long.valueOf(System.currentTimeMillis());
                            } else {
                                TcpTunnel.this.stopTunnel();
                                System.exit(0);
                                break;
                            }
                        }
                        if (packet.getHeadObject().getString("type").equals("CONN")) {
                            Socket cs = null;
                            Socket ss = null;
                            try {
                                cs = new Socket();
                                cs.connect(new InetSocketAddress(config.getClientHost(), config.getClientPort()), 2000);
                            } catch (Exception e) {
                                continue;
                            }
                            ss = new Socket(config.getServerIp(), 8888);
                            JSONObject jsonObject = new JSONObject();
                            jsonObject.put("type", "CONN");
                            jsonObject.put("uid", packet.getHeadObject().getString("uid"));
                            Packet packet1 = new Packet(jsonObject, "null".getBytes());
                            packet1.Send(ss);
                            TcpTunnel.this.cachedThreadPool.execute(new ReadWriteRunnable(cs, ss));
                            TcpTunnel.this.cachedThreadPool.execute(new ReadWriteRunnable(ss, cs));
                        }
                        if (packet.getHeadObject().getString("type").equals("HEART")) {
                            TcpTunnel.this.time = Long.valueOf(System.currentTimeMillis());
                        }
                        if (packet.getHeadObject().getString("type").equals("MSG") &&
                                packet.getHeadObject().getString("msg").equals("STOP")) {
                            System.exit(0);
                        }
                    }
                } catch (Exception exception) {
                }
            }
        };
        return runnable;
    }
}
