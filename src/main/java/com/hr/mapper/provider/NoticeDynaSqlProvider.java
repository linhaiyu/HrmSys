package com.hr.mapper.provider;

import com.hr.entity.Notice;
import org.apache.ibatis.jdbc.SQL;

import java.util.Map;

import static com.hr.common.HrmConstants.NOTICE_TABLE;

public class NoticeDynaSqlProvider {

    public String selectWithParam(Map<String, Object> params) {
        String sql = new SQL() {
            {
                SELECT("*");
                FROM(NOTICE_TABLE);
                if (params.get("notice") != null) {
                    Notice notice = (Notice)params.get("notice");
                    if (notice.getTitle() != null && !notice.getTitle().equals("")) {
                        WHERE(" title LIKE CONCAT ('%', #{notice.title}, '%') ");
                    }

                    if (notice.getContent() != null && !notice.getContent().equals("")) {
                        WHERE(" content LIKE CONCAT ('%', #{notice.content}, '%') ");
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
                FROM(NOTICE_TABLE);
                if(params.get("notice") != null){
                    Notice notice = (Notice)params.get("notice");
                    if(notice.getTitle() != null && !notice.getTitle().equals("")){
                        WHERE("  title LIKE CONCAT ('%',#{notice.title},'%') ");
                    }
                    if(notice.getContent() != null && !notice.getContent().equals("")){
                        WHERE("  content LIKE CONCAT ('%',#{notice.content},'%') ");
                    }
                }

            }
        }.toString();
    }

    public String insertNotice(Notice notice) {
        return new SQL() {
            {
                INSERT_INTO(NOTICE_TABLE);

                if (notice.getTitle() != null && !notice.getTitle().equals("")) {
                    VALUES("title", "#{title}");
                }

                if (notice.getContent() != null && !notice.getContent().equals("")) {
                    VALUES("content", "#{content}");
                }

                if (notice.getUser() != null && notice.getUser().getId() != null) {
                    VALUES("userId", "#{user.id}");
                }
            }
        }.toString();
    }


    public String updateNotice(Notice notice) {
        return new SQL() {
            {
                UPDATE(NOTICE_TABLE);
                if (notice.getTitle() != null && !notice.getTitle().equals("")) {
                    SET("title = #{title}");
                }

                if (notice.getContent() != null && !notice.getContent().equals("")) {
                    SET("content = #{content}");
                }

                if (notice.getUser() != null && notice.getUser().getId() != null) {
                    SET("userId = #{user.id}");
                }

                WHERE(" id = #{id}");
            }
        }.toString();
    }
}
