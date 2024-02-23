package com.showmeco.myjdmall.product.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.showmeco.myjdmall.product.entity.BaseTrademark;
import com.showmeco.myjdmall.product.mapper.BaseTrademarkMapper;
import com.showmeco.myjdmall.product.service.BaseTrademarkService;
import com.showmeco.myjdmall.product.service.MinioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 糖饼
 * @description 针对表【base_trademark(品牌表)】的数据库操作Service实现
 * @createDate 2024-02-04 01:55:38
 */
@Service
public class BaseTrademarkServiceImpl extends ServiceImpl<BaseTrademarkMapper, BaseTrademark> implements BaseTrademarkService {

	@Autowired
	MinioService minioService;

	@Override
	public void removeTrademark(Long id) {
		//先从//从minio中删除
		BaseTrademark thistrademark = this.getById(id);
		try {
			minioService.removeObject(thistrademark.getLogoUrl());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		// 数据库中删除
		this.removeById(id);
	}

	@Override
	public void myUpdate(BaseTrademark trademark) {

		//先判断minio中是否要删除
		BaseTrademark trademark1 = this.getById(trademark.getId());
		if (!trademark1.getLogoUrl()
		               .equals(trademark.getLogoUrl())) {
			//说明要删除
			try {
				minioService.removeObject(trademark.getLogoUrl());
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}


		this.updateById(trademark);

	}


}




