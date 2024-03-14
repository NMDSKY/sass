package com.bage.finance.biz.service.impl;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;
import com.bage.common.exception.BizException;
import com.bage.common.exception.ParameterException;
import com.bage.common.util.DateUtil;
import com.bage.common.util.MyUtil;
import com.bage.finance.biz.constant.RedisKeyConstant;
import com.bage.finance.biz.dto.form.GetBase64CodeForm;
import com.bage.finance.biz.dto.form.GetSmsCodeForm;
import com.bage.finance.biz.service.MemberLoginService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;
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

    /**
     * 获取短信验证码
     *
     * @param form
     * @return
     */
    @Override
    public void sendSmsCode(GetSmsCodeForm form) {
        //todo 校验图形验证码
//        checkBase64Code(form.getClientId(), form.getCode());
//        String key = RedisKeyConstant.SMS_CODE + form.getSmsCodeType() + form.getPhone();
//        SmsCodeResult smsCodeResult = (SmsCodeResult) redisTemplate.opsForValue().get(key);
//        if (smsCodeResult != null) {
//            Duration duration = DateUtil.getDuration(smsCodeResult.getGetTime(), DateUtil.getSystemTime());
//            if (duration.getSeconds() < 60) {
//                throw new BizException("验证码获取太频繁，请稍后重试");
//            }
//        }
//        MemberBindPhone memberBindPhone = memberBindPhoneService.getMemberByPhone(form.getPhone());
//        if (form.getSmsCodeType().equals(SmsCodeTypeEnum.REG.getCode()) && memberBindPhone != null) {
//            throw new ParameterException("phone", "该手机号已注册！");
//        }
//        if (form.getSmsCodeType().equals(SmsCodeTypeEnum.LOGIN.getCode()) && memberBindPhone == null) {
//            throw new ParameterException("phone", "该手机号未注册！");
//        }
//        int smsCode = MyUtil.getRandom(6);
//        smsCodeResult = new SmsCodeResult();
//        smsCodeResult.setCode(String.valueOf(smsCode));
//        smsCodeResult.setGetTime(DateUtil.getSystemTime());
//        redisTemplate.opsForValue().set(key, smsCodeResult, 15, TimeUnit.MINUTES);
//        log.info("客户端id{},手机号：{},短信验证码：{}", form.getClientId(), form.getPhone(), smsCode);
//        smsService.sendSmsCode(form.getPhone(), smsCodeResult.getCode(), form.getSmsCodeType());
    }

}
