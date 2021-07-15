package ink.hutao.manage.config;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import ink.hutao.manage.entity.po.Plates;
import ink.hutao.manage.utils.HttpUtils;
import lombok.Data;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>osr-文字识别系统-车牌号识别</p>
 * @author tfj
 * @since 2021/7/14
 */
@Data
@Configuration
public class OsrConfig {

    @Value("${ali.osr.host}")
    private String host;

    @Value("${ali.osr.path}")
    private String path;

    @Value("${ali.osr.appcode}")
    private String appcode;

    @Value("${ali.osr.method}")
    private String method;

    public Plates getPlants(String imagesUrl) throws Exception {
        JSONObject configObj = new JSONObject();
        configObj.put("multi_crop", false);
        String config = configObj.toString();

        Map<String, String> headers = new HashMap<>(1);
        //appcode个人身份标识
        headers.put("Authorization", "APPCODE " + appcode);
        Map<String, String> query = new HashMap<>(0);
        //requestBody
        JSONObject requestObj = new JSONObject();
        requestObj.put("image", imagesUrl);
        if(config.length() > 0) {
            requestObj.put("configure", config);
        }
        String body = requestObj.toString();
        //向host接口地址发送POST请求
        HttpResponse response= HttpUtils.doPost(host,path,method,headers,query,body);
        //获取返回值解析
        String res= EntityUtils.toString(response.getEntity());
        com.alibaba.fastjson.JSONObject jsonObject = JSON.parseObject(res);
        //使用for循环获取json对象中的数组数据
        JSONArray plates = jsonObject.getJSONArray("plates");
        for (int i = 0; i < plates.size();) {
            JSONObject jsonObject1 = plates.getJSONObject(i);
            String txt= (String) jsonObject1.get("txt");
            String clsName= (String) jsonObject1.get("cls_name");
            Plates carInfo=new Plates();
            carInfo.setClsName(clsName);
            carInfo.setTxt(txt);
            return carInfo;
        }
        return null;
    }

}
