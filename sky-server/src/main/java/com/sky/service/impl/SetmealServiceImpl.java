package com.sky.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sky.dto.SetmealDTO;
import com.sky.entity.Setmeal;
import com.sky.entity.SetmealDish;
import com.sky.mapper.SetmealDishMapper;
import com.sky.mapper.SetmealMapper;
import com.sky.service.SetmealService;

@Service
public class SetmealServiceImpl implements SetmealService{

    @Autowired
    SetmealMapper setmealMapper;
    @Autowired
    SetmealDishMapper setmealDishMapper;


    /**
     * 新增套餐
     * @param setmealDTO
     */
    @Transactional
    public void addSetmeal(SetmealDTO setmealDTO){

        /**
         * 新增套餐
         */
        Setmeal setmeal = new Setmeal();
        BeanUtils.copyProperties(setmealDTO, setmeal);
        setmealMapper.addSetmeal(setmeal);
        Long setmealId = setmeal.getId();

        /**
         * 新增套餐中的菜品
         */
        List<SetmealDish> dishes = setmealDTO.getSetmealDishes();
        List<SetmealDish> setmealDishes = new ArrayList();
        for(SetmealDish dish: dishes){
            SetmealDish temp = new SetmealDish();
            BeanUtils.copyProperties(dish, temp);
            temp.setSetmealId(setmealId);
            setmealDishes.add(temp);
        }
        setmealDishMapper.addSetmealDish(setmealDishes);
    }

}
