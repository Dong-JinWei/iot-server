package cn.edu.yulinu.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * (Datas)表实体类
 *
 * @author DongJinwei
 * @since 2022-10-16 20:44:04
 */
@SuppressWarnings("serial")
@NoArgsConstructor
@AllArgsConstructor
@Data
@TableName("datas")
public class Datas extends Model<Datas> {

    @TableId(type = IdType.AUTO)
    private Integer tbId;

    private String deviceId;

    private String sensorId;

    private String updateAt;

    private String currentValue;


}

