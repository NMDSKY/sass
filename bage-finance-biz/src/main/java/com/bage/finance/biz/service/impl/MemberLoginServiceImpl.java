package com.bage.finance.biz.service.impl;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;
import com.bage.finance.biz.constant.RedisKeyConstant;
import com.bage.finance.biz.dto.form.GetBase64CodeForm;
import com.bage.finance.biz.service.MemberLoginService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import java.util.UUID;
import java.util.concurrent.TimeUnit;


@Service
@Slf4j
@RequiredArgsConstructor
public class MemberLoginServiceImpl implements MemberLoginService {
    final RedisTemplate<String, Object> redisTemplate;

    /**
     * 获取客户端id
     *
     * @return
     */
    @Override
    public String getClientId() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    @Override
    public String getBase64Code(GetBase64CodeForm form) {
        LineCaptcha lineCaptcha = CaptchaUtil.createLineCaptcha(300, 192, 5, 1000);
        String code = lineCaptcha.getCode();
        // 将图形验证码写到redis缓存中
        redisTemplate.opsForValue().set(RedisKeyConstant.GRAPHIC_VERIFICATION_CODE + form.getClientId(),
                code, 15, TimeUnit.MINUTES);
        log.info("图形验证码{}", code);
        return lineCaptcha.getImageBase64();
    }


}
