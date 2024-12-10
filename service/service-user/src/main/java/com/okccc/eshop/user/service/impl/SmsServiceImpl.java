package com.okccc.eshop.user.service.impl;

import com.okccc.eshop.common.constant.RedisConstant;
import com.okccc.eshop.common.result.ResultCodeEnum;
import com.okccc.eshop.common.handler.MyRuntimeException;
import com.okccc.eshop.user.util.HttpUtils;
import com.okccc.eshop.user.service.SmsService;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.http.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @Author: okccc
 * @Date: 2024/5/22 18:13:09
 * @Desc: 购买阿里云发送手机短信服务
 * 参考文档：https://market.aliyun.com/products/57124001/cmapi00037170.html?spm=5176.2020520132.101.17.3f857218FL9yug#sku=yuncode3117000001
 */
@Service
public class SmsServiceImpl implements SmsService {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Override
    public void sendSmsCode(String phone) {
        // 这里为了节省购买的阿里云服务的免费试用次数,先手动往redis放一个固定值,拿到就直接return,拿不到再调接口
        String code = redisTemplate.opsForValue().get(phone);
        if (StringUtils.hasText(code)) {
            return;
        }

        // 生成四位数随机验证码
        code = RandomStringUtils.randomNumeric(4);

        // 将验证码放到redis,并设置过期时间
        redisTemplate.opsForValue().set(phone, code, RedisConstant.APP_SMS_TTL_SEC, TimeUnit.SECONDS);

        // 向手机号发送短信验证码
        sendMessage(phone, code);
    }

    private void sendMessage(String phone, String code) {
        String host = "https://dfsns.market.alicloudapi.com";
        String path = "/data/send_sms";
        String appcode = "938a8491cea74e43a48edf0a67070051";
        Map<String, String> headers = new HashMap<>();
        // 最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
        headers.put("Authorization", "APPCODE " + appcode);
        // 根据API的要求定义对应的Content-Type
        headers.put("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
        Map<String, String> querys = new HashMap<>();
        Map<String, String> bodys = new HashMap<>();
        bodys.put("content", "code:" + code);
        bodys.put("template_id", "CST_100");
        bodys.put("phone_number", phone);

        try {
            HttpResponse response = HttpUtils.doPost(host, path, headers, querys, bodys);
            System.out.println(response.toString());
        } catch (Exception e) {
            throw new MyRuntimeException(ResultCodeEnum.FAIL);
        }
    }

}
