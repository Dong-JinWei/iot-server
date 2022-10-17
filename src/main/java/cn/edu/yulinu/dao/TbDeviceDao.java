package cn.edu.yulinu.dao;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import cn.edu.yulinu.entity.TbDevice;

/**
 * (TbDevice)表数据库访问层
 *
 * @author DongJinwei
 * @since 2022-10-11 15:47:22
 */
@Mapper

public interface TbDeviceDao extends BaseMapper<TbDevice> {

    List<TbDevice> getSideAll();

    int updateDevice(@Param("deviceIds") List<String> deviceIds, @Param("groupId") Integer groupId);
}

