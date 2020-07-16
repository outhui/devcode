package com.basecode.okhttp.builder;


import com.basecode.okhttp.OKHttpUtils;
import com.basecode.okhttp.request.OtherRequest;
import com.basecode.okhttp.request.RequestCall;

public class HeadBuilder extends GetBuilder {
    @Override
    public RequestCall build() {
        return new OtherRequest(null, null, OKHttpUtils.METHOD.HEAD, url, tag, params, headers, id).build();
    }
}
