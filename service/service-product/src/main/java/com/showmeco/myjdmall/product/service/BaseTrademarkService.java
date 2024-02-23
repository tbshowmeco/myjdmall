package com.showmeco.myjdmall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.showmeco.myjdmall.product.entity.BaseTrademark;

/**
* @author 糖饼
* @description 针对表【base_trademark(品牌表)】的数据库操作Service
* @createDate 2024-02-04 01:55:38
*/
public interface BaseTrademarkService extends IService<BaseTrademark> {

	void removeTrademark(Long id)  ;

	void myUpdate(BaseTrademark trademark);
}
