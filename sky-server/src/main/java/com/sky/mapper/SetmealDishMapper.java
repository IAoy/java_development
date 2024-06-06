package com.sky.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import com.sky.entity.SetmealDish;

@Mapper
public interface SetmealDishMapper {
    /**
     * 根据菜品Ids返回套餐Ids
     * @param ids
     * @return
     */
    List<Long> getSetmealIdsByDishIds(List<Long> ids);

    /**
     * 动态添加套餐中的菜品
     * @param setmealDish
     */
    void addSetmealDish(List<SetmealDish> setmealDish);
}
