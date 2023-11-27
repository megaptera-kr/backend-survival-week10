package kr.megaptera.backendsurvivalweek10.application.cart;

import kr.megaptera.backendsurvivalweek10.Fixtures;
import kr.megaptera.backendsurvivalweek10.models.Cart;
import kr.megaptera.backendsurvivalweek10.models.LineItem;
import kr.megaptera.backendsurvivalweek10.models.LineItemId;
import kr.megaptera.backendsurvivalweek10.repositories.CartRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class ChangeCartItemQuantityServiceTest {
    private CartRepository cartRepository;

    private ChangeCartItemQuantityService changeCartItemQuantityService;

    @BeforeEach
    void setUp() {
        cartRepository = mock(CartRepository.class);

        changeCartItemQuantityService =
                new ChangeCartItemQuantityService(cartRepository);
    }

    @Test
    @DisplayName("changeQuantity - when line item exists")
    void changeQuantity() {
        String userId = "USER_ID";
        Cart cart = Fixtures.cart(List.of(Fixtures.product()));

        LineItem lineItem = cart.lineItem(0);
        LineItemId lineItemId = lineItem.id();

        given(cartRepository.findByUserId(any())).willReturn(Optional.of(cart));

        changeCartItemQuantityService.changeQuantity(userId, lineItemId, 10);

        assertThat(lineItem.quantity()).isEqualTo(10);
    }

    @Test
    @DisplayName("changeQuantity - with incorrect item ID")
    void changeQuantityWithIncorrectID() {
        String userId = "USER_ID";
        Cart cart = Fixtures.cart(List.of(Fixtures.product()));

        LineItemId lineItemId = new LineItemId("test-id");

        given(cartRepository.findByUserId(any())).willReturn(Optional.of(cart));

        assertThatThrownBy(() -> {
            changeCartItemQuantityService.changeQuantity(userId, lineItemId, 10);
        });
    }
}
