package com.showmeco.myjdmall.product.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.showmeco.myjdmall.product.entity.SpuInfo;
import com.showmeco.myjdmall.product.entity.SpuSaleAttr;
import com.showmeco.myjdmall.product.mapper.SpuInfoMapper;
import com.showmeco.myjdmall.product.service.SpuImageService;
import com.showmeco.myjdmall.product.service.SpuInfoService;
import com.showmeco.myjdmall.product.service.SpuSaleAttrService;
import com.showmeco.myjdmall.product.service.SpuSaleAttrValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * @author 糖饼
 * @description 针对表【spu_info(商品表)】的数据库操作Service实现
 * @createDate 2024-02-04 01:55:38
 */
@Service
public class SpuInfoServiceImpl extends ServiceImpl<SpuInfoMapper, SpuInfo> implements SpuInfoService {


	@Autowired
	SpuImageService spuImageService;


	@Autowired
	SpuSaleAttrService spuSaleAttrService;

	@Autowired
	SpuSaleAttrValueService spuSaleAttrValueService;

	@Override
	public void saveSpuInfo(SpuInfo spuInfo) {
		//保存spuinfo
		this.save(spuInfo);
		Long spuId = spuInfo.getId();



		CompletableFuture.runAsync(() -> {
			//保存spuimagelist
			spuInfo.getSpuImageList()
			       .stream()
			       .forEach(item -> {

				       item.setSpuId(spuId);
				       spuImageService.save(item);
			       });

		});


		CompletableFuture.runAsync(() -> {


			//保存spusaleattr
			List<SpuSaleAttr> spuSaleAttrList = spuInfo.getSpuSaleAttrList();
			spuSaleAttrList.stream()
			               .forEach(item -> {
				               item.setSpuId(spuId);
				               spuSaleAttrService.save(item);


				               CompletableFuture.runAsync(() -> {
					               //保存spusaleattrvale
					               item.getSpuSaleAttrValueList()
					                   .stream()
					                   .forEach(item1 -> {
						                   item1.setSpuId(spuId);
						                   item1.setSaleAttrName(item.getSaleAttrName());
						                   spuSaleAttrValueService.save(item1);
					                   });
				               });


			               });
		});


	}
}




