package kr.megaptera.backendsurvivalweek10.application.cart;

import kr.megaptera.backendsurvivalweek10.dtos.CartDto;
import kr.megaptera.backendsurvivalweek10.infrastructure.CartDtoFetcher;
import kr.megaptera.backendsurvivalweek10.models.CartId;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class GetCartService {
    private final CartDtoFetcher cartDtoFetcher;

    public GetCartService(CartDtoFetcher cartDtoFetcher) {
        this.cartDtoFetcher = cartDtoFetcher;
    }

    public CartDto getCartDto() {
        return cartDtoFetcher.fetchCartDto(CartId.generate());
    }
}
