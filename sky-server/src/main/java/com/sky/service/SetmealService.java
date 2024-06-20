package com.sky.service;

import java.util.List;

import org.springframework.web.bind.annotation.RequestParam;

import com.sky.dto.SetmealDTO;
import com.sky.dto.SetmealPageQueryDTO;
import com.sky.result.PageResult;
import com.sky.vo.SetmealVO;

public interface SetmealService {

    /**
     * 新增套餐
     * @param setmealDTO
     */
    public void addSetmeal(SetmealDTO setmealDTO);

    /**
     * 套餐分页查询
     * @param setmealPageQueryDTO
     * @return
     */
    public PageResult setmealPageQuery(SetmealPageQueryDTO setmealPageQueryDTO);

    /**
     * 更新套餐
     * @param setmealDTO
     */
    public void update(SetmealDTO setmealDTO);

    /**
     * 通过套餐Id查询套餐
     * @param id
     * @return
     */
    public SetmealVO getSetmealById(Long id);

    /**
     * 修改套餐起售停售状态
     * @param status
     * @param id
     */
    public void startOrStop(Long status,Long id);

    /**
     * 批量删除套餐
     * @param setmealIds
     */
    public void deleteByIds(List<Integer> setmealIds);
}
