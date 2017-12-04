package com.hr.mapper.provider;

import com.hr.entity.Employee;
import org.apache.ibatis.jdbc.SQL;

import java.util.Map;

import static com.hr.common.HrmConstants.EMPLOYEE_TABLE;

public class EmployeeDynaSqlProvider {
    public String selectWithParam(Map<String, Object> params) {
        String sql = new SQL() {
            {
                SELECT("*");
                FROM(EMPLOYEE_TABLE);
                Employee employee = (Employee)params.get("employee");
                if(employee.getDept() != null && employee.getDept().getId() != null && employee.getDept().getId() != 0){
                    WHERE(" dept_id = #{employee.dept.id} ");
                }
                if(employee.getJob() != null && employee.getJob().getId() != null && employee.getJob().getId() != 0){
                    WHERE(" job_id = #{employee.job.id} ");
                }
                if(employee.getName() != null && !employee.getName().equals("")){
                    WHERE("  name LIKE CONCAT ('%',#{employee.name},'%') ");
                }
                if(employee.getPhone() != null && !employee.getPhone().equals("")){
                    WHERE(" phone LIKE CONCAT ('%',#{employee.phone},'%') ");
                }
                if(employee.getCardId() != null && !employee.getCardId().equals("") ){
                    WHERE(" cardId LIKE CONCAT ('%',#{employee.cardId},'%') ");
                }
                if(employee.getSex()!= null && employee.getSex() != 0){
                    WHERE("sex = #{employee.sex}");
                }
            }
        }.toString();

        if (params.get("pageModel") != null) {
            sql += " limit #{pageModel.firstLimitParam} , #{pageModel.pageSize} ";
        }
        return sql;
    }

    public String count(Map<String, Object> params) {
        return new SQL() {
            {
                SELECT("count(*)");
                FROM(EMPLOYEE_TABLE);
                if(params.get("employee") != null){
                    Employee employee = (Employee)params.get("employee");
                    if(employee.getDept() != null && employee.getDept().getId() != null && employee.getDept().getId() != 0){
                        WHERE(" dept_id = #{employee.dept.id} ");
                    }
                    if(employee.getJob() != null && employee.getJob().getId() != null && employee.getJob().getId() != 0){
                        WHERE(" job_id = #{employee.job.id} ");
                    }
                    if(employee.getName() != null && !employee.getName().equals("")){
                        WHERE("  name LIKE CONCAT ('%',#{employee.name},'%') ");
                    }
                    if(employee.getPhone() != null && !employee.getPhone().equals("")){
                        WHERE(" phone LIKE CONCAT ('%',#{employee.phone},'%') ");
                    }
                    if(employee.getCardId() != null && !employee.getCardId().equals("") ){
                        WHERE(" cardId LIKE CONCAT ('%',#{employee.cardId},'%') ");
                    }
                    if(employee.getSex()!= null && employee.getSex() != 0){
                        WHERE("sex = #{employee.sex}");
                    }
                }
            }

        }.toString();
    }

    public String addEmployee(Employee employee) {
        return new SQL() {
            {
                INSERT_INTO(EMPLOYEE_TABLE);
                if(employee.getName() != null){
                    VALUES("name", "#{name}");
                }
                if(employee.getCardId() != null){
                    VALUES("cardId", "#{cardId}");
                }
                if(employee.getAddress()!= null){
                    VALUES("address", "#{address}");
                }
                if(employee.getPhone()!= null){
                    VALUES("phone", "#{phone}");
                }
                if(employee.getQq()!= null){
                    VALUES("qq", "#{qq}");
                }
                if(employee.getEmail()!= null){
                    VALUES("email", "#{email}");
                }
                if(employee.getSex()!= null){
                    VALUES("sex", "#{sex}");
                }
                if(employee.getBirthday()!= null){
                    VALUES("birthday", "#{birthday}");
                }
                if(employee.getEducation()!= null){
                    VALUES("education", "#{education}");
                }
                if(employee.getRemark()!= null){
                    VALUES("remark", "#{remark}");
                }
                if(employee.getDept()!= null){
                    VALUES("dept_id", "#{dept.id}");
                }
                if(employee.getJob()!= null){
                    VALUES("job_id", "#{job.id}");
                }
            }
        }.toString();
    }

    public String updateEmployee(Employee employee) {
        return new SQL(){
            {
                UPDATE(EMPLOYEE_TABLE);
                if(employee.getName() != null){
                    SET(" name = #{name} ");
                }
                if(employee.getCardId() != null){
                    SET(" cardId = #{cardId} ");
                }
                if(employee.getAddress()!= null){
                    SET(" address = #{address} ");
                }
                if(employee.getPhone()!= null){
                    SET(" phone = #{phone} ");
                }
                if(employee.getQq()!= null){
                    SET(" qq = #{qq} ");
                }
                if(employee.getEmail()!= null){
                    SET(" email = #{email} ");
                }
                if(employee.getSex()!= null){
                    SET(" sex = #{sex} ");
                }
                if(employee.getBirthday()!= null){
                    SET(" birthday = #{birthday} ");
                }
                if(employee.getEducation()!= null){
                    SET(" education = #{education} ");
                }
                if(employee.getRemark()!= null){
                    SET(" remark = #{remark} ");
                }
                if(employee.getDept()!= null){
                    SET(" dept_id = #{dept.id} ");
                }
                if(employee.getJob()!= null){
                    SET(" job_id = #{job.id} ");
                }
                WHERE(" id = #{id} ");
            }
        }.toString();
    }

}
