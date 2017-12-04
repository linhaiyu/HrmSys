package com.hr.mapper;

import com.hr.entity.Dept;
import com.hr.mapper.provider.DeptDynaSqlProvider;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;
import java.util.Map;

import static com.hr.common.HrmConstants.DEPT_TABLE;

public interface DeptMapper {
    @Select("select count(*) from " + DEPT_TABLE)
    int getTotalCount();

    @Select(" select * from " + DEPT_TABLE + " ")
    List<Dept> selectAllDept();

    @Select(" select * from " + DEPT_TABLE + " where id = #{id}")
    Dept selectById(int id);

    // 根据id删除部门
    @Delete(" delete from " + DEPT_TABLE + " where id = #{id}")
    void deleteById(Integer id) throws Exception;

    /**  动态SQL */
    // 动态查询
    @SelectProvider(type = DeptDynaSqlProvider.class, method = "selectWithParam")
    List<Dept> selectByPage(Map<String, Object> paramps);

    @SelectProvider(type = DeptDynaSqlProvider.class, method = "count")
    Integer count(Map<String, Object> params);

    // 动态插入部门
    @SelectProvider(type = DeptDynaSqlProvider.class, method = "insertDept")
    void save(Dept dept);

    // 动态修改部门
    @SelectProvider(type = DeptDynaSqlProvider.class, method = "updateDept")
    void update(Dept dept);

}
