package com.dingxianginc.ctu.client.model;
/**
 * Copyright: 2017 dingxiang-inc.com Inc. All rights reserved.
 */

import java.io.Serializable;

/**
 * @author jiang
 * @FileName: Suggestion.java
 * @Description: Suggestion.java类说明
 * @Author: jiangqian
 * @Date: 2017/11/17 19:07
 * @Copyright: 2017 dingxiang-inc.com Inc. All rights reserved.
 */
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
