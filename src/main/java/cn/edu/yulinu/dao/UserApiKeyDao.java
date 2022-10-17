package cn.edu.yulinu.dao;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import cn.edu.yulinu.entity.UserApiKey;

/**
 * (UserApiKey)表数据库访问层
 *
 * @author DongJinwei
 * @since 2022-10-11 21:17:57
 */@Mapper

public interface UserApiKeyDao extends BaseMapper<UserApiKey> {

    int updateApiKey(@Param("userApiKey") UserApiKey userApiKey, @Param("newApiKey") String newApiKey);

    int removeApiKey(Integer userId, List<String> apiKeys);
}

