package com.showmeco.myjdmall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.showmeco.myjdmall.product.entity.SpuInfo;

/**
* @author 糖饼
* @description 针对表【spu_info(商品表)】的数据库操作Service
* @createDate 2024-02-04 01:55:38
*/
public interface SpuInfoService extends IService<SpuInfo> {

	void saveSpuInfo(SpuInfo spuInfo);
}
