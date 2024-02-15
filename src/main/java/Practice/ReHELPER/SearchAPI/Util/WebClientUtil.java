package Practice.ReHELPER.SearchAPI.Util;

import Practice.ReHELPER.SearchAPI.DTO.SearchDTO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class WebClientUtil {

    private static final String client_id = "D757ij1FPjUwjYav7g9E";
    private static final String client_Secret = "McuFAsRrqH";

    public static WebClient getBaseUrl() {
        return WebClient.builder()
                .baseUrl("https://openapi.naver.com/v1/search")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .defaultHeader("X-Naver-Client-Id", client_id)
                .defaultHeader("X-Naver-Client-Secret", client_Secret)
                .build()
                .mutate()
                .build();
    }

    public SearchDTO searchData(String query) {
        return getBaseUrl().get()
                .uri("/local.json?display=5&sort=comment&query={query}", query)
                .retrieve()
                .bodyToMono(SearchDTO.class)
                .block();
    }
}
