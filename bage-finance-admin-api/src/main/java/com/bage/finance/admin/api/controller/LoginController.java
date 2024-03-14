package com.bage.finance.admin.api.controller;

import com.bage.common.dto.ApiResponse;
import com.bage.finance.biz.dto.form.GetBase64CodeForm;
import com.bage.finance.biz.dto.form.GetSmsCodeForm;
import com.bage.finance.biz.service.MemberLoginService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "用户登录模块")//这是Swagger的注解，用于对API进行描述，其中tags参数指定了API的标签，即"用户登录模块"。
@RestController//这是Spring MVC的注解，它将这个类标记为一个控制器，表示这个类的每个方法的返回值都会直接写入HTTP响应体中，而不是视图渲染。
@RequestMapping(value = "/login")// 这个注解指定了处理请求的基本URL路径，即"/login"。在这个控制器中，所有的请求路径都相对于这个基本路径
@RequiredArgsConstructor//这是Lombok库提供的注解，它会为这个类生成一个构造函数，该构造函数接收所有未初始化的final字段作为参数。
@Slf4j//这是Lombok提供的注解，它会在编译时自动生成一个名为log的Slf4j日志记录器，用于在代码中记录日志。
public class LoginController {
    final MemberLoginService memberLoginService;//使用了final关键字，表示它在初始化后不能再被修改。这个成员变量通常用于注入服务或组件。

    @ApiOperation(value = "获取客户端Id")
    @GetMapping(value = "/getClientId")
    public ApiResponse<String> getClientId() {
        String result = memberLoginService.getClientId();
        return ApiResponse.success(result);
    }

    @ApiOperation(value = "获取图形验证码")
    @GetMapping(value = "/getBase64Code")
    public ApiResponse<String> getBase64Code(@Validated @ModelAttribute GetBase64CodeForm form) {
        String code = memberLoginService.getBase64Code(form);
        return ApiResponse.success(code);
    }

    @ApiOperation(value = "获取短信验证码")
    @GetMapping(value = "/sendSmsCode")
    public ApiResponse<Void> sendSmsCode(@Validated @ModelAttribute GetSmsCodeForm form) {
        memberLoginService.sendSmsCode(form);
        return ApiResponse.success();
    }
}