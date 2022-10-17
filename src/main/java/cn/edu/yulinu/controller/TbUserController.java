package cn.edu.yulinu.controller;


import cn.edu.yulinu.entity.TbUser;
import cn.edu.yulinu.service.TbUserService;
import cn.edu.yulinu.service.UserToGroupService;
import cn.edu.yulinu.tools.CommonResult;
import cn.edu.yulinu.tools.IDUtils;
import cn.edu.yulinu.tools.MD5;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * (TbUser)表控制层
 *
 * @author DongJinwei
 * @since 2022-10-10 17:25:21
 */
@RestController
@RequestMapping("tbUser")
@Api(tags = "用户控制器")
@CrossOrigin
public class TbUserController {
    /**
     * 服务对象
     */
    @Resource
    private TbUserService tbUserService;

    @Resource
    private UserToGroupService userToGroupService;

    @ApiOperation("查询所有用户")
    @GetMapping("/list")
    public CommonResult list() {
        QueryWrapper<TbUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.ne("user_id", 1);
        List<TbUser> list = tbUserService.list(queryWrapper);
        List<TbUser> resList = new ArrayList<>();
        for (TbUser tbUser : list) {
            List<String> groups = userToGroupService.getGroupNameById(""+ tbUser.getUserId());
            tbUser.setUserToGroup(groups);
            resList.add(tbUser);
        }
        return CommonResult.success(resList, "查询");
    }

    @ApiOperation("登陆")
    @GetMapping("/login")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户名或邮箱", example = "admin", required = true),
            @ApiImplicitParam(name = "password", value = "密码", example = "123", required = true)}
    )
    public CommonResult login(String username, String password) {
        QueryWrapper<TbUser> queryWrapper = new QueryWrapper<>();
        if (IDUtils.isEmail(username)) {
            queryWrapper.eq("user_mail", username);
            System.out.println("邮箱登陆");
        } else {
            queryWrapper.eq("user_name", username);
            System.out.println("用户名登陆");
        }
        TbUser tbUser = tbUserService.getOne(queryWrapper);
        if (tbUser != null) {
            String result = MD5.getResult(tbUser.getUserName(), password);
            if (tbUser.getUserPassword().equals(result)) {
                tbUser.setUserLoginDate(new SimpleDateFormat("yyyy-MM-dd HH:dd:ss").format(new Date()));
                tbUserService.updateById(tbUser);
                return CommonResult.success(tbUser, "登陆成功");
            } else {
                return CommonResult.failed("密码错误");
            }
        } else {
            return CommonResult.failed("用户名或邮箱不存在");
        }
    }

    @ApiOperation("添加用户")
    @PostMapping("/addUser")
    public CommonResult addUser(@RequestBody TbUser tbUser, String adminKey) {
        // adminKey ylxyadmin
        if (adminKey == null || "".equals(adminKey)) {
            return CommonResult.failed("你不是管理员");
        }
        QueryWrapper<TbUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_admin_key", MD5.getResult(adminKey, adminKey));
        TbUser isAdmin = tbUserService.getOne(queryWrapper);
        if (isAdmin == null) {
            return CommonResult.failed("管理员密钥错误");
        }
        if (!tbUserService.checkUserName(tbUser.getUserName())) {
            // 注册
            tbUser.setUserPassword(MD5.getResult(tbUser.getUserName(), tbUser.getUserPassword()));
            boolean ok = tbUserService.saveUser(tbUser);
            return CommonResult.success(ok, "添加成功");
        } else {
            return CommonResult.failed("用户名已存在");
        }
    }

    @ApiOperation("修改用户信息")
    @PostMapping("/updateUser")
    public CommonResult updateUser(TbUser tbUser) {
        tbUser.setUserPassword(MD5.getResult(tbUser.getUserName(), tbUser.getUserPassword()));
        boolean ok = tbUserService.updateUserById(tbUser);
        if (ok) {
            return CommonResult.success("修改成功");
        } else {
            return CommonResult.failed("修改失败");
        }
    }

    @ApiOperation("修改密码")
    @PostMapping("/updatePassword")
    public CommonResult updatePassword(@RequestBody TbUser tbUser, String newPwd) {
        tbUser.setUserPassword(MD5.getResult(tbUser.getUserName(), newPwd));
        boolean ok = tbUserService.updatePassword(tbUser);
        if (ok) {
            return CommonResult.success("修改成功");
        } else {
            return CommonResult.failed("修改失败");
        }
    }

    // +++++++++++++++++++++_+__++__+_+__+_+_+_++_+
    @ApiOperation("修改用户名")
    @PostMapping("/updateUserName")
    public CommonResult updateUserName(@RequestBody TbUser tbUser, String newName) {
        if(tbUserService.checkUserName(newName)){
            return CommonResult.failed("用户名已存在");
        }
        boolean ok = tbUserService.updateUserName(tbUser, newName);
        if (ok) {
            return CommonResult.success("修改成功");
        } else {
            return CommonResult.failed("修改失败");
        }
    }

    @ApiOperation("删除用户")
    @PostMapping("/removeUser")
    public CommonResult removeUser(@RequestBody TbUser tbUser, String adminKey) {
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
        boolean ok = tbUserService.removeUser(tbUser);
        if(ok){
            return CommonResult.success(true, "删除成功");
        }else {
            return CommonResult.failed("删除失败");
        }
    }

    @ApiOperation("检查是否存在此用户")
    @GetMapping("/checkUserName")
    public CommonResult checkUserName(String userName){
        boolean ok = tbUserService.checkUserName(userName);
        if(ok){
            return CommonResult.failed( "用户名已存在");
        }else {
            return CommonResult.success(true, "用户名不存在");
        }
    }


    @ApiOperation("管理员修改用户")
    @PostMapping("/updateUserByAdmin")
    public CommonResult updateUserByAdmin(@RequestBody TbUser tbUser, String adminKey) {
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
        boolean ok = tbUserService.updateUserByAdmin(tbUser);
        if(ok){
            return CommonResult.success(true, "修改成功");
        }else {
            return CommonResult.failed("修改失败");
        }
    }

}

