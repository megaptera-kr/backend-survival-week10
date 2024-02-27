package kr.megaptera.backendsurvivalweek10.controllers;

import kr.megaptera.backendsurvivalweek10.application.product.CreateProductService;
import kr.megaptera.backendsurvivalweek10.application.product.GetProductListService;
import kr.megaptera.backendsurvivalweek10.dtos.ProductListDto;
import kr.megaptera.backendsurvivalweek10.models.Money;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static kr.megaptera.backendsurvivalweek10.controllers.helpers.ResultMatchers.contentContains;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductController.class)
@ActiveProfiles("test")
class ProductControllerTest extends ControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GetProductListService getProductListService;

    @MockBean
    private CreateProductService createProductService;

    @Test
    @DisplayName("GET /products")
    void list() throws Exception {
        ProductListDto.ProductDto productDto =
                new ProductListDto.ProductDto("test-id", "제품", 100_000L);

        given(getProductListService.getProductListDto()).willReturn(
                new ProductListDto(List.of(productDto)));

        mockMvc.perform(get("/products")
                        .header("Authorization", "Bearer " + userAccessToken))
                .andExpect(status().isOk())
                .andExpect(contentContains("제품"));
    }

    @Test
    @DisplayName("POST /products - success with ROLE_ADMIN ")
    void createWithROLE_ADMIN() throws Exception {
        String json = String.format(
                """
                        {
                            "name": "멋진 제품",
                            "price": %d
                        }
                        """,
                100_000L
        );

        mockMvc.perform(post("/products")
                        .header("Authorization", "Bearer " + adminAccessToken)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isCreated());

        verify(createProductService)
                .createProduct("멋진 제품", new Money(100_000L));
    }

    @Test
    @DisplayName("POST /products - fail with ROLE_USER ")
    void createWithUSER_ADMIN() throws Exception {
        String json = String.format(
                """
                        {
                            "name": "멋진 제품",
                            "price": %d
                        }
                        """,
                100_000L
        );

        mockMvc.perform(post("/products")
                        .header("Authorization", "Bearer " + userAccessToken)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isForbidden());
    }
}
