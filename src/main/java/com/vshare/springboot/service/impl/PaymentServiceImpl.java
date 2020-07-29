package com.vshare.springboot.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.vshare.springboot.Mapper.PaymentMapper;
import com.vshare.springboot.entity.Payment;
import com.vshare.springboot.service.IPaymentService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Jerry
 * @since 2020-06-25
 */
@Service
public class PaymentServiceImpl extends ServiceImpl<PaymentMapper, Payment> implements IPaymentService {

    @Override
    public Payment getOneById(String id) {
        return baseMapper.getOneById(id);
    }
}
