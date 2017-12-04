package com.hr.mapper.provider;

import com.hr.entity.User;
import org.apache.ibatis.jdbc.SQL;

import java.util.Map;
import static com.hr.common.HrmConstants.USER_TABLE;

/**
 * 动态SQL提供类
 */
public class UserDynaSqlProvider {

    // PS： 注意数据库表字段的名称 和 实体类属性的名称

    // 动态查询
    public String selectWithParam(Map<String, Object> params) {
        String sql = new SQL() {
            {
                SELECT("id as id, loginName as loginName, password as password, userName as userName, createdDate as createDate, status as status ");
                FROM(USER_TABLE);

                if(params.get("user") != null) {
                    User user = (User)params.get("user");
                    if (user.getUserName() != null && !user.getUserName().equals("")) {
                        WHERE(" userName LIKE CONCAT ('%', #{user.userName}, '%') ");
                    }

                    if (user.getStatus() != null && !user.getStatus().equals("")) {
                        WHERE(" status LIKE CONCAT ('%', #{user.status}, '%') ");
                    }
                }
            }
        }.toString();

        if (params.get("pageModel") != null) {
            // 从 pageModel.firstLimitParam 开始，取pageModel.pageSize 条数据
            sql += " limit #{pageModel.firstLimitParam}, #{pageModel.pageSize} ";
        }

        return sql;
    }

    // 动态查询总数量
    public String count(Map<String, Object> params) {
        return new SQL() {
            {
                SELECT("count(*)");
                FROM(USER_TABLE);
                if (params.get("user") != null) {
                    User user = (User)params.get("user");
                    if(user.getUserName() != null && !user.getUserName().equals("")) {
                        WHERE(" userName LIKE CONCAT('%', #{user.userName}, '%') ");
                    }

                    if (user.getStatus() != null && !user.getStatus().equals("")) {
                        WHERE(" status LIKE CONCAT('%', #{user.status}, '%') ");
                    }
                }
            }
        }.toString();
    }

    // 动态插入
    public String insertUser(User user) {
        return new SQL() {
            {
                INSERT_INTO(USER_TABLE);
                if (user.getUserName() != null && !user.getUserName().equals("")) {
                    VALUES("userName", "#{userName}");
                }

                if (user.getStatus() != null && !user.getStatus().equals("")) {
                    VALUES("status", "#{status}");
                }

                if (user.getLoginName() != null && !user.getLoginName().equals("")) {
                    VALUES("loginName", "#{loginName}");
                }

                if (user.getPassword() != null && !user.getPassword().equals("")) {
                    VALUES("password", "#{password}");
                }
            }
        }.toString();
    }

    // 动态更新
    public String updateUser(User user) {
        return new SQL() {
            {
                UPDATE(USER_TABLE);
                if(user.getUserName() != null ) {
                    SET(" userName = #{userName} ");
                }
                if(user.getLoginName() != null ) {
                    SET(" loginName = #{loginName} ");
                }
                if(user.getPassword() != null ) {
                    SET(" password = #{password} ");
                }
                if(user.getStatus() != null ) {
                    SET(" status = #{status} ");
                }
                if(user.getCreateDate() != null ) {
                    SET(" createdDate = #{createDate} ");
                }

                WHERE(" id = #{id} ");
            }
        }.toString();
    }

}
