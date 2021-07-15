/*
 * Copyright: 2017 dingxiang-inc.com Inc. All rights reserved.
 */

package com.dingxianginc.ctu.client.model;

import java.io.Serializable;

public class HitRule implements Serializable {
    private Long id;
    private String leftValue;

    public HitRule() {
    }

    public HitRule(Long id, String leftValue) {
        this.id = id;
        this.leftValue = leftValue;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLeftValue() {
        return leftValue;
    }

    public void setLeftValue(String leftValue) {
        this.leftValue = leftValue;
    }
}
