package com.vshare.springboot.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author Jerry
 * @since 2020-06-29
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class GoodsSale implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 销量 id
     */
    private String goodsSaleId;

    /**
     * 商品 id
     */
    @TableId
    private String goodsId;

    /**
     * 	销量
     */
    private Integer count;
    /**
     * 版本号
     */
    private Integer version;

}
