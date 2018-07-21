/*
 * Copyright: 2017 dingxiang-inc.com Inc. All rights reserved.
 */

package com.dingxianginc.ctu.client.model;

import java.io.Serializable;
import java.util.Map;

/**
 * Created by dingxiang-inc on 2017/5/5.
 */
public class CtuRequest implements Serializable {
    private static final long serialVersionUID = -6582668008857059475L;
    private String eventCode;            // 事件code
    private String flag;                 // 客户端请求标记,用来标识该次请求
    private Map<String, Object> data;    // 事件参数

    public String getEventCode() {
        return eventCode;
    }

    public void setEventCode(String eventCode) {
        this.eventCode = eventCode;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "CtuRequest{" +
                "eventCode='" + eventCode + '\'' +
                ", data=" + data +
                ", flag='" + flag + '\'' +
                '}';
    }
}
