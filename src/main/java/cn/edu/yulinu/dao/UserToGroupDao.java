package cn.edu.yulinu.dao;

import java.util.List;

import cn.edu.yulinu.entity.GroupKey;
import cn.edu.yulinu.entity.TbGroup;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import cn.edu.yulinu.entity.UserToGroup;

/**
 * (UserToGroup)表数据库访问层
 *
 * @author DongJinwei
 * @since 2022-10-11 15:47:22
 */
@Mapper
public interface UserToGroupDao extends BaseMapper<UserToGroup> {


    int saveUTG(Integer userId, Integer groupId);

    int removeUTG(@Param("userId") Integer userId, @Param("groupId") Integer groupId);

    List<GroupKey> getGroupNameAndGroupKey(@Param("userName") String userName);

    List<TbGroup> getGroupKeyById(@Param("userId") String userId);

    List<String> getGroupNameById(@Param("userId") String userId);

    List<String> getUserNameById(@Param("groupId") String groupId);

    int removeUserId(@Param("userId") Integer userId);


}

