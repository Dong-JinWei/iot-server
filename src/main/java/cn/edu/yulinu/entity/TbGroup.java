package cn.edu.yulinu.entity;


import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * (TbGroup)表实体类
 *
 * @author DongJinwei
 * @since 2022-10-11 15:47:22
 */
@SuppressWarnings("serial")
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("tb_group")
@ApiModel(value = "分组对象", description = "分组对象")
public class TbGroup implements Serializable {

    private Integer groupId;
    // 分组名称
    private String groupName;

    private String groupKey;

    private List groupToUser;
}

