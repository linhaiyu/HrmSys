package com.hr.mapper;

import com.hr.entity.Document;
import com.hr.mapper.provider.DocumentDynaSqlProvider;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;
import java.util.Map;

import static com.hr.common.HrmConstants.DOCUMENT_TABLE;

public interface DocumentMapper {

    @Select("select count(*) from " + DOCUMENT_TABLE)
    int getTotalCount();

    @Select("select * from " + DOCUMENT_TABLE + " where id = #{id}")
    Document selectById(Integer id);

    @Delete("delete from " + DOCUMENT_TABLE + " where id = #{id}")
    void deleteById(Integer id);

    /** 动态SQL */
    @SelectProvider(type = DocumentDynaSqlProvider.class, method = "selectWithParam")
    @Results({
            @Result(column = "filename", property = "fileName"),
            @Result(column = "userId", property = "user", one = @One(select = "com.hr.mapper.UserMapper.selectById", fetchType = FetchType.EAGER))
    })
    List<Document> selectByPage(Map<String, Object> params);

    @SelectProvider(type = DocumentDynaSqlProvider.class, method = "count")
    Integer count(Map<String, Object> params);

    @SelectProvider(type = DocumentDynaSqlProvider.class, method = "insertDocument")
    void save(Document document);

    @SelectProvider(type = DocumentDynaSqlProvider.class, method = "updateDocument")
    void update(Document document);
}
