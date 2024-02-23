package com.showmeco.myjdmall.product.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.showmeco.myjdmall.product.entity.SpuSaleAttr;
import com.showmeco.myjdmall.product.entity.SpuSaleAttrValue;
import com.showmeco.myjdmall.product.mapper.SpuSaleAttrMapper;
import com.showmeco.myjdmall.product.service.SpuSaleAttrService;
import com.showmeco.myjdmall.product.service.SpuSaleAttrValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 糖饼
 * @description 针对表【spu_sale_attr(spu销售属性)】的数据库操作Service实现
 * @createDate 2024-02-04 01:55:38
 */
@Service
public class SpuSaleAttrServiceImpl extends ServiceImpl<SpuSaleAttrMapper, SpuSaleAttr> implements SpuSaleAttrService {


	@Autowired
	SpuSaleAttrValueService spuSaleAttrValueService;

	@Override
	public List<SpuSaleAttr> getSpuSaleAttrList(Long spuId) {

		List<SpuSaleAttr> list = this.lambdaQuery()
		                             .eq(SpuSaleAttr::getSpuId, spuId)
		                             .list();
		list.stream()
		    .forEach(item -> {

				//先根据spusaleattr查到对应的salevaluelist
			    List<SpuSaleAttrValue> saleValueList = spuSaleAttrValueService.lambdaQuery()
			                                                                  .eq(SpuSaleAttrValue::getSpuId, spuId)
			                                                                  .eq(SpuSaleAttrValue::getBaseSaleAttrId,
			                                                                      item.getBaseSaleAttrId())
			                                                                  .list();
			    item.setSpuSaleAttrValueList(saleValueList);
		    });

		return list;
	}
}




