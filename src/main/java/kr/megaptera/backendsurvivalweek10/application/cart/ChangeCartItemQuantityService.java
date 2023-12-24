package kr.megaptera.backendsurvivalweek10.application.cart;

import jakarta.transaction.Transactional;
import kr.megaptera.backendsurvivalweek10.models.Cart;
import kr.megaptera.backendsurvivalweek10.models.LineItemId;
import kr.megaptera.backendsurvivalweek10.models.UserId;
import kr.megaptera.backendsurvivalweek10.repositories.CartRepository;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ChangeCartItemQuantityService {
    private final CartRepository cartRepository;

    public ChangeCartItemQuantityService(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    public void changeQuantity(LineItemId lineItemId, int quantity, String userId) {
        Cart cart = cartRepository.findByUserId(UserId.of(userId)).get();

        cart.changeLineItemQuantity(lineItemId, quantity);
    }
}
