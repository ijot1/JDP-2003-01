package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.domain.Group;
import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.domain.ProductDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductMapper {

    public Product map(final ProductDto productDto, final Group group) {
        return Product.builder()
                .id(productDto.getProductId())
                .productName(productDto.getProductName())
                .description(productDto.getDescription())
                .group(group)
                .price(productDto.getPrice())
                .quantity(productDto.getQuantity())
                .build();
    }

    public ProductDto mapToDto(final Product product) {
        return new ProductDto(
                product.getId(),
                product.getProductName(),
                product.getGroup().getId(),
                product.getDescription(),
                product.getQuantity(),
                product.getPrice()
        );
    }

    public List<ProductDto> mapToDtoList(final List<Product> productList) {
        return productList.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }
}

