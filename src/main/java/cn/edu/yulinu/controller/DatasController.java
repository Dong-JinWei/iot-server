package cn.edu.yulinu.controller;


import cn.edu.yulinu.service.DatasService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * (Datas)表控制层
 *
 * @author DongJinwei
 * @since 2022-10-16 20:44:04
 */
@RestController
@RequestMapping("datas")
public class DatasController {
    /**
     * 服务对象
     */
    @Resource
    private DatasService datasService;

}

