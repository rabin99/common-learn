### 基础
1. 修改vim颜色
```sh
:colorscheme darkblue
```
2. kafka中config配置中有个listener监听端口，可以带上ip相当于绑定主机。默认9092端口。

3. 后台启动`bin/kafka-server-start.sh -daemon config/server.properties` 

4. 清空kafka在zk信息：zk中删除（极大风险）：

   `/cluster `

   `/brokers`

   ` /admin` 

   ` /isr_change_notification` 

   ` /log_dir_event_notification` 

   ` /controller_epoch` 

   ` /consumers` 

   ` /latest_producer_id_block` 

   `/config`

#### 常用命令：

> 注意zk是否带有目录

```sh
flume安装在日志输出节点上/opt/flume/flume-**下
启动：启动flume：nohup bin/flume-ng agent -n a1 -c conf -f conf/flume.conf &

启动kafaka：bin/kafka-server-start.sh -daemon config/server.properties 
```
1. 在flu02、flu03、flu04装在/HELOWIN，zk在flu01~03
2. 创建topic名称为spark-log： 
```sh
bin/kafka-topics.sh --create --zookeeper flu02:2181 --replication-factor 3 --partitions 3 --topic spark-log
```
创建topic名称为news-access：
```java
bin/kafka-topics.sh --create --zookeeper flu02:2181 --replication-factor 3 --partitions 3 --topic news-access
```
4. 查看topic :` bin/kafka-topics.sh --list --zookeeper flu02:2181`
5. 测试发送数据：`bin/kafka-console-producer.sh --broker-list flu02:9092 --topic spark-log`
6. 查看消费数据：`bin/kafka-console-consumer.sh --zookeeper flu01:2181 --from-beginning --topic spark-log`
7. 查看消费者组消费情况：
```java
bin/kafka-consumer-groups.sh --zookeeper flu03:2181 --describe  --group test-consumer-group1
```
8. 删除topic

```sh
./kafka-topics.sh --delete --zookeeper flu02:2181 --topic news-access （还需到zk /broker/topic下删除first目录）
```
需要`server.properties`中设置`delete.topic.enable=true`否则只是标记删除或者直接重启。

9. 通过shell命令发送消息（发送连的是kafka broker，没有zk选项）

```sh
kafka-console-producer.sh --broker-list flu02:9092 --topic spark-log #连接一个其他的也都会发送
```
10. 通过shell消费消息（消费连的是zk，也可以指定broker，必填其中一个）

```sh
kafka-console-consumer.sh --zookeeper flu02:2181 --from-beginning --topic news-access
kafka-console-consumer.sh --bootstrap-server flu02:9092 --topic spark-log # 连接一个broker，不代表就消费者一个,需要指定消费partition，则加上--partition
```
11. 查看消费位置

```java
kafka-run-class.sh kafka.tools.ConsumerOffsetChecker --zookeeper flu02:2181 --group testGroup
```
12. 查看某个Topic的详情

```java
kafka-topics.sh --topic news-access --describe --zookeeper flu02:2181
```
