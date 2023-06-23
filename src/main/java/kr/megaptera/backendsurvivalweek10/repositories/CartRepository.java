package kr.megaptera.backendsurvivalweek10.repositories;

import kr.megaptera.backendsurvivalweek10.models.Cart;
import kr.megaptera.backendsurvivalweek10.models.CartId;
import kr.megaptera.backendsurvivalweek10.models.UserId;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CartRepository extends CrudRepository<Cart, CartId> {
    Optional<Cart> findByUserId(UserId userId);
}
