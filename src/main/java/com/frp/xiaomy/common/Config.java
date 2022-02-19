package com.frp.xiaomy.common;

public class Config {
    public int userId;
    public int tunnelId;
    public int clientPort;
    public String clientHost;
    public String serverIp;
    public int serverPort;
    public String domain;
    public int type;
    public int autoConn;
    public String name;
    public Integer role;
    public String token;
    public int isFirst = 0;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getTunnelId() {
        return tunnelId;
    }

    public void setTunnelId(int tunnelId) {
        this.tunnelId = tunnelId;
    }

    public int getClientPort() {
        return clientPort;
    }

    public void setClientPort(int clientPort) {
        this.clientPort = clientPort;
    }

    public String getClientHost() {
        return clientHost;
    }

    public void setClientHost(String clientHost) {
        this.clientHost = clientHost;
    }

    public String getServerIp() {
        return serverIp;
    }

    public void setServerIp(String serverIp) {
        this.serverIp = serverIp;
    }

    public int getServerPort() {
        return serverPort;
    }

    public void setServerPort(int serverPort) {
        this.serverPort = serverPort;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getAutoConn() {
        return autoConn;
    }

    public void setAutoConn(int autoConn) {
        this.autoConn = autoConn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getIsFirst() {
        return isFirst;
    }

    public void setIsFirst(int isFirst) {
        this.isFirst = isFirst;
    }

    public static String getServer_ip() {
        return server_ip;
    }

    public static void setServer_ip(String server_ip) {
        Config.server_ip = server_ip;
    }

    public static String server_ip = "http://nw.xiaomy.net:8080";
}
