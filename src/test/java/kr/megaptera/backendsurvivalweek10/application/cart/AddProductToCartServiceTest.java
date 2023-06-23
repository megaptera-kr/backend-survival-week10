package kr.megaptera.backendsurvivalweek10.application.cart;

import kr.megaptera.backendsurvivalweek10.Fixtures;
import kr.megaptera.backendsurvivalweek10.models.Cart;
import kr.megaptera.backendsurvivalweek10.models.Product;
import kr.megaptera.backendsurvivalweek10.models.ProductId;
import kr.megaptera.backendsurvivalweek10.repositories.CartRepository;
import kr.megaptera.backendsurvivalweek10.repositories.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class AddProductToCartServiceTest {
    private CartRepository cartRepository;
    private ProductRepository productRepository;

    private AddProductToCartService addProductToCartService;

    @BeforeEach
    void setUp() {
        cartRepository = mock(CartRepository.class);
        productRepository = mock(ProductRepository.class);

        addProductToCartService = new AddProductToCartService(
                cartRepository, productRepository);
    }

    @Test
    @DisplayName("addProduct - when cart doesn't exist")
    void cartNotExists() {
        Product product = Fixtures.product();
        ProductId productId = product.id();

        given(cartRepository.findByUserId(any())).willReturn(Optional.empty());
        given(productRepository.findById(productId))
                .willReturn(Optional.of(product));

        Cart cart = addProductToCartService.addProduct(productId, 1, "UserID");

        assertThat(cart.lineItemsSize()).isEqualTo(1);

        verify(cartRepository).save(cart);
    }

    @Test
    @DisplayName("addProduct - when cart exists")
    void cartExists() {
        Cart cart = Fixtures.cart();

        Product product = Fixtures.product();
        ProductId productId = product.id();

        given(cartRepository.findByUserId(any())).willReturn(Optional.of(cart));
        given(productRepository.findById(productId))
                .willReturn(Optional.of(product));

        addProductToCartService.addProduct(productId, 1, "UserID");

        assertThat(cart.lineItemsSize()).isEqualTo(1);
    }

    @Test
    @DisplayName("addProduct - when product doesn't exist")
    void productNotExists() {
        ProductId productId = new ProductId("test-product-id");

        given(productRepository.findById(productId))
                .willReturn(Optional.empty());

        assertThatThrownBy(() -> {
            addProductToCartService.addProduct(productId, 1, "UserID");
        });
    }
}
