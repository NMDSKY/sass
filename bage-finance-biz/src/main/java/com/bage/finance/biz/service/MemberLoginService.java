package com.bage.finance.biz.service;

import com.bage.common.dto.TokenResponse;
import com.bage.finance.biz.dto.form.GetBase64CodeForm;

public interface MemberLoginService {
    /**
     * 获取客户端id
     *
     * @return
     */
    String getClientId();


    String getBase64Code(GetBase64CodeForm form);
}
