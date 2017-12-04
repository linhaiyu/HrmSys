package com.hr.mapper;

import com.hr.entity.User;
import com.hr.mapper.provider.UserDynaSqlProvider;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;
import java.util.Map;

import static com.hr.common.HrmConstants.USER_TABLE;

public interface UserMapper {

    // 根据登录名和密码查询用户
    @Select("select id as id, loginName as loginName, password as password, userName as userName, createdDate as createDate, status as status from "
            + USER_TABLE + " where loginName = #{loginname} and password = #{password}")
    User selectByLoginNameAndPassword(@Param("loginname") String name, @Param("password") String password);

    // 根据id 查询用户
    @Select("select * from " + USER_TABLE + " where id = #{id}")
    User selectById(Integer id);

    // 根据id 删除用户
    @Delete(" delete from " + USER_TABLE + " where id = #{id}")
    void deleteById(Integer id);

    @Select("select count(*) from " + USER_TABLE)
    int getTotalCount();

    // PS: 以下为通过动态SQL实现

    // 动态修改用户
    @SelectProvider(type = UserDynaSqlProvider.class, method = "updateUser")
    void update(User user);

    // 动态查询用户
    @SelectProvider(type = UserDynaSqlProvider.class, method = "selectWithParam")
    List<User> selectByPage(Map<String, Object> params);

    // 动态查询，根据参数查询用户总数
    @SelectProvider(type = UserDynaSqlProvider.class, method = "count")
    Integer count(Map<String, Object> params);

    // 动态插入用户
    @SelectProvider(type = UserDynaSqlProvider.class, method = "insertUser")
    void save(User user);

}
