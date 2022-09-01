package com.yosypchuk.product.api;

import com.yosypchuk.product.model.dto.ProductDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api("Product management API")
@RequestMapping("/product")
public interface ProductApi {
    @ApiOperation("Get all products")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/all")
    @ApiResponse(code = 200, message = "OK")
    List<ProductDTO> getAllProducts();

    @ApiOperation("Get product by id")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Not Found")
    })
    ProductDTO getProduct(@PathVariable Long id);

    @ApiOperation("Create product")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    @ApiResponses({
            @ApiResponse(code = 201, message = "Created"),
            @ApiResponse(code = 409, message = "Conflict")
    })
    ProductDTO createProduct(@RequestBody @Valid ProductDTO productDTO);

    @ApiOperation("Update product")
    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/update/{id}")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Not Found")
    })
    ProductDTO updateProduct(@PathVariable Long id, @RequestBody @Valid ProductDTO productDTO);

    @ApiOperation("Delete product")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @DeleteMapping("/delete/{id}")
    @ApiResponses({
            @ApiResponse(code = 202, message = "Accepted"),
            @ApiResponse(code = 404, message = "Not Found")
    })
    ResponseEntity<Void> deleteProduct(@PathVariable Long id);
}
