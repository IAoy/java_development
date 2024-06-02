package com.sky.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import com.sky.entity.DishFlavor;

@Mapper

public interface DishFlavorMapper {

    public void insertBatch(List<DishFlavor> flavors);
}
