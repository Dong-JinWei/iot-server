package cn.edu.yulinu.entity;


import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * (TbUser)表实体类
 *
 * @author DongJinwei
 * @since 2022-10-10 17:25:21
 */
@SuppressWarnings("serial")

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("tb_user")
@ApiModel(value="user对象",description="用户对象user")
public class TbUser implements Serializable {
    @TableId(type = IdType.AUTO)
    private Long userId;
    // 用户名
    @ApiModelProperty(value = "用户名", name = "userName", required = true, example = "admin")
    private String userName;
    // 用户邮箱
    @ApiModelProperty(value = "邮箱", name = "userMail", example = "admin@qq.com")
    private String userMail;
    // 密码
    @ApiModelProperty(value = "密码", name = "userPassword", required = true)
    private String userPassword;
    // 权限级别
    @ApiModelProperty(value = "权限级别", name = "userType")
    private String userType;
    // 性别
    @ApiModelProperty(value = "性别", name = "userSex")
    private String userSex;
    // 头像
    @ApiModelProperty(value = "头像", name = "userAvatar")
    private String userAvatar;
    // 用户状态（0正常 1停用）
    @ApiModelProperty(value = "用户状态", name = "userStatus")
    private String userStatus;
    // 删除标志（0代表存在 2代表删除）
    @TableLogic
    @ApiModelProperty(value = "删除标志", name = "userDel")
    private String userDel;
    // 最后登陆时间
    @TableField(fill = FieldFill.UPDATE)
    @ApiModelProperty(value = "最后登陆时间", name = "userLoginDate")
    private String userLoginDate;
    // 创建时间
    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty(value = "创建时间", name = "userCreateTime")
    private String userCreateTime;

    // 超级管理员密钥
    @TableField(exist = false)
    @ApiModelProperty(hidden = true)
    private String userAdminKey;

    @TableField(exist = false)
    @ApiModelProperty(hidden = true)
    private List userToGroup;

}

