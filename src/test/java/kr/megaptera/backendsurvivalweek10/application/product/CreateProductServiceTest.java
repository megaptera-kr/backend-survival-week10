package kr.megaptera.backendsurvivalweek10.application.product;

import kr.megaptera.backendsurvivalweek10.models.Money;
import kr.megaptera.backendsurvivalweek10.models.Product;
import kr.megaptera.backendsurvivalweek10.repositories.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class CreateProductServiceTest {
    private ProductRepository productRepository;

    private CreateProductService createProductService;

    @BeforeEach
    void setUp() {
        productRepository = mock(ProductRepository.class);

        createProductService = new CreateProductService(productRepository);
    }

    @Test
    void createProduct() {
        String name = "제-품";
        Money price = new Money(100_000L);

        Product product = createProductService.createProduct(name, price);

        assertThat(product.name()).isEqualTo(name);
        assertThat(product.price()).isEqualTo(price);

        verify(productRepository).save(product);
    }
}
