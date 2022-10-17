package cn.edu.yulinu.service.impl;

import cn.edu.yulinu.dao.TbGroupDao;
import cn.edu.yulinu.entity.TbGroup;
import cn.edu.yulinu.service.TbGroupService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (TbGroup)表服务实现类
 *
 * @author DongJinwei
 * @since 2022-10-11 15:47:22
 */
@Service("tbGroupService")
public class TbGroupServiceImpl extends ServiceImpl<TbGroupDao, TbGroup> implements TbGroupService {

    @Resource
    private TbGroupDao tbGroupDao;

    @Override
    public boolean updateGroupName(TbGroup tbGroup) {
        return tbGroupDao.updateGroupName(tbGroup) > 0;
    }

    @Override
    public boolean checkName(TbGroup tbGroup) {
        return tbGroupDao.checkName(tbGroup) > 0;
    }

    @Override
    public boolean saveGroup(TbGroup tbGroup) {
        return tbGroupDao.saveGroup(tbGroup) > 0;
    }

    @Override
    public List<TbGroup> getAllGroup() {
        return tbGroupDao.getAllGroup();
    }

    @Override
    public boolean removeGroup(TbGroup tbGroup) {
        return tbGroupDao.removeGroup(tbGroup) > 0;
    }

    @Override
    public boolean updateGroup(TbGroup tbGroup) {
        return tbGroupDao.updateGroup(tbGroup) > 0;
    }
}

