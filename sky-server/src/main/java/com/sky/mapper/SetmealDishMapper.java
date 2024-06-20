package com.sky.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

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

    /**
     * 通过套餐Id删除菜品
     * @param id
     */
    @Delete("delete from setmeal_dish where setmeal_id = #{id}")
    void deleteById(Long id);

    /**
     * 通过套餐id筛选菜品
     * @param id
     * @return
     */
    List<SetmealDish> getDishesBySetmealId(Long id);

    /**
     * 根据套餐id批量删除菜品
     * @param setmealIds
     */
    void deleteByIds(List<Integer> setmealIds);
}
