package com.hr.mapper;

import com.hr.entity.Job;
import com.hr.mapper.provider.JobDynaSqlProvider;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;
import java.util.Map;

import static com.hr.common.HrmConstants.JOB_TABLE;

public interface JobMapper {
    @Select("select * from " + JOB_TABLE + " where id = #{id}")
    Job selectById(Integer id);

    @Select("select * from " + JOB_TABLE + " ")
    List<Job> selectAllJob();

    @Delete(" delete from " + JOB_TABLE + " where id = #{id}")
    void deleteById(Integer id) throws Exception;

    @Select("select count(*) from " + JOB_TABLE)
    int getTotalCount();

    /**
     * 动态SQL
     */
    @SelectProvider(type = JobDynaSqlProvider.class, method = "selectWithParam")
    List<Job> selectByPage(Map<String, Object> params);

    @SelectProvider(type = JobDynaSqlProvider.class, method = "count")
    Integer count(Map<String, Object> params);

    @SelectProvider(type = JobDynaSqlProvider.class, method = "addJob")
    void save(Job job);

    @SelectProvider(type = JobDynaSqlProvider.class, method = "updateJob")
    void update(Job job);
}
