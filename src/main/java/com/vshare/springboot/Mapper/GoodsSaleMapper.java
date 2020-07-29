package com.vshare.springboot.Mapper;

import com.vshare.springboot.entity.GoodsSale;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Jerry
 * @since 2020-06-29
 */
public interface GoodsSaleMapper extends BaseMapper<GoodsSale> {
    @Select("select * from goods_sale where goods_id = #{goodId}")
    GoodsSale selectById(@Param("goodId") String goodId);

    @Update("update goods_sale set count = #{goodsSale.count},version = #{goodsSale.version} +1" +
            " where goods_id = #{goodsSale.goodsId} and  version = #{goodsSale.version}")
    int updateById(@Param("goodsSale") GoodsSale goodsSale);

    @Select("select * from goods_sale where goods_id = #{goodId} for update")
    GoodsSale selectByIdPCC(@Param("goodId") String goodId);

    @Update("update goods_sale set count = #{goodsSale.count}" +
            " where goods_id = #{goodsSale.goodsId} ")
    int updateByIdPCC(@Param("goodsSale") GoodsSale goodsSale);
}
