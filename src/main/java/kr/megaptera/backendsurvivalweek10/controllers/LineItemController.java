package kr.megaptera.backendsurvivalweek10.controllers;

import kr.megaptera.backendsurvivalweek10.application.cart.AddProductToCartService;
import kr.megaptera.backendsurvivalweek10.application.cart.ChangeCartItemQuantityService;
import kr.megaptera.backendsurvivalweek10.application.cart.GetCartService;
import kr.megaptera.backendsurvivalweek10.dtos.AddCartLineItemDto;
import kr.megaptera.backendsurvivalweek10.dtos.CartDto;
import kr.megaptera.backendsurvivalweek10.dtos.ChangeCartLineItemDto;
import kr.megaptera.backendsurvivalweek10.models.LineItemId;
import kr.megaptera.backendsurvivalweek10.models.ProductId;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

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
    public void create(@RequestBody AddCartLineItemDto dto, Principal principal) {
        ProductId productId = new ProductId(dto.productId());
        int quantity = dto.quantity();
        String userId = principal.getName();

        addProductToCartService.addProduct(productId, quantity, userId);
    }

    @PatchMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    public void update(
            @PathVariable("id") String id,
            @RequestBody ChangeCartLineItemDto dto,
            Principal principal) {
        LineItemId lineItemId = new LineItemId(id);
        int quantity = dto.quantity();
        String userId = principal.getName();

        changeCartItemQuantityService.changeQuantity(lineItemId, quantity, userId);
    }

    @ExceptionHandler(NoSuchElementException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String notFound() {
        return "Not Found";
    }
}
