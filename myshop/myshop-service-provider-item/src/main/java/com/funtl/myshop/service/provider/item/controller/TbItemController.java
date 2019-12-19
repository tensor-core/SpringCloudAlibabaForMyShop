package com.funtl.myshop.service.provider.item.controller;

import com.funtl.myshop.commons.domain.TbItem;
import com.funtl.myshop.commons.service.TbItemService;
import com.funtl.myshop.commons.web.AbstractBaseController;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "item")
public class TbItemController extends AbstractBaseController<TbItem> {
    @Autowired
    private TbItemService tbItemService;

    @ApiOperation(value = "商品分页查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "页码", required = true, paramType = "path"),
            @ApiImplicitParam(name = "pageSize", value = "笔数", required = true, paramType = "path")
    })
    @GetMapping (value = "page/{pageNum}/{pageSize}")
    public PageInfo<TbItem> page(
            @PathVariable int pageNum,
            @PathVariable int pageSize
    ) {
        PageInfo<TbItem> page = tbItemService.page(null, pageNum, pageSize);
        return page;
    }
}
