
package com.ua.flipPhone.user;

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
public class UserRepositoryTest {
    
    @Mock
    private UserRepository userRepository;
       
    private User userJoana;
    
    private User userPedro;
       
    @BeforeEach
    public void setUp(){
        userJoana = new User(1,"password", "Joana", "adfqewrewq", "joana@email.com", "Porto", "52346134", UserType.PARTICULAR);
        userPedro = new User(1,"password", "Pedro", "adfqewrewq", "pedro@email.com", "Lisboa", "58746584", UserType.PARTICULAR);
    }
    
    @AfterEach
    public void tearDown() {
        userJoana = null;
        userPedro = null;
        reset(userRepository);
    }
    
    @Test
    public void testSave(){
        when(userRepository.save(userJoana)).thenReturn(userJoana);
    }
    
    @Test
    public void testFindById(){
        when(userRepository.findById(userJoana.getUser_id())).thenReturn(Optional.of(userJoana));
    }
    
    @Test
    public void testFindAll(){
        List<User> listUser = new ArrayList<User>();
        listUser.add(userJoana);
        listUser.add(userPedro);
        when(userRepository.findAll()).thenReturn(listUser);
    }
    
    @Test
    public void testDeleteById(){
        userRepository.deleteById(userJoana.getUser_id());
        when(userRepository.findById(userJoana.getUser_id())).thenReturn(null);
    }
    
    @Test
    public void testFindByEmail(){
        when(userRepository.findByEmail(userJoana.getEmail())).thenReturn(userJoana);
    }
}
