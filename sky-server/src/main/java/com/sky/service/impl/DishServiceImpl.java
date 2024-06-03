package com.sky.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sky.constant.MessageConstant;
import com.sky.constant.StatusConstant;
import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.entity.Dish;
import com.sky.entity.DishFlavor;
import com.sky.exception.DeletionNotAllowedException;
import com.sky.mapper.DishFlavorMapper;
import com.sky.mapper.DishMapper;
import com.sky.mapper.SetmealDishMapper;
import com.sky.result.PageResult;
import com.sky.service.DishService;
import com.sky.vo.DishVO;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class DishServiceImpl implements DishService{

    @Autowired
    DishMapper dishMapper;
    @Autowired
    DishFlavorMapper dishFlavorMapper;
    @Autowired
    SetmealDishMapper setMealDishMapper;

    /**
     * 添加菜品
     */
    @Transactional
    public void saveWithFlavor(DishDTO dishDTO){

        // 向菜品表中插入1条数据
        Dish dish = new Dish();
        BeanUtils.copyProperties(dishDTO, dish);
        dishMapper.insert(dish);

        Long dishID = dish.getId();

        // 向口味表中插入n条数据
        List<DishFlavor> flavors = dishDTO.getFlavors();
        if(flavors != null && flavors.size()>0){
            flavors.forEach(flavor -> {
                flavor.setDishId(dishID);
            });
            dishFlavorMapper.insertBatch(flavors);
        }
    };


    /**
     * 菜品分页查询
     */
    public PageResult pageQuery(DishPageQueryDTO dishPageQueryDTO){
        PageHelper.startPage(dishPageQueryDTO.getPage(),dishPageQueryDTO.getPageSize());
        Page<DishVO> page = dishMapper.pageQuery(dishPageQueryDTO);
        return new PageResult(page.getTotal(),page.getResult());
    }


    /**
     * 批量删除菜品
     */
    @Transactional
    public void deleteBatch(List<Long> ids){
        //判断是否可以删除菜品---是否是停售状态、若是则可删除、否则报异常
        for(Long id : ids){
            Dish dish = dishMapper.getDishById(id);
            if(dish.getStatus() == StatusConstant.ENABLE){
                throw new DeletionNotAllowedException(MessageConstant.DISH_ON_SALE);
            }
        }
        //判断是否可以删除菜品---是否在套餐内、是则报异常
        for(Long id : ids){
            List<Long> setMealList = setMealDishMapper.getSetmealIdsByDishIds(ids);
            if(setMealList != null && setMealList.size() > 0){
                throw new DeletionNotAllowedException(MessageConstant.DISH_BE_RELATED_BY_SETMEAL);
            }
        }
        //根据id删除菜品
        for(Long id : ids){
            dishMapper.deleteById(id);
            dishFlavorMapper.deleteById(id);
        }
    }
}
