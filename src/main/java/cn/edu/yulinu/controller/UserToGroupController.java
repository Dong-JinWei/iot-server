package cn.edu.yulinu.controller;


import cn.edu.yulinu.entity.GroupKey;
import cn.edu.yulinu.entity.TbGroup;
import cn.edu.yulinu.entity.TbUser;
import cn.edu.yulinu.service.TbUserService;
import cn.edu.yulinu.service.UserToGroupService;
import cn.edu.yulinu.tools.CommonResult;
import cn.edu.yulinu.tools.MD5;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

/**
 * (UserToGroup)表控制层
 *
 * @author DongJinwei
 * @since 2022-10-11 15:47:22
 */
@RestController
@RequestMapping("userToGroup")
@Api(tags = "用户与组关系")
@CrossOrigin
public class UserToGroupController {
    /**
     * 服务对象
     */
    @Resource
    private UserToGroupService userToGroupService;

    @Resource
    private TbUserService tbUserService;


    @ApiOperation("为用户分配组")
    @PostMapping("/save")
    public CommonResult save(Integer userId, @RequestBody List<Integer> groupIds, String adminKey) {
        if (adminKey == null || "".equals(adminKey)) {
            return CommonResult.failed("你不是管理员");
        }
        QueryWrapper<TbUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_admin_key", MD5.getResult(adminKey, adminKey));
        TbUser isAdmin = tbUserService.getOne(queryWrapper);
        if (isAdmin == null) {
            return CommonResult.failed("管理员密钥错误");
        }

        userToGroupService.removeUserId(userId);

        for (Integer groupId : groupIds) {
            boolean ok = userToGroupService.saveUTG(userId, groupId);
            if (!ok) {
                return CommonResult.failed("插入失败");
            }
        }
        return CommonResult.success("插入成功");
    }

    @ApiOperation("删除用户分组")
    @PostMapping("/remove")
    public CommonResult remove(Integer userId, Integer groupId) {
        boolean ok = userToGroupService.removeUTG(userId, groupId);
        if (!ok) {
            return CommonResult.failed("删除失败");
        }
        return CommonResult.success("删除成功");
    }

    @ApiOperation("查询用户分组")
    @GetMapping("/getByUser")
    public CommonResult getByUser(String userName) {
        List<GroupKey> list = userToGroupService.getGroupNameAndGroupKey(userName);
        if(list == null || list.size() == 0){
            return CommonResult.failed("查询失败");
        }
        return CommonResult.success(list, "查询成功");
    }

    @ApiOperation("根据用户查Key")
    @GetMapping("/getGroupKey")
    public CommonResult getGroupKey(String userId) {
        List<TbGroup> list = userToGroupService.getGroupKeyById(userId);
        if(list == null || list.size() == 0){
            return CommonResult.failed("查询失败");
        }
        return CommonResult.success(list, "查询成功");
    }


}

