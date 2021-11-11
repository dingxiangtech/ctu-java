/*
 * Copyright: 2017 dingxiang-inc.com Inc. All rights reserved.
 */

package com.dingxianginc.ctu.client.model;

import java.io.Serializable;

public class CtuResponse implements Serializable {
    private String uuid;
    private String status;
    private CtuEntireResult ctuEntireResult;


    public CtuResponse() {
    }

    public CtuResponse(String uuid) {
        this.uuid = uuid;
    }

    public CtuResponse(String uuid, CtuEntireResult ctuEntireResult) {
        this(uuid, ctuEntireResult, "SUCCESS");
    }

    public CtuResponse(String uuid, CtuEntireResult ctuEntireResult, String status) {
        this.uuid = uuid;
        this.ctuEntireResult = ctuEntireResult;
        this.status = status;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public CtuEntireResult getResult() {
        return ctuEntireResult;
    }

    public void setResult(CtuEntireResult ctuEntireResult) {
        this.ctuEntireResult = ctuEntireResult;
    }
}
