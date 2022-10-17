package cn.edu.yulinu.entity;


import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * (UserApiKey)表实体类
 *
 * @author DongJinwei
 * @since 2022-10-11 21:17:57
 */
@SuppressWarnings("serial")

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("user_api_key")
@ApiModel(value="apikey对象",description="apikey对象")
public class UserApiKey implements Serializable {

    private Integer apiId;

    private Integer userId;

    private String apiKey;
}

