package com.vshare.springboot.controller;

import com.vshare.springboot.config.JmsConfig;
import com.vshare.springboot.mq.Consumer;
import com.vshare.springboot.mq.Producer;
import org.apache.rocketmq.client.consumer.*;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.common.message.MessageQueue;
import org.apache.rocketmq.common.protocol.heartbeat.MessageModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

//@RestController
public class RocketController {
    @Autowired
    private Producer producer;

    private List<String> mesList;

    private static final Logger logger = LoggerFactory.getLogger(com.vshare.springboot.controller.RocketController.class);
    /**
     * 初始化消息
     */
    public RocketController() {
        mesList = new ArrayList<>();
        mesList.add("小小");
        mesList.add("爸爸");
        mesList.add("妈妈");
        mesList.add("爷爷");
        mesList.add("奶奶");

    }

    @GetMapping("/text/rocketmq")
    public Object callback() throws Exception {
        //总共发送五次消息
        for (String s : mesList) {
            //创建生产信息
            Message message = new Message(JmsConfig.TOPIC, "testtag", ("小小一家人的称谓:" + s).getBytes());
            //发送
            SendResult sendResult = producer.getProducer().send(message);
            logger.info("输出生产者信息={}",sendResult);
        }
        return "成功";
    }

    @GetMapping("/pull/rocketmq")
    public String pullRocketMQ() throws MQClientException {
        String group_name = "family";
        String TOPIC_TEST = "topic_family";

        MQPullConsumerScheduleService scheduleService = new MQPullConsumerScheduleService(group_name);

        DefaultMQPullConsumer consumer = scheduleService.getDefaultMQPullConsumer();

        consumer.setNamesrvAddr("localhost:9876");

        scheduleService.setMessageModel(MessageModel.CLUSTERING);

        scheduleService.registerPullTaskCallback(TOPIC_TEST, new PullTaskCallback() {

            public void doPullTask(MessageQueue mq, PullTaskContext context) {

                MQPullConsumer consumer = context.getPullConsumer();
                try {
                    //获取从哪里开始拉取
                    long offset = consumer.fetchConsumeOffset(mq, false);
                    if(offset < 0) {
                        offset = 0;
                    }
                    PullResult pullResult = consumer.pull(mq, "*", offset, 1);
                    switch (pullResult.getPullStatus()) {
                        case FOUND:
                            List<MessageExt> list = pullResult.getMsgFoundList();
                            for (MessageExt msg : list) {
                                System.out.println(new String(msg.getBody()));
                            }
                            break;
                        case NO_MATCHED_MSG:
                            break;
                        case NO_NEW_MSG:
                        case OFFSET_ILLEGAL:
                            break;
                        default:
                            break;
                    }
                    //存储offset，客户端每隔5s会定时刷新到broker
                    consumer.updateConsumeOffset(mq, pullResult.getNextBeginOffset());
                    //重新拉取 建议超过5s这样就不会重复获取
                    context.setPullNextDelayTimeMillis(6000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        scheduleService.start();
        return "成功";
    }
}
