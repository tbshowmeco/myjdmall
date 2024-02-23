package com.showmeco.myjdmall.product.controller;

import com.showmeco.myjdmall.common.result.Result;
import com.showmeco.myjdmall.product.entity.BaseAttrInfo;
import com.showmeco.myjdmall.product.entity.BaseAttrValue;
import com.showmeco.myjdmall.product.service.BaseAttrInfoService;
import com.showmeco.myjdmall.product.service.BaseAttrValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 说明:
 *
 * @Author: @showmecoa
 * @Date: 2024/2/22 22:01
 */
@RestController
@RequestMapping("/admin/product")
public class BaseAttrRestController {

	@Autowired
	BaseAttrInfoService baseAttrInfoService;
	@Autowired
	BaseAttrValueService baseAttrValueService;

	@GetMapping("/getAttrValueList/{attrId}")
	public Result getAttrValueList(@PathVariable("attrId") Long attrId) {
		List<BaseAttrValue> list = baseAttrValueService.lambdaQuery()
		                                               .eq(BaseAttrValue::getAttrId, attrId)
		                                               .list();
		return Result.ok(list);
	}

	@PostMapping("/saveAttrInfo")
	public Result saveAttrInfo(@RequestBody BaseAttrInfo baseAttrInfo) {

		//因为这里的保存和修改使用的是同一个接口,需要判断
		if (baseAttrInfo.getId() == null) {
			//说明是保存
			//保存平台属性对应的值
			baseAttrInfoService.saveAttrInfo(baseAttrInfo);
		}
		//说明是修改
		baseAttrInfoService.updateAttrInfo(baseAttrInfo);

		return Result.ok();

	}

	//查各个分类下的所有的平台属性
	@GetMapping("/attrInfoList/{category1Id}/{category2Id}/{category3Id}")
	public Result getAttrInfoListById(@PathVariable("category1Id") Long category1Id,
	                                  @PathVariable("category2Id") Long category2Id,
	                                  @PathVariable("category3Id") Long category3Id) {

		List<BaseAttrInfo> list = baseAttrInfoService.getAttrInfoListById(category1Id, category2Id, category3Id);

		return Result.ok(list);
	}
}
