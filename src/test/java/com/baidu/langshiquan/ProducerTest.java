package com.baidu.langshiquan;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

import javax.jms.*;

/**
 * Created by Administrator on 2017/10/25.
 */
@RunWith(BlockJUnit4ClassRunner.class)
public class ProducerTest {

    @Test
    public void testProducer() throws Exception {
        // 创建ConnectionFactory [tcp://60.205.178.34:61616]
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_USER, ActiveMQConnection.DEFAULT_PASSWORD, MqConstant.URL);
        // 创建连接
        Connection connection = connectionFactory.createConnection();
        // 启动连接
        connection.start();
        // param1 是否开启事务 param2：应答模式
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        // 创建目标地址
        Destination destination = session.createQueue(MqConstant.QUEUE_NAME);
        // 创建生产者
        MessageProducer messageProducer = session.createProducer(destination);
        for (int i = 0; i < 100; i++) {
            TextMessage textMessage = session.createTextMessage("test" + i);
            // 发送消息
            messageProducer.send(textMessage);
        }
        // 关闭连接
        connection.close();
    }
}
