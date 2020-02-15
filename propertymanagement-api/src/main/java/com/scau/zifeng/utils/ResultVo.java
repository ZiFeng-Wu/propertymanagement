package com.scau.zifeng.utils;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;


public class ResultVo {

    /**
     * 是否成功
     */
    private boolean success = true;

    /**
     * 需要返回到前台的数据
     */
    private Object data;

    /**
     * 消息
     */
    private String msg = "成功";

    /**
     * 错误编码
     */
    private String errorCode = "";

    public ResultVo(boolean success, String msg) {
        this.success = success;
        this.msg = msg;
    }


    public ResultVo(Object data) {
        this.data = data;
    }

    public ResultVo() {
    }


    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

    public String toJSONString() {
        JSONObject obj = new JSONObject();
        if (this.isSuccess()) {
            obj.put("data", getData() == null ? "" : getData());
            obj.put("success", true);
        } else {
            JSONObject errorObj = new JSONObject();
            errorObj.put("code", getErrorCode());
            errorObj.put("message", getMsg());
            obj.put("error", errorObj);
            obj.put("success", false);
        }
        return JSONObject.toJSONString(obj, SerializerFeature.WriteMapNullValue);
    }
    public String toJSONStringWithOutNull() {
        JSONObject obj = new JSONObject();
        if (this.isSuccess()) {
            obj.put("data", getData() == null ? "" : getData());
            obj.put("success", true);
        } else {
            JSONObject errorObj = new JSONObject();
            errorObj.put("code", getErrorCode());
            errorObj.put("message", getMsg());
            obj.put("error", errorObj);
            obj.put("success", false);
        }
        return JSONObject.toJSONString(obj);
    }


    public boolean isSuccess() {
        return success;
    }


    public void setSuccess(boolean success) {
        this.success = success;
    }


    public String getMsg() {
        return msg;
    }


    public void setMsg(String msg) {
        this.msg = msg;
    }


    public Object getData() {
        return data;
    }


    public void setData(Object data) {
        this.data = data;
    }


    public String getErrorCode() {
        return errorCode;
    }


    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }
}

