package com.sky.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sky.dto.SetmealDTO;
import com.sky.result.Result;
import com.sky.service.SetmealService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/admin/setmeal")
@Api
@Slf4j
public class SetmealController {

    @Autowired
    SetmealService setmealService;

    @PostMapping
    @ApiOperation("新增套餐")
    public Result AddSetmeal(@RequestBody SetmealDTO setmealDTO){
        log.info("新增套餐{}",setmealDTO);
        setmealService.addSetmeal(setmealDTO);
        return Result.success();
    }
}