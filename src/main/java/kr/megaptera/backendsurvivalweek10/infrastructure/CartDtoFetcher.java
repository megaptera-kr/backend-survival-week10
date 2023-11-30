package kr.megaptera.backendsurvivalweek10.infrastructure;

import kr.megaptera.backendsurvivalweek10.dtos.CartDto;
import kr.megaptera.backendsurvivalweek10.models.CartId;
import kr.megaptera.backendsurvivalweek10.models.UserId;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.util.List;

@Component
public class CartDtoFetcher {
    private final JdbcTemplate jdbcTemplate;

    public CartDtoFetcher(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public CartDto fetchCartDto(UserId userId) {
        String sql = """
            SELECT
                *,
                products.name AS product_name
            FROM line_items
            JOIN products ON line_items.product_id = products.id
            JOIN carts ON line_items.cart_id = carts.id
            WHERE carts.user_id = ?
            ORDER BY line_items.id
            """;

        List<CartDto.LineItemDto> lineItemDtos = jdbcTemplate.query(
                sql,
                (ResultSet resultSet, int rowNum) -> new CartDto.LineItemDto(
                        resultSet.getString("id"),
                        resultSet.getString("product_name"),
                        resultSet.getLong("unit_price"),
                        resultSet.getInt("quantity"),
                        resultSet.getLong("total_price")
                ),
                userId.toString()
        );

        return new CartDto(lineItemDtos);
    }
}
