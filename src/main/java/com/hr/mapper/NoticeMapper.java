package com.hr.mapper;

import com.hr.entity.Notice;
import com.hr.mapper.provider.NoticeDynaSqlProvider;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;
import java.util.Map;

import static com.hr.common.HrmConstants.NOTICE_TABLE;

public interface NoticeMapper {

    @Select("select * from " + NOTICE_TABLE + " where id = #{id}")
    Notice selectById(Integer id);

    @Delete(" delete from " + NOTICE_TABLE + " where id = #{id}")
    void deleteById(Integer id);

    @Select("select count(*) from " + NOTICE_TABLE)
    int getTotalCount();

    /** 动态SQL */
    @SelectProvider(type = NoticeDynaSqlProvider.class, method = "selectWithParam")
    @Results({
            @Result(column = "userId", property = "user", one = @One(select = "com.hr.mapper.UserMapper.selectById", fetchType = FetchType.EAGER))
    })
    List<Notice> selectByPage(Map<String, Object> params);

    @SelectProvider(type = NoticeDynaSqlProvider.class, method = "count")
    Integer count(Map<String, Object> params);

    @SelectProvider(type = NoticeDynaSqlProvider.class, method = "insertNotice")
    void save(Notice notice);

    @SelectProvider(type = NoticeDynaSqlProvider.class, method = "updateNotice")
    void update(Notice notice);


}
