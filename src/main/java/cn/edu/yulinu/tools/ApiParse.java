package cn.edu.yulinu.tools;

import cmcc.iot.onenet.javasdk.api.datapoints.GetDatapointsListApi;
import cmcc.iot.onenet.javasdk.api.datastreams.FindDatastreamListApi;
import cmcc.iot.onenet.javasdk.api.device.FindDevicesListApi;
import cmcc.iot.onenet.javasdk.response.BasicResponse;
import cmcc.iot.onenet.javasdk.response.datapoints.DatapointsList;
import cmcc.iot.onenet.javasdk.response.datastreams.DatastreamsResponse;
import cmcc.iot.onenet.javasdk.response.device.DeviceList;
import cn.edu.yulinu.entity.Datas;
import cn.edu.yulinu.entity.Devices;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ApiParse {

    private ApiParse(){

    }


    /**
     * 根据产品的key获取该产品的所有设备并转化为对象
     * @param key 产品的key
     * @return
     */
    public static List<Devices> parseDevices(String key){
        // "zfKDkkEzpQDUAyjiLX=5PND8GxU="
        FindDevicesListApi api = new FindDevicesListApi(null, null, null, null, null, null, null,
                null, null, null, null, null, key);
        BasicResponse<DeviceList> response = api.executeApi();
        String json = response.getJson();
        JSONObject object = JSONObject.parseObject(json);
        String data = object.getString("data");

        object = JSONObject.parseObject(data);
        JSONArray devices = object.getJSONArray("devices");

        List<Devices> resList = new ArrayList<>();

        for (Object device : devices) {
            Devices myDevices = new Devices();
            object = JSONObject.parseObject(device.toString());
            myDevices.setId(Integer.valueOf(object.getString("id")));
            myDevices.setTitle(object.getString("title"));
            myDevices.setOnline(object.getString("online"));
            myDevices.setLastLogin(object.getString("last_login"));
            myDevices.setActTime(object.getString("act_time"));
            myDevices.setCreateTime(object.getString("create_time"));

            resList.add(myDevices);
        }
        return resList;
    }


    public static String parseDatastreams(String devId, String key){
        // "1001324328"
        // "zfKDkkEzpQDUAyjiLX=5PND8GxU="
        FindDatastreamListApi api = new FindDatastreamListApi(null, devId , key);
        BasicResponse<List<DatastreamsResponse>> response = api.executeApi();
        String json = response.getJson();
        JSONObject jsonObject = JSONObject.parseObject(json);
        JSONArray data = jsonObject.getJSONArray("data");
        List<String> list = new ArrayList<>();
        for (Object datum : data) {
            JSONObject datumJson = JSONObject.parseObject(datum.toString());
            String id = datumJson.getString("id");
            list.add(id);
        }
        list.remove("all1");
        list.remove("heart");

        String ids = list.toString();
        ids = ids.replace("[", "");
        ids = ids.replace("]", "");
        ids = ids.replace(" ", "");
        return ids;
    }


    public static List<List<Datas>> parseDataponints(String datastreamIds, String devId, Integer limit, String key){
        GetDatapointsListApi api;
        api = new GetDatapointsListApi(datastreamIds, null, null, devId, null, limit, null, null, null, null, null, key);
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
                datas.setDeviceId(devId);
                datas.setSensorId(id);
                JSONObject datapointJson = JSONObject.parseObject(datapoint.toString());
                datas.setUpdateAt(datapointJson.getString("at"));
                datas.setCurrentValue(datapointJson.getString("value"));
                datasList.add(datas);
            }
            resList.add(datasList);
        }
        return resList;
    }
}
