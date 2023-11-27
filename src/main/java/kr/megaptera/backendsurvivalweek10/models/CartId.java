package kr.megaptera.backendsurvivalweek10.models;

import jakarta.persistence.Embeddable;

@Embeddable
public class CartId extends EntityId {

    private CartId() {
        super();
    }

    public CartId(String value) {
        super(value);
    }

    public static CartId generate() {
        return new CartId(newTsid());
    }
}
