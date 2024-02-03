package com.showmeco.myjdmall.product.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * spu销售属性
 * @TableName spu_sale_attr
 */
@TableName(value ="spu_sale_attr")
@Data
public class SpuSaleAttr implements Serializable {
    /**
     * 编号(业务中无关联)
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 商品id
     */
    @TableField(value = "spu_id")
    private Long spuId;

    /**
     * 销售属性id
     */
    @TableField(value = "base_sale_attr_id")
    private Long baseSaleAttrId;

    /**
     * 销售属性名称(冗余)
     */
    @TableField(value = "sale_attr_name")
    private String saleAttrName;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}