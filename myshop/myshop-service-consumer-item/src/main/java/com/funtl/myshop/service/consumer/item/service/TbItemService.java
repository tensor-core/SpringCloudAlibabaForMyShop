package com.funtl.myshop.service.consumer.item.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "myshop-service-provider-item")
public interface TbItemService {

    @GetMapping(value = "/item/page/{pageNum}/{pageSize}")
    String page(@PathVariable(name = "pageNum") int pageNum, @PathVariable(name = "pageSize") int pageSize);
}
