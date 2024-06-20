package com.sky.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.github.pagehelper.Page;
import com.sky.annotation.AutoFill;
import com.sky.dto.SetmealPageQueryDTO;
import com.sky.entity.Setmeal;
import com.sky.enumeration.OperationType;
import com.sky.vo.SetmealVO;

@Mapper
public interface SetmealMapper {

    /**
     * 根据分类id查询套餐的数量
     * @param id
     * @return
     */
    @Select("select count(id) from setmeal where category_id = #{categoryId}")
    Integer countByCategoryId(Long id);

    @AutoFill(OperationType.INSERT)
    @Insert("insert into setmeal(category_id,name,price,status,description,image,create_time,update_time,create_user,update_user)"
    + " values "
    +"(#{categoryId},#{name},#{price},#{status},#{description},#{image},#{createTime},#{updateTime},#{createUser},#{updateUser})")
    @Options(useGeneratedKeys = true , keyProperty = "id")
    void addSetmeal(Setmeal setmeal);


    /**
     * 套餐分页查询
     * @param setmealPageQueryDTO
     * @return
     */
    Page<Setmeal> pageQuery(SetmealPageQueryDTO setmealPageQueryDTO);


    /**
     * 修改套餐信息
     * @param setmeal
     */
    @AutoFill(OperationType.UPDATE)
    void update(Setmeal setmeal);

    /**
     * 根据套餐ID查询套餐
     * @param id
     * @return
     */
    @Select("Select * from setmeal where id = #{id}")
    SetmealVO getSetmealById(Long id);

    /**
     * 更新套餐销售状态
     * @param status
     * @param id
     */
    @Update("update setmeal set status = #{status} where id = #{id}")
    void startOrStop(Long status,Long id);

    /**
     * 批量删除套餐
     * @param setmealIds
     */
    void deleteByIds(List<Integer> setmealIds);
}
