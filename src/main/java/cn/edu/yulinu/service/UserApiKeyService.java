package cn.edu.yulinu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import cn.edu.yulinu.entity.UserApiKey;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * (UserApiKey)表服务接口
 *
 * @author DongJinwei
 * @since 2022-10-11 21:17:57
 */
public interface UserApiKeyService extends IService<UserApiKey> {

    boolean removeApiKey(Integer userId, List<String> apiKeys);

    boolean updateApiKey(UserApiKey userApiKey, String newApiKey);



}

