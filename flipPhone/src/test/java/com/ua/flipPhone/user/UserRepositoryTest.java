
package com.ua.flipPhone.user;

import com.ua.flipPhone.admin.Admin;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.when;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

@AutoConfigureTestDatabase(replace=AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
@RunWith(MockitoJUnitRunner.class)
public class UserRepositoryTest {
    
    @Autowired
    private TestEntityManager entityManager;

    
    @Autowired
    private UserRepository userRepository;
       
    
    @Test
    public void testSave(){
        User userJoana = new User("password", "Joana", "adfqewrewq", "joana@email.com", "Porto", "52346134", UserType.PARTICULAR);
         
        userRepository.save(userJoana);
        
        Optional<User> result = userRepository.findById(userJoana.getUser_id());
        
        assertThat(Optional.of(userJoana).equals(result));
    }
    
    @Test
    public void testFindById(){
        User userJoana = new User("password", "Joana", "adfqewrewq", "joana@email.com", "Porto", "52346134", UserType.PARTICULAR);
        entityManager.persistAndFlush(userJoana);
        
        Optional<User> result = userRepository.findById(userJoana.getUser_id());
        
        assertThat(Optional.of(userJoana).equals(result));
    }
    
    @Test
    public void testFindAll(){
        User userJoana = new User("password", "Joana", "adfqewrewq", "joana@email.com", "Porto", "52346134", UserType.PARTICULAR);
        entityManager.persistAndFlush(userJoana);
        User userPedro = new User("password", "Pedro", "adfqewrewq", "pedro@email.com", "Lisboa", "58746584", UserType.PARTICULAR);
        entityManager.persistAndFlush(userPedro);
        
        List<User> expected = new ArrayList<User>();
        expected.add(userJoana);
        expected.add(userPedro);
        
         List<User> result = userRepository.findAll();
        
        assertThat(expected.equals(result));
    }
    
    @Test
    public void testDeleteById(){
        User userJoana = new User("password", "Joana", "adfqewrewq", "joana@email.com", "Porto", "52346134", UserType.PARTICULAR);
        entityManager.persistAndFlush(userJoana);
        
        userRepository.deleteById(userJoana.getUser_id());
        
        Optional<User> result = userRepository.findById(userJoana.getUser_id());
        
        assertEquals(Optional.empty(), result);
    }
    
    @Test
    public void testFindByEmail(){
        User userJoana = new User("password", "Joana", "adfqewrewq", "joana@email.com", "Porto", "52346134", UserType.PARTICULAR);
        entityManager.persistAndFlush(userJoana);
                
        User result = userRepository.findByEmail(userJoana.getEmail());
        
        assertThat(userJoana.equals(result));
    }
}
