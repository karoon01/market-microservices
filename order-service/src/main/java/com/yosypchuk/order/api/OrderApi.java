package com.yosypchuk.order.api;

import com.yosypchuk.order.model.Order;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api("Order management API")
@RequestMapping("/order")
public interface OrderApi {

    @ApiOperation("Create user order")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/{userId}")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Created"),
            @ApiResponse(code = 409, message = "Conflict")
    })
    Order createOrder(@PathVariable Long userId);

    @ApiOperation("Get all orders")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/all")
    @ApiResponse(code = 200, message = "OK")
    List<Order> getAllOrders();

    @ApiOperation("Get all user's orders")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{userId}/all")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Not Found")
    })
    List<Order> getAllUserOrders(@PathVariable Long userId);

    @ApiOperation("Add order item to order")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/{orderId}/add/{productId}")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Created"),
            @ApiResponse(code = 404, message = "Not Found")
    })
    ResponseEntity<Void> addOrderItemToOrder(@PathVariable Long orderId,
                                             @PathVariable Long productId,
                                             @RequestParam Integer amount);

    @ApiOperation("Delete order item from order")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @DeleteMapping("/{orderItemId}/delete")
    @ApiResponses({
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 202, message = "Accepted")
    })
    ResponseEntity<Void> deleteOrderItemFromOrder(@PathVariable Long orderItemId);
}
