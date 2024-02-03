package com.showmeco.myjdmall.product.controller;

import com.showmeco.myjdmall.common.result.Result;
import com.showmeco.myjdmall.product.entity.BaseCategory1;
import com.showmeco.myjdmall.product.entity.BaseCategory2;
import com.showmeco.myjdmall.product.entity.BaseCategory3;
import com.showmeco.myjdmall.product.service.BaseCategory1Service;
import com.showmeco.myjdmall.product.service.BaseCategory2Service;
import com.showmeco.myjdmall.product.service.BaseCategory3Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 说明:
 *
 * @Author: @showmeco
 * @Date: 2024/2/3 2:21
 */
@RestController
public class CategoryRestController {


    @Resource
    private BaseCategory1Service baseCategory1Service;

    @Resource
    private BaseCategory2Service baseCategory2Service;

    @Resource
    private BaseCategory3Service baseCategory3Service;

    @GetMapping("/admin/product/getCategory1")
    public Result<List<BaseCategory1>> getAllCategory1() {
        List<BaseCategory1> list = baseCategory1Service.list();
        return Result.ok(list);
    }


    @GetMapping("/admin/product/getCategory2/{category1Id}")
    public Result<List<BaseCategory2>> getAllCategory2ByCategory1Id(@PathVariable("category1Id") Long category1Id) {
        List<BaseCategory2> list = baseCategory2Service.lambdaQuery().eq(BaseCategory2::getCategory1Id, category1Id).list();
        return Result.ok(list);
    }

    @GetMapping("/admin/product/getCategory3/{category2Id}")
    public Result<List<BaseCategory3>> getAllCategory3ByCategory2Id(@PathVariable("category2Id") Long category2Id) {
        List<BaseCategory3> list = baseCategory3Service.lambdaQuery().eq(BaseCategory3::getCategory2Id, category2Id).list();
        return Result.ok(list);
    }
}
