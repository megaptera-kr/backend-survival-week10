package kr.megaptera.backendsurvivalweek10.controllers;

import kr.megaptera.backendsurvivalweek10.application.cart.AddProductToCartService;
import kr.megaptera.backendsurvivalweek10.application.cart.ChangeCartItemQuantityService;
import kr.megaptera.backendsurvivalweek10.application.cart.GetCartService;
import kr.megaptera.backendsurvivalweek10.dtos.cart.AddCartLineItemDto;
import kr.megaptera.backendsurvivalweek10.dtos.cart.CartDto;
import kr.megaptera.backendsurvivalweek10.dtos.cart.ChangeCartLineItemDto;
import kr.megaptera.backendsurvivalweek10.models.LineItemId;
import kr.megaptera.backendsurvivalweek10.models.ProductId;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("cart-line-items")
public class LineItemController {
    private final GetCartService getCartService;
    private final AddProductToCartService addProductToCartService;
    private final ChangeCartItemQuantityService changeCartItemQuantityService;

    public LineItemController(
        GetCartService getCartService,
        AddProductToCartService addProductToCartService,
        ChangeCartItemQuantityService changeCartItemQuantityService) {
        this.getCartService = getCartService;
        this.addProductToCartService = addProductToCartService;
        this.changeCartItemQuantityService = changeCartItemQuantityService;
    }

    @GetMapping
    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    public CartDto list(Principal principal) {
        String userId = principal.getName();

        return getCartService.getCartDto(userId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    public void create(
            Principal principal,
            @RequestBody AddCartLineItemDto dto) {
        String userId = principal.getName();

        ProductId productId = new ProductId(dto.productId());
        int quantity = dto.quantity();

        addProductToCartService.addProduct(userId, productId, quantity);
    }

    @PatchMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    public void update(
        Principal principal,
        @PathVariable("id") String id,
        @RequestBody ChangeCartLineItemDto dto) {
        String userId = principal.getName();

        LineItemId lineItemId = new LineItemId(id);
        int quantity = dto.quantity();

        changeCartItemQuantityService.changeQuantity(userId, lineItemId, quantity);
    }

    @ExceptionHandler(NoSuchElementException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String notFound() {
        return "Not Found";
    }
}
