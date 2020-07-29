//package com.vshare.springboot.config;
//
//import org.redisson.Redisson;
//import org.redisson.api.RedissonClient;
//import org.redisson.config.Config;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//
////@Configuration
//public class RedissonConfig {
//    @Bean
//    public RedissonClient getRedisson(){
//        Config config = new Config();
//        config.useSentinelServers()
//                .setMasterName("mymaster")
//                //可以用"rediss://"来启用SSL连接
//                .addSentinelAddress("redis://192.168.31.101:26379", "redis://192.168.31.102:26379")
//                .addSentinelAddress("redis://192.168.31.103:26379");
//
//        RedissonClient redisson = Redisson.create(config);
//        return redisson;
//    }
//}
