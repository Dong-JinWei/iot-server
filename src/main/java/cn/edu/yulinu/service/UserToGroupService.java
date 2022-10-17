package cn.edu.yulinu.service;

import cn.edu.yulinu.entity.GroupKey;
import cn.edu.yulinu.entity.TbGroup;
import com.baomidou.mybatisplus.extension.service.IService;
import cn.edu.yulinu.entity.UserToGroup;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * (UserToGroup)表服务接口
 *
 * @author DongJinwei
 * @since 2022-10-11 15:47:22
 */
public interface UserToGroupService extends IService<UserToGroup> {

    boolean saveUTG(Integer userId, Integer groupId);

    boolean removeUTG(Integer userId, Integer groupId);

    List<GroupKey> getGroupNameAndGroupKey(String userName);

    List<TbGroup> getGroupKeyById(String userId);

    List<String> getGroupNameById(String userId);

    List<String> getUserNameById( String groupId);

    int removeUserId( Integer userId);




}

