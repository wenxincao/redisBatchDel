# redisBatchDel
Redis Key 批量删除的工具

主要是解决命令行批量删除key时无法删除包含空格、特殊字符的key的问题。如果没有这个问题，可以通过以下命令来批量删除redis中的key：
```
redis-cli -h 主机名 -p 端口号 -a 密码 -n 数据库编号 keys keys表达式 | xargs redis-cli -h 主机名 -p 端口号 -a 密码 -n 数据库编号  del
```


## 依赖
- jedis
- args4j

## 打包
mvn clean package assembly:signle

## 运行
```
例: java -jar redis-batch-del-allInOne.jar  -h 192.168.1.10 -p 6379 -a mypassword -n 10 -keys testdel:*
```
**参数说明:**
- -h redis地址,默认为127.0.0.1
- -p redis端口号，默认为6379
- -a redis密码，默认为空
- -keys keys命令批量查找key用的表达式，如:hello*。无默认值，必填！

*注:这里命令行的入参，除了-keys外，其他都与redis-cli命令参数保持一致，避免记忆麻烦。*