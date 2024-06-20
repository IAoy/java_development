package com.sky.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sky.dto.SetmealDTO;
import com.sky.dto.SetmealPageQueryDTO;
import com.sky.entity.Setmeal;
import com.sky.entity.SetmealDish;
import com.sky.mapper.SetmealDishMapper;
import com.sky.mapper.SetmealMapper;
import com.sky.result.PageResult;
import com.sky.service.SetmealService;
import com.sky.vo.SetmealVO;

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

    /**
     * 套餐分页查询
     */
    public PageResult setmealPageQuery(SetmealPageQueryDTO setmealPageQueryDTO){
        PageHelper.startPage(setmealPageQueryDTO.getPage(),setmealPageQueryDTO.getPageSize());

        Page<Setmeal> page = setmealMapper.pageQuery(setmealPageQueryDTO);

        long total = page.getTotal();

        List<Setmeal> records = page.getResult();

        return new PageResult(total,records);
    }

    @Transactional
    public void update(SetmealDTO setmealDTO){
        Setmeal setmeal = new Setmeal();
        BeanUtils.copyProperties(setmealDTO, setmeal);
        setmealMapper.update(setmeal);

        List<SetmealDish> dishes = setmealDTO.getSetmealDishes();
        for(SetmealDish dish : dishes){
            dish.setSetmealId(setmealDTO.getId());
        }

        setmealDishMapper.deleteById(setmealDTO.getId());

        if(dishes != null && dishes.size()>0){
            dishes.forEach(dish -> {
                dish.setSetmealId(setmealDTO.getId());
            });
            setmealDishMapper.addSetmealDish(dishes);
        }
    }

    public SetmealVO getSetmealById(Long id){

        SetmealVO setmealVO = setmealMapper.getSetmealById(id);

        List<SetmealDish> setmealDishes = setmealDishMapper.getDishesBySetmealId(id);

        setmealVO.setSetmealDishes(setmealDishes);

        return setmealVO;
    }

    
    public void startOrStop(Long status,Long id){
        setmealMapper.startOrStop(status,id);
    }


    @Transactional
    public void deleteByIds(List<Integer> setmealIds){
        
        setmealMapper.deleteByIds(setmealIds);

        setmealDishMapper.deleteByIds(setmealIds);
    }
}
