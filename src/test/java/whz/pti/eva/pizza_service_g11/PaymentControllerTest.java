package whz.pti.eva.pizza_service_g11;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PaymentControllerTest {
    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;
    @Autowired
    private TestRestTemplate testRestTemplate;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(wac)
                .build();
    }

    @Test
    public void testDoPayAction() throws Exception {

        mockMvc.perform(post("/pay")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .param("totalAmount", "150")
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(view().name("checkout"))
                .andDo(print());







     /*   String url = "http://localhost:9090"  + "/users";

        String username = "plainTextPassword";
        String password = "123";

        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setContentType(MediaType.APPLICATION_JSON);
        requestHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));


        TransferDTO addUserRequest = new TransferDTO(80);

        HttpEntity<AddUserRequest> requestEntity = new HttpEntity<>(addUserRequest, requestHeaders);

        //basic authentication is made with  'withBasicAuth' method available in the TestRestTemplate
        ResponseEntity<AddUserResponse> responseEntity = testRestTemplate.withBasicAuth(username, password)
                .exchange(
                        url,
                        HttpMethod.POST,
                        requestEntity,
                        AddUserResponse.class
                );

        if (responseEntity.getStatusCode() == HttpStatus.OK)
        {
            AddUserResponse addUserResponse = responseEntity.getBody();
            System.out.println(addUserResponse);
        }*/
    }


}
