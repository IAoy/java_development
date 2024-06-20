package com.sky.controller.admin;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sky.dto.SetmealDTO;
import com.sky.dto.SetmealPageQueryDTO;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.SetmealService;
import com.sky.vo.SetmealVO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/admin/setmeal")
@Api(tags = "套餐操作")
@Slf4j
public class SetmealController {

    @Autowired
    SetmealService setmealService;


    /**
     * 新增套餐
     * @param setmealDTO
     * @return
     */
    @PostMapping
    @ApiOperation("新增套餐")
    public Result AddSetmeal(@RequestBody SetmealDTO setmealDTO){
        log.info("新增套餐{}",setmealDTO);
        setmealService.addSetmeal(setmealDTO);
        return Result.success();
    }

    /**
     * 套餐分页查询
     * @param setmealPageQueryDTO
     * @return
     */
    @GetMapping("/page")
    @ApiOperation("套餐分页查询")
    public Result<PageResult> setmealPageQuery(SetmealPageQueryDTO setmealPageQueryDTO){
        log.info("套餐分页查询{}",setmealPageQueryDTO);
        PageResult setmeal = setmealService.setmealPageQuery(setmealPageQueryDTO);
        return Result.success(setmeal);
    }


    /**
     * 根据套餐Id查询套餐
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    @ApiOperation("根据套餐ID查询套餐")
    public Result<SetmealVO> getSetmealById(@PathVariable Long id){
        SetmealVO setmeal = setmealService.getSetmealById(id);
        return Result.success(setmeal);
    }


    /**
     * 修改套餐
     * @param setmealDTO
     * @return
     */
    @PutMapping
    @ApiOperation("修改套餐")
    public Result update(@RequestBody SetmealDTO setmealDTO){
        log.info("修改套餐{}",setmealDTO.getId());
        setmealService.update(setmealDTO);
        return Result.success();
    }


    /**
     * 设置起售停售状态
     * @param status
     * @param id
     * @return
     */
    @PostMapping("/status/{status}")
    @ApiOperation("起售停售套餐")
    public Result startOrStop(@PathVariable Long status,Long id){
        setmealService.startOrStop(status,id);
        return Result.success();
    }


    /**
     * 批量删除套餐
     * @param ids
     * @return
     */
    @DeleteMapping
    @ApiOperation("批量删除套餐")
    public Result deleteByIds(@RequestParam String ids){
        List<Integer> setmealIds = Arrays.stream(ids.split(",")).
                                    map(Integer :: parseInt).collect(Collectors.toList());
        setmealService.deleteByIds(setmealIds);
        return Result.success();
    }
}
