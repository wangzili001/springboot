package com.vshare.springboot.Mapper;

import com.vshare.springboot.entity.Payment;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Jerry
 * @since 2020-06-25
 */
public interface PaymentMapper extends BaseMapper<Payment> {
    @Select("select * from payment where id = #{id}")
    Payment getOneById(String id);
}
