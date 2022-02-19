package com.frp.xiaomy.bean;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DataDTO {
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Object getTimePriceId() {
        return timePriceId;
    }

    public void setTimePriceId(Object timePriceId) {
        this.timePriceId = timePriceId;
    }

    public Integer getServerPort() {
        return serverPort;
    }

    public void setServerPort(Integer serverPort) {
        this.serverPort = serverPort;
    }

    public Integer getClientPort() {
        return clientPort;
    }

    public void setClientPort(Integer clientPort) {
        this.clientPort = clientPort;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public Object getServerId() {
        return serverId;
    }

    public void setServerId(Object serverId) {
        this.serverId = serverId;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Object getStartTime() {
        return startTime;
    }

    public void setStartTime(Object startTime) {
        this.startTime = startTime;
    }

    public Long getStopTime() {
        return stopTime;
    }

    public void setStopTime(Long stopTime) {
        this.stopTime = stopTime;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getClientHost() {
        return clientHost;
    }

    public void setClientHost(String clientHost) {
        this.clientHost = clientHost;
    }

    public Integer getBandwidth() {
        return bandwidth;
    }

    public void setBandwidth(Integer bandwidth) {
        this.bandwidth = bandwidth;
    }

    public String getServerIp() {
        return serverIp;
    }

    public void setServerIp(String serverIp) {
        this.serverIp = serverIp;
    }

    public String getPortToken() {
        return portToken;
    }

    public void setPortToken(String portToken) {
        this.portToken = portToken;
    }

    public Object getTunnelToken() {
        return tunnelToken;
    }

    public void setTunnelToken(Object tunnelToken) {
        this.tunnelToken = tunnelToken;
    }

    public Integer getAutoConn() {
        return autoConn;
    }

    public void setAutoConn(Integer autoConn) {
        this.autoConn = autoConn;
    }

    public String getStopTime1() {
        return stopTime1;
    }

    public void setStopTime1(String stopTime1) {
        this.stopTime1 = stopTime1;
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getTunnelGrade() {
        return tunnelGrade;
    }

    public void setTunnelGrade(Integer tunnelGrade) {
        this.tunnelGrade = tunnelGrade;
    }

    @JsonProperty("id")
    private Integer id;
    @JsonProperty("time_price_id")
    private Object timePriceId;
    @JsonProperty("server_port")
    private Integer serverPort;
    @JsonProperty("client_port")
    private Integer clientPort;
    @JsonProperty("name")
    private String name;
    @JsonProperty("create_time")
    private Long createTime;
    @JsonProperty("type")
    private Integer type;
    @JsonProperty("domain")
    private String domain;
    @JsonProperty("server_id")
    private Object serverId;
    @JsonProperty("state")
    private Integer state;
    @JsonProperty("start_time")
    private Object startTime;
    @JsonProperty("stop_time")
    private Long stopTime;
    @JsonProperty("user_id")
    private Integer userId;
    @JsonProperty("client_host")
    private String clientHost;
    @JsonProperty("bandwidth")
    private Integer bandwidth;
    @JsonProperty("server_ip")
    private String serverIp;
    @JsonProperty("port_token")
    private String portToken;
    @JsonProperty("tunnel_token")
    private Object tunnelToken;
    @JsonProperty("auto_conn")
    private Integer autoConn;
    @JsonProperty("stop_time_1")
    private String stopTime1;
    @JsonProperty("flag")
    private Integer flag;
    @JsonProperty("status")
    private Integer status;
    @JsonProperty("tunnelGrade")
    private Integer tunnelGrade;
}
