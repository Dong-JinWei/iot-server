package cn.edu.yulinu.backup;

import cn.edu.yulinu.IotSpringBootApplication;
import cn.edu.yulinu.dao.DatasDao;
import cn.edu.yulinu.dao.DevicesDao;
import cn.edu.yulinu.entity.Datas;
import cn.edu.yulinu.entity.Devices;
import cn.edu.yulinu.entity.TbGroup;
import cn.edu.yulinu.service.TbGroupService;
import cn.edu.yulinu.tools.ApiParse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = IotSpringBootApplication.class)
public class BackupTest {

    @Autowired
    private TbGroupService tbGroupService;

    @Autowired
    private DatasDao datasDao;

    @Autowired
    private DevicesDao devicesDao;


    @Test
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

    @Test
    public void backupDatas(){
        List<List<Devices>> resList = new ArrayList<>();
        for (TbGroup tbGroup : tbGroupService.getAllGroup()) {
            List<Devices> devices = ApiParse.parseDevices(tbGroup.getGroupKey());
            for (Devices device : devices) {
                String id = device.getId().toString();
                String key = tbGroup.getGroupKey();
                String datastreams = ApiParse.parseDatastreams(id, key);
                List<List<Datas>> lists = ApiParse.parseDataponints(datastreams, id, 100, key);
                for (List<Datas> list : lists) {
                    datasDao.insertBatch(list);
                }
            }
        }
    }

}
