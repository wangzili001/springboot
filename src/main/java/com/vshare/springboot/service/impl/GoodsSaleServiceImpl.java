package com.vshare.springboot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.vshare.springboot.Mapper.GoodsSaleMapper;
import com.vshare.springboot.entity.GoodsSale;
import com.vshare.springboot.service.IGoodsSaleService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Jerry
 * @since 2020-06-29
 */
@Service
public class GoodsSaleServiceImpl extends ServiceImpl<GoodsSaleMapper, GoodsSale> implements IGoodsSaleService {

    @Async("taskExecutor")
    public void addCount(String goodsId, Integer count) throws Exception {
        while (true){
            GoodsSale goodsSale = baseMapper.selectById(goodsId);
            System.out.println(goodsSale.toString());
            if (goodsSale == null) {
                throw new Exception("数据不存在");
            }
            int counts = goodsSale.getCount() + count;
            goodsSale.setCount(counts);
            int flag = baseMapper.updateById(goodsSale);
            System.out.println(flag);
            if (flag > 0) {
                return;
            }
        }
    }

    @Override
    @Transactional
    public void pccAddCount(String goodsId, Integer count) {
        GoodsSale goodsSale = baseMapper.selectByIdPCC(goodsId);
        System.out.println(goodsSale.toString());
        int counts = goodsSale.getCount() + count;
        goodsSale.setCount(counts);
        int flag = baseMapper.updateByIdPCC(goodsSale);
        System.out.println(flag);
        if (flag > 0) {
            return;
        }
    }
}
