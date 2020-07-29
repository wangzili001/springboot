//package com.vshare.springboot.controller;
//
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.vshare.springboot.config.RedissonConfig;
//import com.vshare.springboot.entity.Result;
//import com.vshare.springboot.entity.User;
//import com.vshare.springboot.service.IUserService;
//import com.vshare.springboot.utils.JSONChange;
//import com.vshare.springboot.utils.RedisUtil;
//import io.swagger.annotations.Api;
//import org.redisson.Redisson;
//import org.redisson.api.RLock;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.io.IOException;
//import java.util.Map;
//import java.util.concurrent.TimeUnit;
//
//@RestController
//@Api("redis操作类")
//public class RedisController {
//    @Autowired
//    RedisUtil redisUtil;
//    @Autowired
//    private IUserService iUserService;
//    @Autowired
//    private RedissonConfig redisson;
//    //加锁失效时间，毫秒
//    public static final int LOCK_EXPIRE_PX=3000;
//    //锁名称
//    public static final String LOCK_SUFFIX=":lock";
//
//
//    @GetMapping("/getCode")
//    public Result setString(String phone) {
//        int code = (int) ((Math.random() * 9 + 1) * 100000);
//        if(redisUtil.hasKey(phone)){
//            return Result.error("请五分钟后再试");
//        }
//        boolean set = redisUtil.set(phone, code, 5 * 60);
//        return Result.success(set);
//    }
//
//    @GetMapping("/getUserInfo/{id}")
//    public Result getUserList(@PathVariable("id") String id) throws InterruptedException {
//        String key = "user"+id;
//        RLock lock = redisson.getRedisson().getLock(LOCK_SUFFIX);
//        User user = null;
//        //加锁
//        lock.lock(10, TimeUnit.SECONDS);
//        try {
//            if(redisUtil.hasKey(key)){
//                System.out.println("缓存有数据！");
//                String result = (String) redisUtil.get(key);
//                if(result == null){
//                    System.out.println("但是数据为null");
//                    return Result.error("不存在改用户");
//                }
//                try {
//                    user = (User)JSONChange.jsonToObj(new User(), result);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//                return Result.success(user);
//            }
//            user = iUserService.getById(id);
//            if(user == null){
//                System.out.println("数据库没有数据  则插入空值到redis  防止缓存穿透");
//                redisUtil.set(key,null,60);
//                return Result.error("不存在改用户");
//            }
//            try {
//                String result = JSONChange.objToJson(user);
//                redisUtil.set(key,result);
//            } catch (JsonProcessingException e) {
//                e.printStackTrace();
//            }
//        }finally {
//            lock.unlock();
//        }
//        return Result.success(user);
//    }
//}
