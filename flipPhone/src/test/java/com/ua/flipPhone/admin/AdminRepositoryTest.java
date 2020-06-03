
package com.ua.flipPhone.admin;

import java.util.ArrayList;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.when;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

@AutoConfigureTestDatabase(replace=AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
@RunWith(MockitoJUnitRunner.class)
public class AdminRepositoryTest {
    
    @Mock
    private AdminRepository adminRepository;
       
    private Admin adminMaria;
    
    private Admin adminJoao;
       
    @BeforeEach
    public void setUp(){
        adminMaria = new Admin("pasord", "asdfsdfxzv", "maria@email.com");
        adminJoao = new Admin("passrd", "gvserfve", "joao@email.com");
    }
    
    @AfterEach
    public void tearDown() {
        adminMaria = null;
        adminJoao = null;
        reset(adminRepository);
    }
    
    @Test
    public void testSave(){
        when(adminRepository.save(adminMaria)).thenReturn(adminMaria);
    }
    
    @Test
    public void testFindById(){
        when(adminRepository.findById(adminMaria.getAdmin_id())).thenReturn(Optional.of(adminMaria));
    }
    
    @Test
    public void testFindAll(){
        List<Admin> listAdmin = new ArrayList<Admin>();
        listAdmin.add(adminMaria);
        listAdmin.add(adminJoao);
        when(adminRepository.findAll()).thenReturn(listAdmin);
    }
    
    @Test
    public void testDeleteById(){
        adminRepository.deleteById(adminMaria.getAdmin_id());
        when(adminRepository.findById(adminMaria.getAdmin_id())).thenReturn(null);
    }
    
    @Test
    public void testFindByEmail(){
        when(adminRepository.findByEmail(adminMaria.getEmail())).thenReturn(adminMaria);
    }
}
