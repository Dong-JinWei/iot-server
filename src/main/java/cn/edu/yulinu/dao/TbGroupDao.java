package cn.edu.yulinu.dao;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import cn.edu.yulinu.entity.TbGroup;

/**
 * (TbGroup)表数据库访问层
 *
 * @author DongJinwei
 * @since 2022-10-11 15:47:22
 */
@Mapper
public interface TbGroupDao extends BaseMapper<TbGroup> {

    int updateGroupName(TbGroup tbGroup);

    int checkName(TbGroup tbGroup);

    int saveGroup(TbGroup tbGroup);

    List<TbGroup> getAllGroup();

    int removeGroup(TbGroup tbGroup);

    int updateGroup(TbGroup tbGroup);
}

