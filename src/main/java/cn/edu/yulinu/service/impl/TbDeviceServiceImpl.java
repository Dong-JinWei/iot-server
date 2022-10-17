package cn.edu.yulinu.service.impl;

import cn.edu.yulinu.dao.TbDeviceDao;
import cn.edu.yulinu.entity.TbDevice;
import cn.edu.yulinu.service.TbDeviceService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (TbDevice)表服务实现类
 *
 * @author DongJinwei
 * @since 2022-10-11 15:47:22
 */
@Service("tbDeviceService")
public class TbDeviceServiceImpl extends ServiceImpl<TbDeviceDao, TbDevice> implements TbDeviceService {

    @Resource
    private TbDeviceDao tbDeviceDao;


    @Override
    public List<TbDevice> getSideAll() {
        return tbDeviceDao.getSideAll();
    }

    @Override
    public boolean updateDevice(List<String> deviceIds, Integer groupId) {
        return tbDeviceDao.updateDevice(deviceIds, groupId) > 0;
    }
}

