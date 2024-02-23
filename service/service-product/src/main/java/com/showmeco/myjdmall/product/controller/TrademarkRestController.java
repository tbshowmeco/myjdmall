package com.showmeco.myjdmall.product.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.showmeco.myjdmall.common.result.Result;
import com.showmeco.myjdmall.product.entity.BaseTrademark;
import com.showmeco.myjdmall.product.mapper.BaseTrademarkMapper;
import com.showmeco.myjdmall.product.service.BaseTrademarkService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 说明:
 *
 * @Author: @showmeco
 * @Date: 2024/2/4 15:18
 */
@RestController
@RequestMapping("/admin/product")
public class TrademarkRestController {

	@Resource
	BaseTrademarkService baseTrademarkService;

	@Resource
	BaseTrademarkMapper baseTrademarkMapper;

	@GetMapping("/baseTrademark/get/{id}")
	public Result getTrademarkById(@PathVariable("id") Long id) {
		BaseTrademark baseTrademark = baseTrademarkMapper.selectById(id);
		return Result.ok(baseTrademark);
	}


	@GetMapping("/baseTrademark/{page}/{limit}")
	public Result getTrademarkPage(@PathVariable("page") Long page, @PathVariable("limit") Long limit) {
		Page<BaseTrademark> page1 = new Page<>(page, limit);
		Page<BaseTrademark> result = baseTrademarkService.lambdaQuery()
		                                                  .page(page1);
		return Result.ok(result);
	}

	@PostMapping("/baseTrademark/save")
	public Result addTrademark(@RequestBody BaseTrademark trademark) {
		boolean saved = baseTrademarkService.save(trademark);
		return saved ? Result.ok() : Result.fail();
	}


	@PutMapping("/baseTrademark/update")
	public Result updateTrademark(@RequestBody BaseTrademark trademark) {
		baseTrademarkService.myUpdate(trademark);
		return  Result.ok() ;
	}



	@DeleteMapping("/baseTrademark/remove/{id}")
	public Result deleteTrademark(@PathVariable("id") Long id) {
	  baseTrademarkService.removeTrademark(id);
		return   Result.ok() ;
	}

	@GetMapping("/baseTrademark/getTrademarkList")
	public Result getTrademarkList() {

		List<BaseTrademark> list = baseTrademarkService.list();
		return Result.ok(list);
	}
}
