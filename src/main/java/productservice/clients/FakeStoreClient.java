package productservice.clients;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import productservice.dtos.FakeStoreProductRequestDto;
import productservice.models.Product;
import productservice.utility.ConvertDtoToEntity;

import java.util.Arrays;
import java.util.List;

@Component
public class FakeStoreClient {
    private final RestTemplateBuilder restTemplateBuilder;

    public FakeStoreClient(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplateBuilder = restTemplateBuilder;
    }

    private <T> ResponseEntity<T> requestForEntity(HttpMethod httpMethod,
                                                   String url,
                                                   @Nullable Object request,
                                                   Class<T> responseType,
                                                   Object... uriVariables) throws RestClientException {
        RestTemplate restTemplate = this.restTemplateBuilder
                .requestFactory(HttpComponentsClientHttpRequestFactory.class)
                .build();
        RequestCallback requestCallback = restTemplate.httpEntityCallback(request, responseType);
        ResponseExtractor<ResponseEntity<T>> responseExtractor = restTemplate.responseEntityExtractor(responseType);
        return restTemplate.execute(url, httpMethod, requestCallback, responseExtractor, uriVariables);
    }

    public FakeStoreProductDto getASingleProduct(Long productId){
        RestTemplate restTemplate = this.restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto> responseEntity = restTemplate.getForEntity(
                "https://fakestoreapi.com/products/{id}",
                FakeStoreProductDto.class,
                productId
        );
        return responseEntity.getBody();
    }

    public List<FakeStoreProductDto> getAllProducts() {
        RestTemplate restTemplate = this.restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto[]> responseEntity = restTemplate.getForEntity(
                "https://fakestoreapi.com/products",
                FakeStoreProductDto[].class
        );
        return Arrays.asList(responseEntity.getBody());
    }

    public FakeStoreProductDto addNewProduct(FakeStoreProductDto productRequestDto) {
        RestTemplate restTemplate = this.restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto> responseEntity = restTemplate.postForEntity(
                "https://fakestoreapi.com/products",
                productRequestDto,
                FakeStoreProductDto.class
        );
        return responseEntity.getBody();
    }

    public FakeStoreProductDto updateProduct(Long productId, FakeStoreProductDto productRequestDto) {
        ResponseEntity<FakeStoreProductDto> responseEntity =
                requestForEntity(
                        HttpMethod.PUT,
                        "https://fakestoreapi.com/products/{id}",
                        productRequestDto,
                        FakeStoreProductDto.class,
                        productId);
        return responseEntity.getBody();
    }

    public FakeStoreProductDto deleteProduct(Long productId){
        FakeStoreProductDto productDto = getASingleProduct(productId);
        ResponseEntity<FakeStoreProductDto> responseEntity =
                requestForEntity(
                        HttpMethod.DELETE,
                        "https://fakestoreapi.com/products/{id}",
                        productDto,
                        FakeStoreProductDto.class,
                        productId);
        return responseEntity.getBody();
    }

}
