package cn.edu.yulinu.controller;


import cn.edu.yulinu.entity.TbUser;
import cn.edu.yulinu.entity.UserApiKey;
import cn.edu.yulinu.service.TbUserService;
import cn.edu.yulinu.service.UserApiKeyService;
import cn.edu.yulinu.tools.CommonResult;
import cn.edu.yulinu.tools.MD5;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javassist.runtime.Cflow;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * (UserApiKey)表控制层
 *
 * @author DongJinwei
 * @since 2022-10-11 21:17:57
 */
@RestController
@RequestMapping("userApiKey")
@Api(tags = "apiKey控制器")
public class UserApiKeyController {
    /**
     * 服务对象
     */
    @Resource
    private UserApiKeyService userApiKeyService;

    @Resource
    private TbUserService tbUserService;

    @GetMapping("/getKeyByUser")
    @ApiOperation("查询apikey")
    public CommonResult getKeyByUser(UserApiKey userApiKey){
        QueryWrapper<UserApiKey> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userApiKey.getUserId());
        List<UserApiKey> keyList = userApiKeyService.list(queryWrapper);
        if (keyList != null){
            return CommonResult.success(keyList, "获取用户api key成功");
        }else {
            return CommonResult.failed("获取api key失败");
        }
    }
    @ApiOperation("添加用户apikey")
    @PostMapping("/save")
    public CommonResult save(Integer userId, List<String> apiKeys, String adminKey){
    // public CommonResult save(){
    //     Integer userId = 1;
    //     ArrayList<String> apiKeys = new ArrayList<>();
    //     apiKeys.add("fDKmkLzpDrGp=HGMVOd3akWaNPA=");
    //     apiKeys.add("zfKDkkEzpQDUAyjiLX=5PND8GxU=");
    //     String adminKey = "ylxyadmin";
        if (adminKey == null || "".equals(adminKey)) {
            return CommonResult.failed("你不是管理员");
        }
        QueryWrapper<TbUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_admin_key", MD5.getResult(adminKey, adminKey));
        TbUser isAdmin = tbUserService.getOne(queryWrapper);
        if (isAdmin == null) {
            return CommonResult.failed("管理员密钥错误");
        }

        for (String apiKey : apiKeys) {
            boolean save = userApiKeyService.save(new UserApiKey(null, userId, apiKey));
            if (!save){
                return CommonResult.failed("添加{" + apiKey + "}失败");
            }
        }
        return CommonResult.success(true, "添加成功");
    }

    @ApiOperation("修改用户apikey")
    @PostMapping("/update")
    public CommonResult update(UserApiKey userApiKey, String newApiKey, String adminKey){
        if (adminKey == null || "".equals(adminKey)) {
            return CommonResult.failed("你不是管理员");
        }
        QueryWrapper<TbUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_admin_key", MD5.getResult(adminKey, adminKey));
        TbUser isAdmin = tbUserService.getOne(queryWrapper);
        if (isAdmin == null) {
            return CommonResult.failed("管理员密钥错误");
        }
        boolean ok = userApiKeyService.updateApiKey(userApiKey, newApiKey);
        if(ok){
            return CommonResult.success(true, "修改成功");
        }else {
            return CommonResult.failed("修改失败");
        }
    }

    @ApiOperation("删除用户的apikey")
    @PostMapping("/remove")
    public CommonResult remove(Integer userId, List<String> apiKeys, String adminKey){
    // public CommonResult remove(){
        // Integer userId = 1;
        // ArrayList<String> apiKeys = new ArrayList<>();
        // apiKeys.add("fDKmkLzpDrGp=HGMVOd3akWaNPA=");
        // apiKeys.add("zfKDkkEzpQDUAyjiLX=5PND8GxU=");
        // String adminKey = "ylxyadmin";
        if (adminKey == null || "".equals(adminKey)) {
            return CommonResult.failed("你不是管理员");
        }
        QueryWrapper<TbUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_admin_key", MD5.getResult(adminKey, adminKey));
        TbUser isAdmin = tbUserService.getOne(queryWrapper);
        if (isAdmin == null) {
            return CommonResult.failed("管理员密钥错误");
        }
        boolean ok = userApiKeyService.removeApiKey(userId, apiKeys);
        if(ok){
            return CommonResult.success(true, "删除成功");
        }else {
            return CommonResult.failed("删除失败");
        }
    }


}

