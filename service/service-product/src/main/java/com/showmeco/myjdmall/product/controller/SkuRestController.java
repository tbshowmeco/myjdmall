package com.showmeco.myjdmall.product.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.showmeco.myjdmall.common.result.Result;
import com.showmeco.myjdmall.product.entity.SkuInfo;
import com.showmeco.myjdmall.product.service.SkuInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 说明:
 *
 * @Author: @showmeco
 * @Date: 2024/2/23 1:15
 */
@RequestMapping("/admin/product")
@RestController
public class SkuRestController {


	@Autowired
	SkuInfoService skuInfoService;

	@GetMapping("/list/{pnumber}/{psize}")
	public Result getSkuPage(@PathVariable("pnumber") Long pnumber,
	                         @PathVariable("psize") Long psize) {
		Page<SkuInfo> page = skuInfoService.lambdaQuery()
		                                   .page(new Page<>(pnumber, psize));

		return Result.ok(page);
	}

	@PostMapping("/saveSkuInfo")
	public Result saveSkuInfo(@RequestBody SkuInfo skuInfo) {
		skuInfoService.saveSkuInfo(skuInfo);

		return Result.ok();
	}


	@GetMapping("/onSale/{skuId}")
	public Result onSale(@PathVariable("skuId") Long skuId) {

		skuInfoService.lambdaUpdate()
		              .eq(SkuInfo::getId, skuId)
		              .set(SkuInfo::getIsSale, 1)
		              .update();
		return Result.ok();
	}

	@GetMapping("/cancelSale/{skuId}")
	public Result cancelSale(@PathVariable("skuId") Long skuId) {

		skuInfoService.lambdaUpdate()
		              .eq(SkuInfo::getId, skuId)
		              .set(SkuInfo::getIsSale, 0)
		              .update();
		return Result.ok();
	}
}
