package com.yosypchuk.product.api;

import com.yosypchuk.product.model.dto.ProductCategoryDTO;
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

@Api("Product category management API")
@RequestMapping("/product/category")
public interface ProductCategoryApi {

    @ApiOperation("Create product category")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    @ApiResponses({
            @ApiResponse(code = 201, message = "Created"),
            @ApiResponse(code = 409, message = "Conflict")
    })
    ProductCategoryDTO createProductCategory(@RequestBody @Valid ProductCategoryDTO productCategoryDTO);

    @ApiOperation("Get all product categories")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/all")
    @ApiResponse(code = 200, message = "OK")
    List<ProductCategoryDTO> getAllProductCategories();

    @ApiOperation("Remove product category by id")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @DeleteMapping("/{id}")
    @ApiResponses({
            @ApiResponse(code = 202, message = "Accepted"),
            @ApiResponse(code = 404, message = "Not Found")
    })
    ResponseEntity<Void> removeProductCategory(@PathVariable Long id);

    @ApiOperation("Add category to the product")
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/{categoryId}/add/{productId}")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Not Found")
    })
    ResponseEntity<Void> addCategoryToProduct(@PathVariable Long productId, @PathVariable Long categoryId);

    @ApiOperation("Remove category from the product")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @DeleteMapping("/{categoryId}/remove/{productId}")
    @ApiResponses({
            @ApiResponse(code = 202, message = "Accepted"),
            @ApiResponse(code = 404, message = "Not Found")
    })
    ResponseEntity<Void> removeCategoryFromProduct(@PathVariable Long productId, @PathVariable Long categoryId);

    @ApiOperation("Get all products by category")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{categoryId}/products")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Not Found")
    })
    List<ProductDTO> getAllProductsByCategory(@PathVariable Long categoryId);
}
