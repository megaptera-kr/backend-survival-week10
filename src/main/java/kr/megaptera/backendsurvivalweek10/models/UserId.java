package kr.megaptera.backendsurvivalweek10.models;

public class UserId extends EntityId {
    private UserId() {
        super();
    }

    public UserId(String value) {
        super(value);
    }

    public static UserId of(String value) {
        return new UserId(value);
    }
}
