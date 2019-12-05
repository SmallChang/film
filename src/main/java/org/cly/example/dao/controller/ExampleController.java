package org.cly.example.dao.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController//返回json数据
@RequestMapping(value = "/v1/example/")
@Api("我描述了该Controller是做什么用的")
public class ExampleController {

    @ApiOperation(value = "测试swagger Value",notes = "测试swagger Note")
    @ApiImplicitParam(name = "test" ,value = "入参str", paramType = "query",required = true,dataType = "string")
    @RequestMapping(value = "test")
    public String test(String test){
        System.out.println("test"+test);
        return "test"+test;
    }

}
