package com.zhou.base;

import com.alibaba.fastjson.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class BaseController {
    /**
     * 将结果进行json格式化
     * @param result
     * @return
     */
    public static ResponseEntity<String> createResponseEntity(Result result){
        return new ResponseEntity<String>(JSONObject.toJSONString(result), HttpStatus.OK);
    }

    /**
     * 将结果进行json格式化
     * @param result
     * @return
     */
    public static String createResponseString(Result result){
        return JSONObject.toJSONString(result);
    }

    /**
     * 返回json串
     * @param result 列表数据
     * @return
     */
    public static JSONObject createResponseJson(Result result){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code",result.getResultCode().showCode());
        jsonObject.put("msg",result.getResultCode().showMsg());
        jsonObject.put("data",result.getData());
        jsonObject.put("count",result.getCount());
        return jsonObject;
    }

}
