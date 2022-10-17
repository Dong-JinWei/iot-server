package cmcc.iot.onenet.javasdk.api;

import cmcc.iot.onenet.javasdk.api.datapoints.GetDatapointsListApi;
import cmcc.iot.onenet.javasdk.api.datastreams.FindDatastreamListApi;
import cmcc.iot.onenet.javasdk.api.device.FindDevicesListApi;
import cmcc.iot.onenet.javasdk.api.device.GetDevicesStatus;
import cmcc.iot.onenet.javasdk.api.dtu.DeleteDtuParser;
import cmcc.iot.onenet.javasdk.response.BasicResponse;
import cmcc.iot.onenet.javasdk.response.datapoints.DatapointsList;
import cmcc.iot.onenet.javasdk.response.datastreams.DatastreamsResponse;
import cmcc.iot.onenet.javasdk.response.device.DeviceList;
import cmcc.iot.onenet.javasdk.response.device.DevicesStatusList;
import cn.edu.yulinu.entity.Datas;
import cn.edu.yulinu.entity.Devices;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import jdk.nashorn.internal.scripts.JS;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;


public class ApiTest {

    @Test
    public void testDeleteDtuParserApi() {
        String key = "zfKDkkEzpQDUAyjiLX=5PND8GxU=";
        Integer id = 385;


        /**
         * TCP透传查询
         * @param name： 名字,精确匹配名字（可选）,String
         * @param key:masterkey 或者 该设备的设备apikey
         */
        DeleteDtuParser api = new DeleteDtuParser(id, key);
        BasicResponse<Void> response = api.executeApi();
        System.out.println("errno:" + response.errno + " error:" + response.error);
    }

    @Test
    public void testGetDevicesStatus() {
        GetDevicesStatus api = new GetDevicesStatus("1000108837", "zfKDkkEzpQDUAyjiLX=5PND8GxU=");
        BasicResponse<DevicesStatusList> response = api.executeApi();
        System.out.println(response.getJson());
    }

    @Test
    public void testFindDevicesListApi() {
        FindDevicesListApi api = new FindDevicesListApi(null, null, null, null, null, null, null,
                null, null, null, null, null, "zfKDkkEzpQDUAyjiLX=5PND8GxU=");
        BasicResponse<DeviceList> response = api.executeApi();
        // System.out.println(response.getJson());
        String json = response.getJson();
        JSONObject object = JSONObject.parseObject(json);
        String data = object.getString("data");
        System.out.println(data);

        object = JSONObject.parseObject(data);
        JSONArray devices = object.getJSONArray("devices");
        System.out.println("devices: " + devices);

        for (Object device : devices) {
            System.out.println(device);
            object = JSONObject.parseObject(device.toString());
            Devices myDevices = new Devices();
            myDevices.setId(Integer.valueOf(object.getString("id")));
            myDevices.setTitle(object.getString("title"));
            myDevices.setOnline(object.getString("online"));
            myDevices.setLastLogin(object.getString("last_login"));
            myDevices.setActTime(object.getString("act_time"));
            myDevices.setCreateTime(object.getString("create_time"));
            // System.out.println(object.getString("id") + ":" +
            //         object.getString("title") + ":" +
            //         object.getString("online") + ":" +
            //         object.getString("last_login") + ":" +
            //         object.getString("act_time") + ":" +
            //         object.getString("create_time"));
            System.out.println(myDevices);
        }
    }


    /**
     * 查询数据流
     */
    @Test
    public void testFindDatastreamListApi(){
        FindDatastreamListApi api = new FindDatastreamListApi(null, "1001324328", "zfKDkkEzpQDUAyjiLX=5PND8GxU=");
        BasicResponse<List<DatastreamsResponse>> response = api.executeApi();
        String json = response.getJson();
        JSONObject jsonObject = JSONObject.parseObject(json);
        JSONArray data = jsonObject.getJSONArray("data");
        List<String> ids = new ArrayList<>();
        for (Object datum : data) {
            JSONObject datumJson = JSONObject.parseObject(datum.toString());
            String id = datumJson.getString("id");
            ids.add(id);
        }
        System.out.println(ids);
    }

    /**
     * 批量查询设备数据
     */
    @Test
    public void testGetDatapointsListApi(){
        GetDatapointsListApi api;
        api = new GetDatapointsListApi("water1,tamperature1", null, null, "1001324328", null, 2, null, null, null, null, "ASC", "zfKDkkEzpQDUAyjiLX=5PND8GxU=");
        BasicResponse<DatapointsList> response = api.executeApi();
        String json = response.getJson();
        JSONObject jsonObject = JSONObject.parseObject(json);
        String object = jsonObject.getString("data");
        JSONObject data = JSONObject.parseObject(object);
        JSONArray datastreams = data.getJSONArray("datastreams");

        List<List<Datas>> resList  = new ArrayList<>();
        for (Object datastream : datastreams) {

            JSONObject datastreamJson = JSONObject.parseObject(datastream.toString());
            String id = datastreamJson.getString("id");

            JSONArray datapoints = datastreamJson.getJSONArray("datapoints");
            List<Datas> datasList = new ArrayList<>();
            for (Object datapoint : datapoints) {
                Datas datas = new Datas();
                datas.setDeviceId("1001324328");
                datas.setSensorId(id);
                JSONObject datapointJson = JSONObject.parseObject(datapoint.toString());
                datas.setUpdateAt(datapointJson.getString("at"));
                datas.setCurrentValue(datapointJson.getString("value"));
                datasList.add(datas);
            }
            resList.add(datasList);
        }
        System.out.println(resList);
    }
}

