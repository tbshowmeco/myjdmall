package com.showmeco.myjdmall.product.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.showmeco.myjdmall.common.result.Result;
import com.showmeco.myjdmall.product.entity.SpuImage;
import com.showmeco.myjdmall.product.entity.SpuInfo;
import com.showmeco.myjdmall.product.entity.SpuSaleAttr;
import com.showmeco.myjdmall.product.service.SpuImageService;
import com.showmeco.myjdmall.product.service.SpuInfoService;
import com.showmeco.myjdmall.product.service.SpuSaleAttrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 说明:
 *
 * @Author: @showmeco
 * @Date: 2024/2/8 10:03
 */
@RestController
@RequestMapping("/admin/product")
public class SpuRestController {

	@Autowired
	SpuInfoService spuInfoService;

	@Autowired
	SpuImageService spuImageService;
	@Autowired
	SpuSaleAttrService spuSaleAttrService;

	@GetMapping("/spuSaleAttrList/{spuId}")
	public Result getSpuSaleAttrList(@PathVariable("spuId") Long spuId) {

		List<SpuSaleAttr> list = spuSaleAttrService.getSpuSaleAttrList(spuId);

		return Result.ok(list);
	}

	@GetMapping("/spuImageList/{spuId}")
	public Result getSpuImageList(@PathVariable("spuId") Long spuId) {

		List<SpuImage> list = spuImageService.lambdaQuery()
		                                           .eq(SpuImage::getSpuId, spuId)
		                                           .list();
		return Result.ok(list);
	}


	@GetMapping("/{pnumber}/{psize}")
	public Result getSpuInfo(@PathVariable("pnumber") Long pnumber,
	                         @PathVariable("psize") Long psize,
	                         @RequestParam("category3Id") Long c3Id) {

		//分页获取spuinfo
		Page<SpuInfo> page = spuInfoService.lambdaQuery()
		                                   .eq(SpuInfo::getCategory3Id, c3Id)
		                                   .page(new Page<SpuInfo>(pnumber, psize));
		return Result.ok(page);
	}


	@PostMapping("/saveSpuInfo")
	public Result saveSpuInfo(@RequestBody SpuInfo spuInfo) {


		spuInfoService.saveSpuInfo(spuInfo);


		return Result.ok();
	}


}
