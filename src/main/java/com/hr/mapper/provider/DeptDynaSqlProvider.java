package com.hr.mapper.provider;

import com.hr.entity.Dept;
import org.apache.ibatis.jdbc.SQL;

import java.util.Map;

import static com.hr.common.HrmConstants.DEPT_TABLE;

public class DeptDynaSqlProvider {

    // 分页动态查询
    public String selectWithParam(Map<String, Object> params) {
        String sql = new SQL() {
            {
                SELECT("*");
                FROM(DEPT_TABLE);
                if (params.get("dept") != null) {
                    Dept dept = (Dept) params.get("dept");
                    if (dept.getName() != null && !dept.getName().equals("")) {
                        WHERE(" name LIKE CONCAT ('%', #{dept.name}, '%') ");
                    }
                }
            }
        }.toString();

        if (params.get("pageModel") != null) {
            sql += " limit #{pageModel.firstLimitParam}, #{pageModel.pageSize}  ";
        }

        return sql;
    }

    // 动态查询总数
    public String count(Map<String, Object> params) {
        return new SQL() {
            {
                SELECT("count(*)");
                FROM(DEPT_TABLE);
                if (params.get("dept") != null) {
                    Dept dept = (Dept) params.get("dept");
                    if (dept.getName() != null && !dept.getName().equals("")) {
                        WHERE(" name LIKE CONCAT ('%', #{dept.name}, '%') ");
                    }
                }
            }
        }.toString();
    }

    // 动态插入
    public String insertDept(Dept dept) {
        return new SQL() {
            {
                INSERT_INTO(DEPT_TABLE);
                if (dept.getName() != null && !dept.getName().equals("")) {
                    VALUES("name", "#{name}");
                }
                if (dept.getRemark() != null && !dept.getRemark().equals("")) {
                    VALUES("remark", "#{remark}");
                }
            }
        }.toString();
    }

    // 动态更新
    public String updateDept(Dept dept) {
        return new SQL() {
            {
                UPDATE(DEPT_TABLE);
                if (dept.getName() != null) {
                    SET(" name = #{name} ");
                }
                if (dept.getRemark() != null && !dept.getRemark().equals("")) {
                    SET(" remark = #{remark} ");
                }
                WHERE(" id = #{id} ");
            }
        }.toString();
    }

}