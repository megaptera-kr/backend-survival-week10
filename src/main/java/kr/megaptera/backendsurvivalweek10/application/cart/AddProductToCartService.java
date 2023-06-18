package kr.megaptera.backendsurvivalweek10.application.cart;

import kr.megaptera.backendsurvivalweek10.models.Cart;
import kr.megaptera.backendsurvivalweek10.models.CartId;
import kr.megaptera.backendsurvivalweek10.models.Product;
import kr.megaptera.backendsurvivalweek10.models.ProductId;
import kr.megaptera.backendsurvivalweek10.repositories.CartRepository;
import kr.megaptera.backendsurvivalweek10.repositories.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AddProductToCartService {
    private final CartRepository cartRepository;
    private final ProductRepository productRepository;

    public AddProductToCartService(CartRepository cartRepository,
                                   ProductRepository productRepository) {
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
    }

    public Cart addProduct(ProductId productId, int quantity) {
        Product product = productRepository.findById(productId)
                .orElseThrow();

        Cart cart = cartRepository.findById(new CartId(""))
                .orElse(new Cart(new CartId("")));

        cart.addProduct(product, quantity);

        cartRepository.save(cart);

        return cart;
    }
}
