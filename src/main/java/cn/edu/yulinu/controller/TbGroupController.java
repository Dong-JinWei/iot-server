package cn.edu.yulinu.controller;


import cn.edu.yulinu.dao.TbGroupDao;
import cn.edu.yulinu.dao.UserToGroupDao;
import cn.edu.yulinu.entity.TbGroup;
import cn.edu.yulinu.entity.TbUser;
import cn.edu.yulinu.entity.UserToGroup;
import cn.edu.yulinu.service.TbGroupService;
import cn.edu.yulinu.service.TbUserService;
import cn.edu.yulinu.service.UserToGroupService;
import cn.edu.yulinu.tools.CommonResult;
import cn.edu.yulinu.tools.MD5;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * (TbGroup)表控制层
 *
 * @author DongJinwei
 * @since 2022-10-11 15:47:22
 */
@RestController
@RequestMapping("tbGroup")
@Api(tags = "分组控制器")
@CrossOrigin
public class TbGroupController {
    /**
     * 服务对象
     */
    @Resource
    private TbGroupService tbGroupService;

    @Resource
    private TbUserService tbUserService;

    @Resource
    private UserToGroupService userToGroupService;




    @ApiOperation("新建产品")
    @PostMapping("/saveGroup")
    public CommonResult saveGroup(@RequestBody TbGroup tbGroup, String adminKey){
        if (adminKey == null || "".equals(adminKey)) {
            return CommonResult.failed("你不是管理员");
        }
        QueryWrapper<TbUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_admin_key", MD5.getResult(adminKey, adminKey));
        TbUser isAdmin = tbUserService.getOne(queryWrapper);
        if (isAdmin == null) {
            return CommonResult.failed("管理员密钥错误");
        }
        boolean checkName = tbGroupService.checkName(tbGroup);
        if (checkName){
            return CommonResult.failed("设备组名已存在");
        }
        boolean save = tbGroupService.saveGroup(tbGroup);
        if(save){
            return CommonResult.success(true, "新建设备组成功");
        }else {
            return CommonResult.failed("新建设备组失败");
        }
    }
    //
    // @ApiOperation("修改设备组")
    // @PostMapping("/update")
    // public CommonResult update(TbGroup tbGroup){
    //     boolean checkName = tbGroupService.checkName(tbGroup);
    //     if (checkName){
    //         return CommonResult.failed("设备组名已存在");
    //     }
    //     boolean save = tbGroupService.updateGroupName(tbGroup);
    //     if(save){
    //         return CommonResult.success(true, "修改设备组成功");
    //     }else {
    //         return CommonResult.failed("修改设备组失败");
    //     }
    // }


    @ApiOperation("查询所有产品")
    @GetMapping("/getAllGroup")
    public CommonResult getAllGroup(){
        List<TbGroup> allGroup = tbGroupService.getAllGroup();
        List<TbGroup> res = new ArrayList<>();
        for (TbGroup tbGroup : allGroup) {
            List<String> userNames = userToGroupService.getUserNameById(tbGroup.getGroupId() + "");
            tbGroup.setGroupToUser(userNames);
            res.add(tbGroup);
        }
        if (allGroup == null || allGroup.size() == 0){
            return CommonResult.failed("数据为空");
        }
        return CommonResult.success(res, "查询成功");
    }
    @ApiOperation("删除产品")
    @PostMapping("/removeGroup")
    public CommonResult removeGroup(@RequestBody TbGroup tbGroup, String adminKey){
        if(adminKey == null || "".equals(adminKey)){
            return CommonResult.failed("请输入管理员密钥");
        }
        String result = MD5.getResult(adminKey, adminKey);
        QueryWrapper<TbUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_admin_key", result);
        TbUser one = tbUserService.getOne(queryWrapper);
        if(one == null){
            return CommonResult.failed("管理员密钥错误");
        }

        boolean ok = tbGroupService.removeGroup(tbGroup);
        if(ok){
            return CommonResult.success(true, "删除成功");
        }else {
            return CommonResult.failed("删除失败");
        }
    }

    @ApiOperation("修改产品信息")
    @PostMapping("/updateGroup")
    public CommonResult updateGroup(@RequestBody TbGroup tbGroup, String adminKey){
        if(adminKey == null || "".equals(adminKey)){
            return CommonResult.failed("请输入管理员密钥");
        }
        String result = MD5.getResult(adminKey, adminKey);
        QueryWrapper<TbUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_admin_key", result);
        TbUser one = tbUserService.getOne(queryWrapper);
        if(one == null){
            return CommonResult.failed("管理员密钥错误");
        }

        boolean ok = tbGroupService.updateGroup(tbGroup);
        if(ok){
            return CommonResult.success(true, "修改成功");
        }else {
            return CommonResult.failed("修改失败");
        }
    }



}

