
## 生产者架构

![在这里插入图片描述](https://img-blog.csdnimg.cn/20200713162238318.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3UwMTM4Mzc4MjU=,size_16,color_FFFFFF,t_70)

消息 --> 生产者拦截器 --> 序列化器 --> 分发器 --> ... ---> Broker