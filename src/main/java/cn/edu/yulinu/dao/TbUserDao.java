package cn.edu.yulinu.dao;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import cn.edu.yulinu.entity.TbUser;
import org.springframework.stereotype.Repository;

/**
 * (TbUser)表数据库访问层
 *
 * @author DongJinwei
 * @since 2022-10-10 17:25:21
 */
@Repository
@Mapper
public interface TbUserDao extends BaseMapper<TbUser> {


    int checkUserName(String userName);

    int updateUserById(TbUser tbUser);

    int updatePassword(TbUser tbUser);

    int updateMail(TbUser tbUser);

    int saveUser(TbUser tbUser);

    int updateUserName(TbUser tbUser, String newName);


    int removeUser(TbUser tbUser);

    int updateUserByAdmin(TbUser tbUser);


}

