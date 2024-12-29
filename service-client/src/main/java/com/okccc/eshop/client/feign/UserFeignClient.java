package com.okccc.eshop.client.feign;

import com.okccc.eshop.client.config.FeignConfig;
import com.okccc.eshop.model.entity.user.UserAddress;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @Author: okccc
 * @Date: 2024/7/9 18:51:26
 * @Desc:
 */
@FeignClient(value = "service-user", configuration = FeignConfig.class)
public interface UserFeignClient {

    @Operation(summary = "根据id查询地址")
    @GetMapping(value = "/api/user/userAddress/getUserAddress/{id}")
    UserAddress getUserAddressById(@PathVariable("id") Long id);
}
