package kr.megaptera.backendsurvivalweek10.application.product;

import kr.megaptera.backendsurvivalweek10.dtos.ProductListDto;
import kr.megaptera.backendsurvivalweek10.infrastructure.ProductDtoFetcher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class GetProductListService {
    private final ProductDtoFetcher productDtoFetcher;

    public GetProductListService(ProductDtoFetcher productDtoFetcher) {
        this.productDtoFetcher = productDtoFetcher;
    }

    public ProductListDto getProductListDto() {
        return productDtoFetcher.fetchProductListDto();
    }
}
