package kr.megaptera.backendsurvivalweek10.application.cart;

import kr.megaptera.backendsurvivalweek10.models.Cart;
import kr.megaptera.backendsurvivalweek10.models.CartId;
import kr.megaptera.backendsurvivalweek10.models.LineItemId;
import kr.megaptera.backendsurvivalweek10.repositories.CartRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ChangeCartItemQuantityService {
    private final CartRepository cartRepository;

    public ChangeCartItemQuantityService(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    public void changeQuantity(LineItemId lineItemId, int quantity) {
        Cart cart = cartRepository.findById(CartId.DEFAULT).get();

        cart.changeLineItemQuantity(lineItemId, quantity);
    }
}
