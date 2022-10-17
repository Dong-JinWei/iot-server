package cn.edu.yulinu.backup;


import cn.edu.yulinu.dao.DatasDao;
import cn.edu.yulinu.dao.DevicesDao;
import cn.edu.yulinu.entity.Datas;
import cn.edu.yulinu.entity.Devices;
import cn.edu.yulinu.entity.TbGroup;
import cn.edu.yulinu.service.TbGroupService;
import cn.edu.yulinu.service.UserToGroupService;
import cn.edu.yulinu.tools.ApiParse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Backup {

    @Autowired
    private UserToGroupService userToGroupService;

    @Autowired
    private TbGroupService tbGroupService;

    @Autowired
    private DevicesDao devicesDao;

    @Autowired
    private DatasDao datasDao;

    @Scheduled(cron = "0 0 2 * * ?")
    public void backupDevice(){
        List<List<Devices>> resList = new ArrayList<>();
        for (TbGroup tbGroup : tbGroupService.getAllGroup()) {
            List<Devices> devices = ApiParse.parseDevices(tbGroup.getGroupKey());
            resList.add(devices);
        }
        for (List<Devices> devices : resList) {
            for (Devices device : devices) {
                System.out.println(device);
                devicesDao.insertOrUpdateBatch(devices);
            }
        }
    }

    @Scheduled(cron = "0 0 4 * * ?")
    public void backupDatas(){
        List<List<Devices>> resList = new ArrayList<>();
        for (TbGroup tbGroup : tbGroupService.getAllGroup()) {
            List<Devices> devices = ApiParse.parseDevices(tbGroup.getGroupKey());
            for (Devices device : devices) {
                String id = device.getId().toString();
                String key = tbGroup.getGroupKey();
                String datastreams = ApiParse.parseDatastreams(id, key);
                List<List<Datas>> lists = ApiParse.parseDataponints(datastreams, id, 10, key);
                for (List<Datas> list : lists) {
                    datasDao.insertBatch(list);
                }
            }
        }
    }

}
