package kr.megaptera.backendsurvivalweek10.models;

public class UserId extends EntityId {
    private UserId() {
        super();
    }

    public static UserId of(String userId) {
        return new UserId(userId);
    }

    public UserId(String value) {
        super(value);
    }

    public static UserId generate() {
        return new UserId(newTsid());
    }
}