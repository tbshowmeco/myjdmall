package com.showmeco.myjdmall.product.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 说明:
 *
 * @Author: @showmeco
 * @Date: 2024/2/3 2:40
 */

@Data
 @TableName("base_category1")
public class BaseCategory1 {

    @TableId(type = IdType.AUTO)
    public Long id;

    @TableField(value = "name")
    public String name;
}
