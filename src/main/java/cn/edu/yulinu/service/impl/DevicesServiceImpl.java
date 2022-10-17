package cn.edu.yulinu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.edu.yulinu.dao.DevicesDao;
import cn.edu.yulinu.entity.Devices;
import cn.edu.yulinu.service.DevicesService;
import org.springframework.stereotype.Service;

/**
 * (Devices)表服务实现类
 *
 * @author DongJinwei
 * @since 2022-10-17 10:04:26
 */
@Service("devicesService")
public class DevicesServiceImpl extends ServiceImpl<DevicesDao, Devices> implements DevicesService {

}

