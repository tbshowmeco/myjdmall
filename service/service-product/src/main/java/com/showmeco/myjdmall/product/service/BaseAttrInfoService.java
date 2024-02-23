package com.showmeco.myjdmall.product.service;

import com.showmeco.myjdmall.product.entity.BaseAttrInfo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author 糖饼
* @description 针对表【base_attr_info(属性表)】的数据库操作Service
* @createDate 2024-02-04 01:55:38
*/
public interface BaseAttrInfoService extends IService<BaseAttrInfo> {

	List<BaseAttrInfo> getAttrInfoListById(Long category1Id,
	                                       Long category2Id,
	                                       Long category3Id);

	void saveAttrInfo(BaseAttrInfo baseAttrInfo);

	void updateAttrInfo(BaseAttrInfo baseAttrInfo);
}
