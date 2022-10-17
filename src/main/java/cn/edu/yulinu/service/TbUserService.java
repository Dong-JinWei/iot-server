package cn.edu.yulinu.service;

import cn.edu.yulinu.dao.TbUserDao;
import cn.edu.yulinu.entity.TbGroup;
import com.baomidou.mybatisplus.extension.service.IService;
import cn.edu.yulinu.entity.TbUser;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (TbUser)表服务接口
 *
 * @author DongJinwei
 * @since 2022-10-10 17:25:21
 */
public interface TbUserService extends IService<TbUser> {

    boolean checkUserName(String userName);

    boolean updateUserById(TbUser tbUser);

    boolean updatePassword(TbUser tbUser);

    boolean updateMail(TbUser tbUser);

    boolean saveUser(TbUser tbUser);

    boolean updateUserName(TbUser tbuser, String newName);

    boolean removeUser(TbUser tbUser);

    boolean updateUserByAdmin(TbUser tbUser);




}

