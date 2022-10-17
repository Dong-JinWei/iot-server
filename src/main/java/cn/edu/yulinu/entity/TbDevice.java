package cn.edu.yulinu.entity;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * (TbDevice)表实体类
 *
 * @author DongJinwei
 * @since 2022-10-11 15:47:22
 */
@SuppressWarnings("serial")
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("tb_device")
@ApiModel(value = "设备对象", description = "设备对象")
public class TbDevice implements Serializable {

    private Integer deviceId;
    // 设备名
    private String deviceName;
    // 所属组id
    private Integer groupId;

    @TableField(exist = false)
    private TbDevice tbDevice;
}

