package com.sky.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import com.sky.entity.DishFlavor;

@Mapper

public interface DishFlavorMapper {

    /**
     * 菜品批量插入
     * @param flavors
     */
    public void insertBatch(List<DishFlavor> flavors);

    /**
     * 删除相关联菜品的口味
     * @param id
     */
    @Delete("delete from dish_flavor where dish_id = #{id}")
    public void deleteById(Long id);

    /**
     * 一次性批量删除多个菜品的口味
     * @param ids
     */
    public void deleteByIds(List<Long> ids);

}
