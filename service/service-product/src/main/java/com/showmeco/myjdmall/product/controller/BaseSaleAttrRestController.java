package com.showmeco.myjdmall.product.controller;

import com.showmeco.myjdmall.common.result.Result;
import com.showmeco.myjdmall.product.entity.BaseSaleAttr;
import com.showmeco.myjdmall.product.service.BaseSaleAttrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 说明:
 *
 * @Author: @showmeco
 * @Date: 2024/2/23 0:35
 */
@RestController
@RequestMapping("/admin/product")
public class BaseSaleAttrRestController {

	@Autowired
	BaseSaleAttrService baseSaleAttrService;

	@GetMapping("/baseSaleAttrList")
	public Result getBaseSaleAttrList() {

		List<BaseSaleAttr> list = baseSaleAttrService.list();

		return Result.ok(list);
	}
}
