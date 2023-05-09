package kr.megaptera.backendsurvivalweek10.application.product;

import kr.megaptera.backendsurvivalweek10.models.Money;
import kr.megaptera.backendsurvivalweek10.models.Product;
import kr.megaptera.backendsurvivalweek10.repositories.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class CreateProductService {
    private final ProductRepository productRepository;

    public CreateProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product createProduct(String name, Money price) {
        Product product = Product.create(name, price);

        productRepository.save(product);

        return product;
    }
}
