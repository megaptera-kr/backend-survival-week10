package kr.megaptera.backendsurvivalweek10.dtos.cart;

public record AddCartLineItemDto(
    String productId,
    int quantity
) {
}
