package com.showmeco.myjdmall.product.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.showmeco.myjdmall.product.entity.BaseAttrInfo;
import com.showmeco.myjdmall.product.entity.BaseAttrValue;
import com.showmeco.myjdmall.product.mapper.BaseAttrInfoMapper;
import com.showmeco.myjdmall.product.service.BaseAttrInfoService;
import com.showmeco.myjdmall.product.service.BaseAttrValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

/**
 * @author 糖饼
 * @description 针对表【base_attr_info(属性表)】的数据库操作Service实现
 * @createDate 2024-02-04 01:55:38
 */
@Service
public class BaseAttrInfoServiceImpl extends ServiceImpl<BaseAttrInfoMapper, BaseAttrInfo> implements BaseAttrInfoService {

	@Autowired
	BaseAttrInfoMapper baseAttrInfoMapper;

	@Autowired
	BaseAttrInfoService baseAttrInfoService;
	@Autowired
	BaseAttrValueService baseAttrValueService;

	@Override
	public List<BaseAttrInfo> getAttrInfoListById(Long category1Id,
	                                              Long category2Id,
	                                              Long category3Id) {
		return baseAttrInfoMapper.getAttrInfoListById(category1Id, category2Id, category3Id);
	}

	@Override
	public void saveAttrInfo(BaseAttrInfo baseAttrInfo) {
		//这里要分步骤存储   注意mybatisplus有id回填,后面要用
		this.save(baseAttrInfo);
		//保存attrvalue
		baseAttrInfo.getAttrValueList()
		            .stream()
		            .forEach(item -> {
			            //先设置attrid,
			            item.setAttrId(baseAttrInfo.getId());
			            baseAttrValueService.save(item);
		            });
	}

	@Override
	public void updateAttrInfo(BaseAttrInfo baseAttrInfo) {

		//先更新属性名
		baseAttrInfoService.updateById(baseAttrInfo);

		//涉及到属性值的更新,这里面有三种情况,一种是原有属性删除了,一种是原有属性更改了,一种是新增的


		List<BaseAttrValue> attrValueList = baseAttrInfo.getAttrValueList();

		//先删除
		List<Long> ids = attrValueList.stream()
		                              .filter(item -> item.getId() != null)
		                              .map(item -> item.getId())
		                              .collect(Collectors.toList());
		if (!ids.isEmpty()) {
			baseAttrValueService.lambdaUpdate()
			                    .eq(BaseAttrValue::getAttrId, baseAttrInfo.getId())
			                    .notIn(BaseAttrValue::getId, ids)
			                    .remove();
		}

		CompletableFuture.runAsync(() -> {
			//然后更新更改过的   这里有一个字段就是edit
			attrValueList.stream()
			             .filter(item -> item.getId() != null)
			             .forEach(item -> baseAttrValueService.updateById(item));
		});

		CompletableFuture.runAsync(() -> {
			//然后新增没有出现过的
			List<BaseAttrValue> addnew = attrValueList.stream()
			                                          .filter(item -> item.getId() == null)
			                                          .map(item -> {
				                                          item.setAttrId(baseAttrInfo.getId());
				                                          return item;
			                                          })
			                                          .collect(Collectors.toList());

			baseAttrValueService.saveBatch(addnew);
		});

	}
}




