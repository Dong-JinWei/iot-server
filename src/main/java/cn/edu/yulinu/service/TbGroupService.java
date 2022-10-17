package cn.edu.yulinu.service;

import cn.edu.yulinu.entity.TbDevice;
import com.baomidou.mybatisplus.extension.service.IService;
import cn.edu.yulinu.entity.TbGroup;

import java.util.List;

/**
 * (TbGroup)表服务接口
 *
 * @author DongJinwei
 * @since 2022-10-11 15:47:22
 */
public interface TbGroupService extends IService<TbGroup> {

    boolean updateGroupName(TbGroup tbGroup);


    boolean checkName(TbGroup tbGroup);

    boolean saveGroup(TbGroup tbGroup);

    List<TbGroup> getAllGroup();

    boolean removeGroup(TbGroup tbGroup);

    boolean updateGroup(TbGroup tbGroup);





}

