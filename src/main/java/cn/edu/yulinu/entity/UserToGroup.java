package cn.edu.yulinu.entity;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * (UserToGroup)表实体类
 *
 * @author DongJinwei
 * @since 2022-10-11 15:47:22
 */
@SuppressWarnings("serial")
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("user_to_group")
@ApiModel(value = "用户与组关系", description = "用户与组关系")
public class UserToGroup implements Serializable {
    private Integer userId;

    private Integer groupId;

    @TableField(exist = false)
    private TbGroup tbGroup;

}

