package com.showmeco.myjdmall.product.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.showmeco.myjdmall.product.entity.SkuInfo;
import com.showmeco.myjdmall.product.mapper.SkuInfoMapper;
import com.showmeco.myjdmall.product.service.SkuAttrValueService;
import com.showmeco.myjdmall.product.service.SkuImageService;
import com.showmeco.myjdmall.product.service.SkuInfoService;
import com.showmeco.myjdmall.product.service.SkuSaleAttrValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

/**
 * @author 糖饼
 * @description 针对表【sku_info(库存单元表)】的数据库操作Service实现
 * @createDate 2024-02-04 01:55:38
 */
@Service
public class SkuInfoServiceImpl extends ServiceImpl<SkuInfoMapper, SkuInfo> implements SkuInfoService {

	@Autowired
	SkuSaleAttrValueService skuSaleAttrValueService;

	@Autowired
	SkuAttrValueService skuAttrValueService;

	@Autowired
	SkuImageService skuImageService;

	@Override
	public void saveSkuInfo(SkuInfo skuInfo) {

		//这里的sku字段是够了的,直接保存
		this.save(skuInfo);
		Long skuId = skuInfo.getId();

		//保存skuimagelist
		CompletableFuture.runAsync(() -> {
			skuInfo.getSkuImageList()
			       .stream()
			       .forEach(item -> {
				       item.setSkuId(skuId);
				       skuImageService.save(item);
			       });
		});


		//保存skuattrvaluelist
		CompletableFuture.runAsync(() -> {
			skuInfo.getSkuAttrValueList()
			       .stream()
			       .forEach(item -> {
				       item.setSkuId(skuId);
				       skuAttrValueService.save(item);
			       });
		});


		//保存skusaleattrvaluelist
		CompletableFuture.runAsync(() -> {
			skuInfo.getSkuSaleAttrValueList()
			       .stream()
			       .forEach(item -> {
				       item.setSkuId(skuId);
				       item.setSpuId(skuInfo.getSpuId());
				       skuSaleAttrValueService.save(item);
			       });
		});





	}
}




