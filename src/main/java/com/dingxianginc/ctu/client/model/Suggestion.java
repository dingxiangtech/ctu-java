package com.dingxianginc.ctu.client.model;
/**
 * Copyright: 2017 dingxiang-inc.com Inc. All rights reserved.
 */

import java.io.Serializable;

public class Suggestion implements Serializable {
    private static final long serialVersionUID = -8497157464470132560L;
    private String code;
    private String message;

    public Suggestion() {
    }

    public Suggestion(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
