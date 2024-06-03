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

    @Delete("delete from dish_flavor where id = #{id}")
    public void deleteById(Long id);
}
