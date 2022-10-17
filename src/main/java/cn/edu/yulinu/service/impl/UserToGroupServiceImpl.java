package cn.edu.yulinu.service.impl;

import cn.edu.yulinu.entity.GroupKey;
import cn.edu.yulinu.entity.TbGroup;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.edu.yulinu.dao.UserToGroupDao;
import cn.edu.yulinu.entity.UserToGroup;
import cn.edu.yulinu.service.UserToGroupService;
import com.mysql.cj.protocol.x.XProtocolRowInputStream;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (UserToGroup)表服务实现类
 *
 * @author DongJinwei
 * @since 2022-10-11 15:47:22
 */
@Service("userToGroupService")
public class UserToGroupServiceImpl extends ServiceImpl<UserToGroupDao, UserToGroup> implements UserToGroupService {

    @Resource
    private UserToGroupDao userToGroupDao;

    @Override
    public boolean saveUTG(Integer userId, Integer groupId) {
        return userToGroupDao.saveUTG(userId, groupId) > 0;
    }

    @Override
    public boolean removeUTG(Integer userId, Integer groupId) {
        return userToGroupDao.removeUTG(userId, groupId) > 0;
    }

    @Override
    public List<GroupKey> getGroupNameAndGroupKey(String userName) {
        return userToGroupDao.getGroupNameAndGroupKey(userName);
    }

    @Override
    public List<TbGroup> getGroupKeyById(String userId) {
        return userToGroupDao.getGroupKeyById(userId);
    }

    @Override
    public List<String> getGroupNameById(String userId) {
        return userToGroupDao.getGroupNameById(userId);
    }

    @Override
    public List<String> getUserNameById(String groupId) {
        return userToGroupDao.getUserNameById(groupId);
    }

    @Override
    public int removeUserId(Integer userId) {
        return userToGroupDao.removeUserId(userId);
    }
}

