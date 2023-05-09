package kr.megaptera.backendsurvivalweek10.models;

public class LineItemId extends EntityId {
    private LineItemId() {
        super();
    }

    public LineItemId(String value) {
        super(value);
    }

    public static LineItemId generate() {
        return new LineItemId(newTsid());
    }
}
