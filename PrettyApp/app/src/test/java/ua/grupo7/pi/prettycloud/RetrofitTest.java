package ua.grupo7.pi.prettycloud;


import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import ua.grupo7.pi.prettycloud.communication.PrettyCloudWebService;
import ua.grupo7.pi.prettycloud.models.Client;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class RetrofitTest {

    @Autowired
    private MockMvc mvc;

    private Retrofit retrofit;

    private PrettyCloudWebService webService;

    @Before
    public void init(){
        webService = retrofit.create(PrettyCloudWebService.class);
    }
    @Test
    public void givenClients_whenGetClients_thenReturnJsonArray() throws Exception {

        Client alex = new Client();
        alex.setFirstName("Alex");
        alex.setLastName("Simon");
        alex.setPhoneNumber("123456789");
        alex.setEmail("alex@mail.com");

        List<Client> allClients = Arrays.asList(alex);

        given(webService.getClients()).willReturn((Call<List<Client>>) allClients);

        mvc.perform(get("/api/clients")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].firstName", is(alex.getFirstName())));

    }
}
