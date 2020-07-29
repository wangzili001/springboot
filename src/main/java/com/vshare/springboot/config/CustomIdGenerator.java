package com.vshare.springboot.config;

import com.baomidou.mybatisplus.core.incrementer.IdentifierGenerator;
import com.vshare.springboot.utils.SnowflakeIdWorker;
import org.springframework.stereotype.Component;

@Component
public class CustomIdGenerator implements IdentifierGenerator {

    @Override
    public Number nextId(Object entity) {
        //可以将当前传入的class全类名来作为bizKey,或者提取参数来生成bizKey进行分布式Id调用生成.
        String bizKey = entity.getClass().getName();
        //根据bizKey调用分布式ID生成
        SnowflakeIdWorker idWorker = new SnowflakeIdWorker(0, 0);
        long id =idWorker.nextId() ;
        //返回生成的id值即可.
        return id;
    }

}
