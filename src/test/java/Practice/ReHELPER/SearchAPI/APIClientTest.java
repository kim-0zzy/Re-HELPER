package Practice.ReHELPER.SearchAPI;

import Practice.ReHELPER.SearchAPI.DTO.SearchDTO;
import Practice.ReHELPER.SearchAPI.Util.WebClientUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.web.reactive.function.client.WebClient;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class APIClientTest {

    @Autowired
    private WebClientUtil webClientUtil;

    @Test
    void searchData() {

        SearchDTO searchDTO = webClientUtil.searchData("경기도 김포시 사우동 헬스장");
        System.out.println("searchDTO = " + searchDTO);

    }
}