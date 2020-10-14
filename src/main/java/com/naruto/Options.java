package com.naruto;

import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.Option;

import java.util.ArrayList;
import java.util.List;

/**
 * 命令行基础参数
 *
 * @author naruto
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
//    @Option(name = "-keys", usage = "keys pattern", required = true)
//    private String keysPattern;

    @Option(name = "-params", usage = "params", required = false)
    private String params;

    @Option(name = "-f", usage = "function", required = true)
    private Function function;

    @Option(name = "-c", usage = "cluster", required = false)
    private int cluster = 0;

    @Argument
    private List<String> arguments = new ArrayList<String>();

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

//    public String getKeysPattern() {
//        return keysPattern;
//    }
//
//    public void setKeysPattern(String keysPattern) {
//        this.keysPattern = keysPattern;
//    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public Function getFunction() {
        return function;
    }

    public void setFunction(Function function) {
        this.function = function;
    }

    public List<String> getArguments() {
        return arguments;
    }

    public void setArguments(List<String> arguments) {
        this.arguments = arguments;
    }


    public int getCluster() {
        return cluster;
    }

    public void setCluster(int cluster) {
        this.cluster = cluster;
    }

    @Override
    public String toString() {
        return "Options{" +
                "host='" + host + '\'' +
                ", port=" + port +
                ", password='" + password + '\'' +
                ", dbIndex=" + dbIndex +
                ", params='" + params + '\'' +
                ", function='" + function + '\'' +
                ", arguments=" + arguments +
                ", cluster=" + cluster +
                '}';
    }
}
