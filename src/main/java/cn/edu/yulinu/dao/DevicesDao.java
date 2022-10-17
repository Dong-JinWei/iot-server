package cn.edu.yulinu.dao;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import cn.edu.yulinu.entity.Devices;

/**
 * (Devices)表数据库访问层
 *
 * @author DongJinwei
 * @since 2022-10-17 10:04:26
 */
@Mapper

public interface DevicesDao extends BaseMapper<Devices> {

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<Devices> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<Devices> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<Devices> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<Devices> entities);

}

