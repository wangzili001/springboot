package com.vshare.springboot.service;

import com.vshare.springboot.entity.Payment;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Jerry
 * @since 2020-06-25
 */
public interface IPaymentService extends IService<Payment> {
    Payment getOneById(String id);
}
