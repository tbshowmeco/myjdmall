package com.showmeco.myjdmall.product.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.showmeco.myjdmall.product.entity.BaseAttrInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author 糖饼
* @description 针对表【base_attr_info(属性表)】的数据库操作Mapper
* @createDate 2024-02-04 01:55:38
* @Entity com.showmeco.myjdmall.product.entity.BaseAttrInfo
*/
public interface BaseAttrInfoMapper extends BaseMapper<BaseAttrInfo> {

	List<BaseAttrInfo> getAttrInfoListById(@Param("c1Id") Long category1Id,
	                                       @Param("c2Id") Long category2Id,
	                                       @Param("c3Id") Long category3Id);
}




