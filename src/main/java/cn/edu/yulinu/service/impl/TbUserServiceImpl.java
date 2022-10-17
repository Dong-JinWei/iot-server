package cn.edu.yulinu.service.impl;

import cn.edu.yulinu.dao.TbUserDao;
import cn.edu.yulinu.entity.TbGroup;
import cn.edu.yulinu.entity.TbUser;
import cn.edu.yulinu.service.TbUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (TbUser)表服务实现类
 *
 * @author DongJinwei
 * @since 2022-10-10 17:25:21
 */
@Service("tbUserService")
public class TbUserServiceImpl extends ServiceImpl<TbUserDao, TbUser> implements TbUserService {

    @Resource
    private TbUserDao tbUserDao;

    @Override
    public boolean checkUserName(String userName) {
        int effect = tbUserDao.checkUserName(userName);
        if (effect > 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean updateUserById(TbUser tbUser) {
        return tbUserDao.updateUserById(tbUser) > 0;
    }

    @Override
    public boolean updatePassword(TbUser tbUser) {
        return tbUserDao.updatePassword(tbUser) > 0;
    }

    @Override
    public boolean updateMail(TbUser tbUser) {
        return tbUserDao.updateMail(tbUser) > 0;
    }

    @Override
    public boolean saveUser(TbUser tbUser) {
        return tbUserDao.saveUser(tbUser) > 0;
    }

    @Override
    public boolean updateUserName(TbUser tbuser, String newName) {
        return tbUserDao.updateUserName(tbuser, newName) > 0;
    }

    @Override
    public boolean removeUser(TbUser tbUser) {
        return tbUserDao.removeUser(tbUser) > 0;
    }

    @Override
    public boolean updateUserByAdmin(TbUser tbUser) {
        return tbUserDao.updateUserByAdmin(tbUser) > 0;
    }

}

