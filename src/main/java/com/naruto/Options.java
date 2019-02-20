package com.naruto;

import org.kohsuke.args4j.Option;

/**
 * 命令行参数
 *
 * @author Min.Xu
 * @date 2019-02-20 10:10
 **/
public class Options {

    @Option(name = "-h", usage = "host", required = false)
    private String host = "localhost";
    @Option(name = "-p", usage = "port", required = false)
    private int port = 6379;
    @Option(name = "-a", usage = "password", required = false)
    private String password = "";
    @Option(name = "-n", usage = "dbIndex", required = false)
    private int dbIndex = 0;
    @Option(name = "-keys", usage = "keys pattern", required = true)
    private String keysPattern;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getDbIndex() {
        return dbIndex;
    }

    public void setDbIndex(int dbIndex) {
        this.dbIndex = dbIndex;
    }

    public String getKeysPattern() {
        return keysPattern;
    }

    public void setKeysPattern(String keysPattern) {
        this.keysPattern = keysPattern;
    }

    @Override
    public String toString() {
        return "Options{" +
                "host='" + host + '\'' +
                ", port=" + port +
                ", password='" + password + '\'' +
                ", dbIndex=" + dbIndex +
                ", keysPattern='" + keysPattern + '\'' +
                '}';
    }
}
