package kr.megaptera.backendsurvivalweek10.application.cart;

import kr.megaptera.backendsurvivalweek10.infrastructure.CartDtoFetcher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class GetCartServiceTest {
    private CartDtoFetcher cartDtoFetcher;

    private GetCartService getCartService;
    private final String USER_ID = "USER_ID";

    @BeforeEach
    void setUp() {
        cartDtoFetcher = mock(CartDtoFetcher.class);

        getCartService = new GetCartService(cartDtoFetcher);
    }

    @Test
    void getCartDto() {
        getCartService.getCartDto(USER_ID);

        verify(cartDtoFetcher).fetchCartDto(any());
    }
}
