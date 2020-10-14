# redisBatchDel
Redis Key 批量处理的工具

主要是解决命令行批量处理key时无法处理包含空格、特殊字符的key的问题。如果没有这个问题，可以通过以下命令来批量删除redis中的key：
```
redis-cli -h 主机名 -p 端口号 -a 密码 -n 数据库编号 keys keys表达式 | xargs redis-cli -h 主机名 -p 端口号 -a 密码 -n 数据库编号  del -c 集群模式
```


## 依赖
- jedis
- args4j

## 打包
mvn clean package assembly:single

## 运行
```
# 批量模糊查询key
单机模式
java -jar redis-batch-del-allInOne.jar  -h 192.168.1.10 -p 6379 -a mypassword -n 10 -f GET_BY_KEYS -params "-keys testdel:*" -c 0
集群模式
java -jar redis-batch-del-allInOne.jar  -h 192.168.1.10 -p 6379 -a mypassword -n 10 -f GET_BY_KEYS -params "-keys testdel:*" -c 1

# 批量删除key
单机模式
java -jar redis-batch-del-allInOne.jar  -h 192.168.1.10 -p 6379 -a mypassword -n 10 -f DEL_BY_KEYS -params "-keys testdel:*" -c 0
集群模式
java -jar redis-batch-del-allInOne.jar  -h 192.168.1.10 -p 6379 -f DEL_BY_KEYS -params "-keys testdel:*" -c 1

# 批量移动key，可以跨不同的库(未验证，待实现）
java -jar redis-batch-del-allInOne.jar  -h 192.168.1.10 -p 6379  -n 11 -f MOVE_BY_KEYS -params "-keys * -targetH 192.168.1.12 -targetP 16378 -targetN 1"
```
**通用参数说明:**
- -h redis地址,默认为127.0.0.1
- -p redis端口号，默认为6379
- -a redis密码，默认为空
- -n redis的database编号
- -f 操作名称，支持 DEL_BY_KEYS,MOVE_BY_KEYS,COPY_BY_KEYS
- -params 具体的操作的参数，根据操作不同而不同

**批量删除，操作名称：DEL_BY_KEYS**
- -keys keys命令批量查找key用的表达式，如:hello*。无默认值，必填！


**移动/复制keys，操作名称：MOVE_BY_KEYS,COPY_BY_KEYS**
- 通用参数作为源库参数
- -keys keys命令批量查找key用的表达式，如:hello*。无默认值，必填！
- -targetH 目标库的地址，必填
- -targetP 目标库的端口号，必填
- -targetN 目标库的编号，必填
- -targetA 目标库的密码，默认为空

*注:这里命令行的通用入参，基本与redis-cli命令参数保持一致，避免记忆麻烦。*
