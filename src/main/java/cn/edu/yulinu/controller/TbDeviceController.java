package cn.edu.yulinu.controller;


import cn.edu.yulinu.entity.TbDevice;
import cn.edu.yulinu.service.TbDeviceService;
import cn.edu.yulinu.tools.CommonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * (TbDevice)表控制层
 *
 * @author DongJinwei
 * @since 2022-10-11 15:47:22
 */
@RestController
@RequestMapping("tbDevice")
@Api(tags = "设备控制器")
@CrossOrigin
public class TbDeviceController {
    /**
     * 服务对象
     */
    @Resource
    private TbDeviceService tbDeviceService;

    // @ApiOperation("查询所有设备")
    // @GetMapping("/getSideAll")
    // public CommonResult getSideAll() {
    //     List<TbDevice> sideAll = tbDeviceService.getSideAll();
    //     if (sideAll != null) {
    //         return CommonResult.success(sideAll);
    //     }else {
    //         return CommonResult.failed("获取设备列表失败");
    //     }
    // }
    //
    // @ApiOperation("更改设备所属组")
    // @PostMapping("/updateDevice")
    // public CommonResult updateDevice(List<String> deviceIds, Integer groupId) {
    //     if (deviceIds != null || deviceIds.size() == 0){
    //         return CommonResult.failed("设备不能为空");
    //     }
    //     boolean updateDevice = tbDeviceService.updateDevice(deviceIds, groupId);
    //     if (updateDevice) {
    //         return CommonResult.success(true, "修改成功");
    //     }else {
    //         return CommonResult.failed("修改失败");
    //     }
    // }

}

