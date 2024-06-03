package com.sky.service;

import org.apache.commons.collections4.iterators.EmptyOrderedIterator;

import com.sky.dto.EmployeeDTO;
import com.sky.dto.EmployeeLoginDTO;
import com.sky.dto.EmployeePageQueryDTO;
import com.sky.entity.Employee;
import com.sky.result.PageResult;

public interface EmployeeService {

    /**
     * 员工登录
     * @param employeeLoginDTO
     * @return
     */
    Employee login(EmployeeLoginDTO employeeLoginDTO);

    /**
     * 员工注册
     * @param employeeLoginDTO
     * @return
     */
    void save(EmployeeDTO employeeDTO);


    /**
     * 分页查询员工信息
     * @param employeePageQueryDTO
     * @return
     */
    PageResult pageQuery(EmployeePageQueryDTO employeePageQueryDTO);

    /**
     * 启用禁用员工账号
     * @param status
     * @param id
     */
    void startOrStop(Integer status,Long id);


    /**
     * 根据员工ID查询信息
     * @param id
     * @return
     */
    Employee getUserById(Long id);


    /**
     * 修改员工信息
     * @param employeeDTO
     */
    void update(EmployeeDTO employeeDTO);
}
