package com.yosypchuk.order.client;

import com.yosypchuk.order.model.dto.ProductDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
        name = "product-service",
        url = "/product")
public interface ProductFeignClient {

    @GetMapping("/{id}")
    ProductDTO getProduct(@PathVariable Long id);
}
