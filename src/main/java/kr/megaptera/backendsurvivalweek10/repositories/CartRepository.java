package kr.megaptera.backendsurvivalweek10.repositories;

import kr.megaptera.backendsurvivalweek10.models.Cart;
import kr.megaptera.backendsurvivalweek10.models.CartId;
import org.springframework.data.repository.CrudRepository;

public interface CartRepository extends CrudRepository<Cart, CartId> {
}
