package com.hr.mapper;

import com.hr.entity.Employee;
import com.hr.mapper.provider.EmployeeDynaSqlProvider;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;
import java.util.Map;

import static com.hr.common.HrmConstants.EMPLOYEE_TABLE;

public interface EmployeeMapper {

    @Delete(" delete from " + EMPLOYEE_TABLE + " where id = #{id} ")
    void deleteById(Integer id);

    @Select(" select * from " + EMPLOYEE_TABLE + " where id = #{id}")
    @Results({
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "birthday", property = "birthday", javaType = java.util.Date.class),
            @Result(column = "dept_id", property = "dept", one = @One(select = "com.hr.mapper.DeptMapper.selectById", fetchType = FetchType.EAGER)),
            @Result(column = "job_id", property = "job", one = @One(select = "com.hr.mapper.JobMapper.selectById", fetchType = FetchType.EAGER))
    })
    Employee selectById(Integer id);

    @Select("select count(*) from " + EMPLOYEE_TABLE)
    int getTotalCount();

    @Select("select * from " + EMPLOYEE_TABLE + " ")
    List<Employee> selectAllEmployee();


    /** 动态 SQL */

    @SelectProvider(type = EmployeeDynaSqlProvider.class, method = "count")
    Integer count(Map<String, Object> params);

    @SelectProvider(type = EmployeeDynaSqlProvider.class, method = "selectWithParam")
    @Results({
            @Result(id = true, column = "id", property = "id"),
//            @Result(column = "name", property = "name"),
//            @Result(column = "cardId", property = "cardId"),
//            @Result(column = "address", property = "address"),
//            @Result(column = "phone", property = "phone"),
//            @Result(column = "qq", property = "qq"),
//            @Result(column = "email", property = "email"),
            @Result(column = "birthday", property = "birthday", javaType = java.util.Date.class),
//            @Result(column = "education", property = "education"),
//            @Result(column = "remark", property = "remark"),
            @Result(column = "dept_id", property = "dept", one = @One(select = "com.hr.mapper.DeptMapper.selectById", fetchType = FetchType.EAGER)),
            @Result(column = "job_id", property = "job", one = @One(select = "com.hr.mapper.JobMapper.selectById", fetchType = FetchType.EAGER))
    })
    List<Employee> selectByPage(Map<String, Object> params);

    @SelectProvider(type = EmployeeDynaSqlProvider.class, method = "addEmployee")
    void save(Employee employee);

    @SelectProvider(type = EmployeeDynaSqlProvider.class, method = "updateEmployee")
    void update(Employee employee);

}
