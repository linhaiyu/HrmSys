package com.hr.mapper.provider;

import com.hr.entity.Train;
import org.apache.ibatis.jdbc.SQL;

import java.util.Map;

import static com.hr.common.HrmConstants.TRAIN_TABLE;

public class TrainDynaSqlProvider {

    public String selectWithParams(Map<String, Object> params) {
        String sql = new SQL() {
            {
                SELECT("*");
                FROM(TRAIN_TABLE);
                if (params.get("train") != null) {
                    Train train = (Train) params.get("train");
                    if (train.getTitle() != null && !train.getTitle().equals("")) {
                        WHERE(" title LIKE CONCAT ('%', #{train.title}, '%') ");
                    }

                    if (train.getRemark() != null && !train.getRemark().equals("")) {
                        WHERE(" remark LIKE CONCAT ('%', #{train.remark}, '%') ");
                    }

                    if(train.getEmployee() != null && train.getEmployee().getId() != null && train.getEmployee().getId() != 0){
                        WHERE(" employee_id = #{train.employee.id} ");
                    }
                }
            }
        }.toString();

        if (params.get("pageModel") != null) {
            sql += " limit #{pageModel.firstLimitParam} , #{pageModel.pageSize}  ";
        }

        return sql;
    }

    public String count(Map<String, Object> params) {
        return new SQL() {
            {
                SELECT("count(*)");
                FROM(TRAIN_TABLE);
                if (params.get("train") != null) {
                    Train train = (Train) params.get("train");

                    if (train.getRemark() != null && !train.getRemark().equals("")) {
                        WHERE(" remark LIKE CONCAT ('%', #{train.remark}, '%') ");
                    }

                    if (train.getTitle() != null && !train.getTitle().equals("")) {
                        WHERE(" title LIKE CONCAT ('%', #{train.title}, '%') ");
                    }
                }
            }
        }.toString();
    }

    public String insertTrain(Train train){
        return new SQL() {
            {
                INSERT_INTO(TRAIN_TABLE);

                if (train.getTitle() != null && !train.getTitle().equals("")) {
                    VALUES("title", "#{title}");
                }

                if (train.getRemark() != null && !train.getRemark().equals("")) {
                    VALUES("remark", "#{remark}");
                }

                if (train.getEmployee()!= null) {
                    VALUES("employee_id", "#{employee.id}");
                }
            }
        }.toString();
    }

    public String updateTrain(Train train) {
        return new SQL() {
            {
                UPDATE(TRAIN_TABLE);

                if (train.getTitle() != null && !train.getTitle().equals("")) {
                    SET("title = #{title}");
                }

                if (train.getRemark() != null && !train.getRemark().equals("")) {
                    SET("remark = #{remark}");
                }

                if(train.getEmployee() != null && train.getEmployee().getId() != null && train.getEmployee().getId() != 0){
                    SET(" employee_id = #{employee.id} ");
                }

                WHERE(" id = #{id} ");
            }
        }.toString();
    }
}
