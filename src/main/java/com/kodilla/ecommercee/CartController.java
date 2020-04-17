package com.kodilla.ecommercee;

import com.kodilla.ecommercee.domain.CartDto;
import com.kodilla.ecommercee.domain.EntityNotFoundException;
import com.kodilla.ecommercee.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/ecommercee/cart")
public class CartController {

    @Autowired
    CartService cartService;

    @RequestMapping(method = RequestMethod.GET, value = "getCarts")
    public List<CartDto> getCartItems() {
        return cartService.getCarts();
    }


    @RequestMapping(method = RequestMethod.GET, value = "getCart")
    public CartDto getCart(@RequestParam Long id) {
        return cartService.getCart(id);
    }

    //    @RequestMapping(method = RequestMethod.DELETE, value = "deleteCart")
//    public void deleteCart(@RequestParam Long id) {
//        System.out.println("Cart has been deleted");
//    }
//
    @RequestMapping(method = RequestMethod.PUT, value = "updateCart")
    public CartDto updateCartItem(@RequestBody CartDto cartDto) throws EntityNotFoundException {
        return cartService.update(cartDto);
    }

    //
    @RequestMapping(method = RequestMethod.POST, value = "createCart")
    public void createCart(@RequestBody CartDto cartDto) {
        cartService.createCart(cartDto);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "deleteCart")
    public void deleteCart(@RequestParam Long id) {
        cartService.deleteCart(id);

    }
}
