package cn.edu.yulinu.controller;


import cn.edu.yulinu.entity.Devices;
import cn.edu.yulinu.service.DevicesService;
import cn.edu.yulinu.tools.CommonResult;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * (Devices)表控制层
 *
 * @author DongJinwei
 * @since 2022-10-17 10:04:26
 */
@RestController
@RequestMapping("devices")
@Api(tags = "设备控制器")
public class DevicesController {
    /**
     * 服务对象
     */
    @Resource
    private DevicesService devicesService;

    /**
     * 分页查询所有数据
     *
     * @param page    分页对象
     * @param devices 查询实体
     * @return 所有数据
     */
    @GetMapping
    public CommonResult selectAll(Page<Devices> page, Devices devices) {
        return CommonResult.success(this.devicesService.page(page, new QueryWrapper<>(devices)));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public CommonResult selectOne(@PathVariable Serializable id) {
        return CommonResult.success(this.devicesService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param devices 实体对象
     * @return 新增结果
     */
    @PostMapping
    public CommonResult insert(@RequestBody Devices devices) {
        return CommonResult.success(this.devicesService.save(devices));
    }

    /**
     * 修改数据
     *
     * @param devices 实体对象
     * @return 修改结果
     */
    @PutMapping
    public CommonResult update(@RequestBody Devices devices) {
        return CommonResult.success(this.devicesService.updateById(devices));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping
    public CommonResult delete(@RequestParam("idList") List<Long> idList) {
        return CommonResult.success(this.devicesService.removeByIds(idList));
    }
}

