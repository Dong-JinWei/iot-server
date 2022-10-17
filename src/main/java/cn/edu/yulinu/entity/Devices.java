package cn.edu.yulinu.entity;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * (Devices)表实体类
 *
 * @author DongJinwei
 * @since 2022-10-16 20:44:05
 */
@SuppressWarnings("serial")
@NoArgsConstructor
@AllArgsConstructor
@Data
@TableName("devices")
public class Devices implements Serializable {

    @TableId
    private Integer id;

    @TableField("title")
    private String title;

    @TableField("online")
    private String online;

    @TableField("last_login")
    private String lastLogin;

    @TableField("act_time")
    private String actTime;

    @TableField("create_time")
    private String createTime;

}

