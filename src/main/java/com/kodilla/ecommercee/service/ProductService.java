package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.domain.EntityNotFoundException;
import com.kodilla.ecommercee.domain.Group;
import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.domain.ProductDto;
import com.kodilla.ecommercee.mapper.ProductMapper;
import com.kodilla.ecommercee.repository.GroupRepository;
import com.kodilla.ecommercee.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private GroupRepository groupRepository;

    public List<ProductDto> getProducts() {
        return productMapper.mapToDtoList(productRepository.findAll());
    }

    public ProductDto getProduct(final Long id) {
        Optional<Product> product = productRepository.findById(id);
        return productMapper.mapToDto(product.orElseThrow(() -> new EntityNotFoundException(Product.class, id)));
    }

    public void deleteProduct(final Long productId) {
        productRepository.findById(productId).orElseThrow(() -> new EntityNotFoundException(Product.class, productId));
        productRepository.deleteById(productId);
    }

    public ProductDto create(ProductDto productDto) {
        Group group = groupRepository.findById(productDto.getGroupId()).orElseThrow(() -> new EntityNotFoundException(Group.class, productDto.getGroupId()));
        productDto.setProductId(null);
        Product product = productMapper.map(productDto, group);
        return productMapper.mapToDto(productRepository.save(product));
    }

    public ProductDto update(ProductDto productDto) {
        productRepository.findById(productDto.getProductId()).orElseThrow(() -> new EntityNotFoundException(Product.class, productDto.getProductId()));
        Group group = groupRepository.findById(productDto.getGroupId()).orElseThrow(() -> new EntityNotFoundException(Group.class, productDto.getGroupId()));
        return productMapper.mapToDto(productRepository.save(productMapper.map(productDto, group)));
    }
}

