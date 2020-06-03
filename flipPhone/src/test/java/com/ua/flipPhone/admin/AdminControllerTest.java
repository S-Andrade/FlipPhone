
package com.ua.flipPhone.admin;

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
@WebMvcTest(AdminController.class)
public class AdminControllerTest {
    
    @Autowired
    private MockMvc mvc;

    @MockBean
    private AdminRepository adminRepository;
    
    private Admin adminMaria;
    
    private Admin adminJoao;
    
    @BeforeEach
    public void setUp(){
        adminMaria = new Admin(1,"pasord", "asdfsdfxzv", "maria@email.com");
        adminJoao = new Admin(2,"passrd", "gvserfve", "joao@email.com");
    }
    
    @AfterEach
    public void tearDown() {
        adminMaria = null;
        adminJoao = null;
        reset(adminRepository);

    }
    
    
    @Test
    public void givenAdmins_whenGetAllAdmins_thenReturnsJsonArray() throws Exception {
                
        List<Admin> allAdmin = Arrays.asList(adminMaria, adminJoao);
        
        given(adminRepository.findAll()).willReturn(allAdmin);
        
        mvc.perform(get("/admin/all")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].email", is(adminMaria.getEmail())))
                .andExpect(jsonPath("$[1].email", is(adminJoao.getEmail())));
        verify(adminRepository, VerificationModeFactory.times(1)).findAll();
    }

    @Test
    public void whenPostAdminEmployee_thenCreateAdmin() throws Exception {
        given(adminRepository.save(Mockito.any())).willReturn(adminMaria);
        
        mvc.perform(post("/admin/add")
                .param("password", "pasord")
                .param("hash","asdfsdfxzv")
                .param("email","maria@email.com"))
                .andExpect(status().isOk());

        verify(adminRepository, VerificationModeFactory.times(1)).save(Mockito.any());
    }
    
    @Test
    public void givenAdmin_whenGetAdminById_thenReturnJson() throws Exception {
        
        given(adminRepository.findById(adminMaria.getAdmin_id())).willReturn(java.util.Optional.of(adminMaria));
        
        mvc.perform(get("/admin/{admin_id}",adminMaria.getAdmin_id())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("email", is(adminMaria.getEmail())));
        verify(adminRepository, VerificationModeFactory.times(1)).findById(adminMaria.getAdmin_id());
    }
    
    @Test
    public void testDeleteById() throws Exception {
              
        mvc.perform(MockMvcRequestBuilders.delete("/admin/delete")
                .param("admin_id", String.valueOf(adminMaria.getAdmin_id())))
                .andExpect(status().isOk());
    }
    
    @Test
    public void givenAdmin_whenGetAdminByEmail_thenReturnsJson() throws Exception {

        given(adminRepository.findByEmail(adminMaria.getEmail())).willReturn(adminMaria);

        mvc.perform(get("/admin/byEmail?email=maria@email.com")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
                
        verify(adminRepository, VerificationModeFactory.times(1)).findByEmail(adminMaria.getEmail());
    }
}
