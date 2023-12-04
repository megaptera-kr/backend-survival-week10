package kr.megaptera.backendsurvivalweek10.repositories;

import kr.megaptera.backendsurvivalweek10.models.Product;
import kr.megaptera.backendsurvivalweek10.models.ProductId;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductRepository extends CrudRepository<Product, ProductId> {
    List<Product> findAll();
}