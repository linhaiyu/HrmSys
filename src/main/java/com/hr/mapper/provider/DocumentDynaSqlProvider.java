package com.hr.mapper.provider;

import com.hr.entity.Document;
import org.apache.ibatis.jdbc.SQL;

import java.util.Map;

import static com.hr.common.HrmConstants.DOCUMENT_TABLE;

public class DocumentDynaSqlProvider {

    public String selectWithParam(Map<String, Object> params) {
        String sql = new SQL() {
            {
                SELECT("*");
                FROM(DOCUMENT_TABLE);
                if (params.get("document") != null) {
                    Document document = (Document) params.get("document");

                    if (document.getTitle() != null && !document.getTitle().equals("")) {
                        WHERE(" title LIKE CONCAT ('%', #{document.title},'%') ");
                    }
                }
            }
        }.toString();

        if (params.get("pageModel") != null) {
            sql += " limit #{pageModel.firstLimitParam}, #{pageModel.pageSize} ";
        }

        return sql;
    }

    public String count(Map<String, Object> params) {
        return new SQL() {
            {
                SELECT("count(*)");
                FROM(DOCUMENT_TABLE);
                if (params.get("document") != null) {
                    Document document = (Document) params.get("document");

                    if (document.getTitle() != null && !document.getTitle().equals("")) {
                        WHERE(" title LIKE CONCAT ('%', #{document.title},'%') ");
                    }
                }

            }
        }.toString();
    }

    public String insertDocument(Document document) {
        return new SQL() {
            {
                INSERT_INTO(DOCUMENT_TABLE);
                if (document.getTitle() != null && !document.getTitle().equals("")) {
                    VALUES("title", "#{title}");
                }

                if (document.getFileName() != null && !document.getFileName().equals("")) {
                    VALUES("filename", "#{fileName}");
                }

                if (document.getRemark() != null && !document.getRemark().equals("")) {
                    VALUES("remark", "#{remark}");
                }

                if (document.getUser() != null && document.getUser().getId() != null) {
                    VALUES("userId", "#{user.id}");
                }
            }
        }.toString();
    }

    public String updateDocument(Document document) {
        return new SQL() {
            {
                UPDATE(DOCUMENT_TABLE);

                if (document.getTitle() != null && !document.getTitle().equals("")) {
                    SET("title = #{title}");
                }

                if (document.getFileName() != null && !document.getFileName().equals("")) {
                    SET("filename = #{fileName}");
                }

                if (document.getRemark() != null && !document.getRemark().equals("")) {
                    SET("remark = #{remark}");
                }

                if (document.getUser() != null && document.getUser().getId() != null) {
                    SET("userId = #{user.id}");
                }

                WHERE(" id = #{id} ");
            }
        }.toString();
    }

}
