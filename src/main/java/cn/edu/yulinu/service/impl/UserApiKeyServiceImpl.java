package cn.edu.yulinu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.edu.yulinu.dao.UserApiKeyDao;
import cn.edu.yulinu.entity.UserApiKey;
import cn.edu.yulinu.service.UserApiKeyService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (UserApiKey)表服务实现类
 *
 * @author DongJinwei
 * @since 2022-10-11 21:17:57
 */
@Service("userApiKeyService")
public class UserApiKeyServiceImpl extends ServiceImpl<UserApiKeyDao, UserApiKey> implements UserApiKeyService {

    @Resource
    private UserApiKeyDao userApiKeyDao;

    @Override
    public boolean removeApiKey(Integer userId, List<String> apiKeys) {
        return userApiKeyDao.removeApiKey(userId, apiKeys) > 0;
    }

    @Override
    public boolean updateApiKey(UserApiKey userApiKey, String newApiKey) {
        return userApiKeyDao.updateApiKey(userApiKey, newApiKey) > 0;
    }
}

