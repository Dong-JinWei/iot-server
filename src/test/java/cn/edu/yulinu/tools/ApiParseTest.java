package cn.edu.yulinu.tools;

import cn.edu.yulinu.entity.Datas;
import cn.edu.yulinu.entity.Devices;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

import java.util.List;

public class ApiParseTest {

    @Test
    public void testApiParse(){
        List<Devices> devices = ApiParse.parseDevices("zfKDkkEzpQDUAyjiLX=5PND8GxU=");
        for (Devices device : devices) {
            System.out.println("我的测试：" + device);
        }
    }

    @Test
    public void parseDatastreamsTest(){
        String ids = ApiParse.parseDatastreams("1001324328", "zfKDkkEzpQDUAyjiLX=5PND8GxU=");
        System.out.println(ids);
    }

    @Test
    public void parseDataponintsTest(){
        String ids = ApiParse.parseDatastreams("1001324328", "zfKDkkEzpQDUAyjiLX=5PND8GxU=");
        for (List<Datas> list : ApiParse.parseDataponints( ids,"1001324328", 2, "zfKDkkEzpQDUAyjiLX=5PND8GxU=")) {
            for (Datas datas : list) {
                System.out.println(datas);
            }
        }

    }

}
