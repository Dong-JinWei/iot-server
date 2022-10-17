package cn.edu.yulinu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import cn.edu.yulinu.entity.TbDevice;

import java.util.List;

/**
 * (TbDevice)表服务接口
 *
 * @author DongJinwei
 * @since 2022-10-11 15:47:22
 */
public interface TbDeviceService extends IService<TbDevice> {

    List<TbDevice> getSideAll();

    boolean updateDevice(List<String> deviceIds, Integer groupId);


}

