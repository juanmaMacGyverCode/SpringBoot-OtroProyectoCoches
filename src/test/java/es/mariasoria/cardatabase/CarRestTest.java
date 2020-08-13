package es.mariasoria.cardatabase;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

//@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CarRestTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    // MockMvc provides the perform method to send these requests.
    // To test authentication, we have to add credentials to the request body
    public void testAuthentication01() throws Exception {
        // Testing authentication with correct credentials
        this.mockMvc.perform(post("/login")
                .content("{\"username\":\"admin\", \"password\":\"admin\"}")).
                andDo(print()).andExpect(status().isOk());
    }

    @Test
    public void testAuthentication02() throws Exception {
        // Testing authentication with wrong credentials
        this.mockMvc.perform(post("/login")
                .content("{\"username\":\"admin\", \"password\":\"wrongpwd\"}")).
                andDo(print()).andExpect(status().is4xxClientError());
    }

    @Test
    public void testAuthentication03() throws Exception {
        // Testing authentication with correct credentials
        this.mockMvc.perform(post("/login")
                .content("{\"username\":\"user\", \"password\":\"user\"}")).
                andDo(print()).andExpect(status().isOk());
    }
}
