package cn.edu.yulinu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.edu.yulinu.dao.DatasDao;
import cn.edu.yulinu.entity.Datas;
import cn.edu.yulinu.service.DatasService;
import org.springframework.stereotype.Service;

/**
 * (Datas)表服务实现类
 *
 * @author DongJinwei
 * @since 2022-10-16 20:44:05
 */
@Service("datasService")
public class DatasServiceImpl extends ServiceImpl<DatasDao, Datas> implements DatasService {

}

