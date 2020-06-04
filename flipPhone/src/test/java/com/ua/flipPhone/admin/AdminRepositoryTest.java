
package com.ua.flipPhone.admin;

import java.util.ArrayList;
import java.util.Arrays;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

@AutoConfigureTestDatabase(replace=AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
public class AdminRepositoryTest {
    
    @Autowired
    private TestEntityManager entityManager;

    
    @Autowired
    private AdminRepository adminRepository;
       
      
   
    
    @Test
    public void testFindAll(){
        
        Admin adminMaria = new Admin("pasord", "asdfsdfxzv", "maria@email.com");
        entityManager.persistAndFlush(adminMaria);
        Admin adminJoao = new Admin("passrd", "gvserfve", "joao@email.com");
        entityManager.persistAndFlush(adminJoao);
        
        List<Admin> expected = new ArrayList<Admin>();
        expected.add(adminMaria);
        expected.add(adminJoao);
        
         List<Admin> result = adminRepository.findAll();
        
        assertThat(expected.equals(result));
    }
    
    @Test
    public void testSave(){
        
        Admin adminMaria = new Admin("pasord", "asdfsdfxzv", "maria@email.com");
        
        adminRepository.save(adminMaria);
        
        Optional<Admin> result = adminRepository.findById(adminMaria.getAdmin_id());
        
        assertThat(Optional.of(adminMaria).equals(result));
        
    }
    
    @Test
    public void testFindById(){
        
        Admin adminMaria = new Admin("pasord", "asdfsdfxzv", "maria@email.com");
        entityManager.persistAndFlush(adminMaria);
        
        Optional<Admin> result = adminRepository.findById(adminMaria.getAdmin_id());
        
        assertThat(Optional.of(adminMaria).equals(result));
    }
    
    
    
    @Test
    public void testDeleteById(){
        Admin adminMaria = new Admin("pasord", "asdfsdfxzv", "maria@email.com");
        entityManager.persistAndFlush(adminMaria);
        
        adminRepository.deleteById(adminMaria.getAdmin_id());
        
        Optional<Admin> result =  adminRepository.findById(adminMaria.getAdmin_id());
        
        assertEquals(Optional.empty(), result);
        
    }
    
    @Test
    public void testFindByEmail(){
        Admin adminMaria = new Admin("pasord", "asdfsdfxzv", "maria@email.com");
        entityManager.persistAndFlush(adminMaria);
        
        Admin result = adminRepository.findByEmail(adminMaria.getEmail());
        
        assertEquals(adminMaria, result);
    }
}
