package com.kodilla.ecommercee;


import com.kodilla.ecommercee.domain.CartItemDto;
import com.kodilla.ecommercee.domain.EntityNotFoundException;
import com.kodilla.ecommercee.service.CartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/ecommercee/cartItem")
public class CartItemController {

    @Autowired
    private CartItemService cartItemService;


    @RequestMapping(method = RequestMethod.GET, value = "getCartItems")
    public List<CartItemDto> getCartItems() {
        return cartItemService.getCartItems();
    }

    @RequestMapping(method = RequestMethod.GET, value = "getCartItem")
    public CartItemDto getCartItem(@RequestParam Long id) throws EntityNotFoundException {
        return cartItemService.getCartItem(id);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "deleteCartItem")
    public void deleteCartItem(@RequestParam Long id) {
        cartItemService.deleteCartItem(id);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "updateCartItem")
    public CartItemDto updateCartItem(@RequestBody CartItemDto cartItemDto) throws EntityNotFoundException {
        return cartItemService.update(cartItemDto);
    }

    @RequestMapping(method = RequestMethod.POST, value = "createCartItem",
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public CartItemDto createCartItem(@RequestBody CartItemDto cartItemDto) throws EntityNotFoundException {
        return cartItemService.create(cartItemDto);
    }
}

