package kr.megaptera.backendsurvivalweek10.dtos.cart;

import java.util.List;

public class CartDto {
    private final List<LineItemDto> lineItems;

    public CartDto(List<LineItemDto> lineItems) {
        this.lineItems = lineItems;
    }

    public List<LineItemDto> getLineItems() {
        return lineItems;
    }

    public record LineItemDto(
        String id,
        String productName,
        long unitPrice,
        int quantity,
        long totalPrice
    ) {
    }
}
