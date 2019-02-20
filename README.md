# redisBatchDel
Redis Key 批量删除的工具

## 依赖
- jedis
- args4j

## 打包
mvn clean package assembly:signle

## 运行
```
java -jar redis-batch-del-allInOne.jar  -h ${redis主机名} -p ${redis端口} -a ${密码} -n ${指定database} -keys ${要删除的key的匹配表达式，如:hello*}
java -jar redis-batch-del-allInOne.jar  -h 192.168.1.10 -p 6379 -a mypassword -n 10 -keys testdel:*
```
参数说明:

-h redis地址,默认为127.0.0.1
-p redis端口号，默认为6379
-a redis密码，默认为空
-keys keys命令批量查找key用的表达式，如:hello*。无默认值，必填！
