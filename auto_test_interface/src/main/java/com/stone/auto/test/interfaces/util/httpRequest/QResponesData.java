package com.stone.auto.test.interfaces.util.httpRequest;

import com.alibaba.fastjson.JSONObject;

public class QResponesData {
    private Integer qStatus;
    private JSONObject qBody;
    private Integer qTime;
    private Long qSize;

    public Integer getqStatus() {
        return qStatus;
    }

    public void setqStatus(Integer qStatus) {
        this.qStatus = qStatus;
    }

    public JSONObject getqBody() {
        return qBody;
    }

    public void setqBody(JSONObject qBody) {
        this.qBody = qBody;
    }

    public Integer getqTime() {
        return qTime;
    }

    public void setqTime(Integer qTime) {
        this.qTime = qTime;
    }

    public Long getqSize() {
        return qSize;
    }

    public void setqSize(Long qSize) {
        this.qSize = qSize;
    }



}
