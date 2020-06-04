
package com.ua.flipPhone.user;

import com.google.common.base.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.internal.verification.VerificationModeFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import org.junit.jupiter.api.AfterEach;
import org.junit.runner.RunWith;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willReturn;
import org.mockito.Mock;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {
    
    @Autowired
    private MockMvc mvc;

    @MockBean
    private UserRepository userRepository;
    
    private User userJoana;
    
    private User userPedro;
    
    @BeforeEach
    public void setUp(){
        userJoana = new User(1,"password", "Joana", "adfqewrewq", "joana@email.com", "Porto", "52346134", UserType.PARTICULAR);
        userPedro = new User(1,"paord", "Pedro", "sefrqew", "pedro@email.com", "Lisboa", "58746584", UserType.PARTICULAR);
    }
    
    @AfterEach
    public void tearDown() {
        userJoana = null;
        userPedro = null;
        reset(userRepository);

    }
    
    
    @Test
    public void givenusers_whenGetAllusers_thenReturnsJsonArray() throws Exception {
                
        List<User> allUser = Arrays.asList(userJoana, userPedro);
        
        given(userRepository.findAll()).willReturn(allUser);
        
        mvc.perform(get("/user/all")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].email", is(userJoana.getEmail())))
                .andExpect(jsonPath("$[1].email", is(userPedro.getEmail())));
        verify(userRepository, VerificationModeFactory.times(1)).findAll();
    }

    @Test
    public void whenPostUser_thenCreateuser() throws Exception {
        given(userRepository.save(Mockito.any())).willReturn(userJoana);
        
        mvc.perform(post("/user/add")
                .param("password","password")
                .param("name","Joana")
                .param("salt","adfqewrewq")
                .param("email","joana@email.com")
                .param("address","Porto")
                .param("nif","52346134")
                .param("type","PARTICULAR"))
                .andExpect(status().isOk());

        verify(userRepository, VerificationModeFactory.times(1)).save(Mockito.any());
    }
    
    @Test
    public void givenUser_whenGetUserById_thenReturnJson() throws Exception {
        
        given(userRepository.findById(userJoana.getUser_id())).willReturn(java.util.Optional.of(userJoana));
        
        mvc.perform(get("/user/{user_id}",userJoana.getUser_id())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("email", is(userJoana.getEmail())));
        verify(userRepository, VerificationModeFactory.times(1)).findById(userJoana.getUser_id());
    }
    
    @Test
    public void testDeleteById() throws Exception {
              
        mvc.perform(MockMvcRequestBuilders.delete("/user/delete")
                .param("user_id", String.valueOf(userJoana.getUser_id())))
                .andExpect(status().isOk());
        verify(userRepository, VerificationModeFactory.times(1)).deleteById(userJoana.getUser_id());
    }
    
    @Test
    public void testGetUserType() throws Exception{
        mvc.perform(get("/user/types")
            .contentType(MediaType.ALL))
            .andExpect(status().isOk());
    }
    
    @Test
    public void givenUser_whenGetUserByEmail_thenReturnsJson() throws Exception {

        given(userRepository.findByEmail(userJoana.getEmail())).willReturn(userJoana);

        mvc.perform(get("/user/byEmail?email=joana@email.com")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(userRepository, VerificationModeFactory.times(1)).findByEmail(userJoana.getEmail());
    }
}
