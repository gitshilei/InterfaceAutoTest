package com.stone.auto.test.interfaces.util.imp;

import com.stone.auto.test.interfaces.util.httpRequest.QResponesData;

public interface ihttpRequest {

    public <Params> QResponesData httpRequest(String url, Params params) throws Exception;


}