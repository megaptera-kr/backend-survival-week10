package kr.megaptera.backendsurvivalweek10.dtos;

public record AddCartLineItemDto(
    String productId,
    int quantity
) {
}
