package com.vshare.springboot.service;

import com.vshare.springboot.entity.GoodsSale;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Jerry
 * @since 2020-06-29
 */
public interface IGoodsSaleService extends IService<GoodsSale> {
    public void addCount(String goodsId, Integer count) throws Exception;

    void pccAddCount(String goodsId, Integer count);
}
