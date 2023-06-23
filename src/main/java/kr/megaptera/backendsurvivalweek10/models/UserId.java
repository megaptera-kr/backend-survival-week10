package kr.megaptera.backendsurvivalweek10.models;

public class UserId extends EntityId {
    private UserId() {
        super();
    }

    public static UserId of(String value) {
        return new UserId(value);
    }

    public UserId(String value) {
        super(value);
    }

    public static UserId generate() {
        return new UserId(newTsid());
    }
}
