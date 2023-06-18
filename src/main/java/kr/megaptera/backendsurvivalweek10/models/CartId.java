package kr.megaptera.backendsurvivalweek10.models;

import jakarta.persistence.Embeddable;

@Embeddable
public class CartId extends EntityId {
    // TODO: 계정 별로 카트를 생성할 수 있도록 수정해 주세요.

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
