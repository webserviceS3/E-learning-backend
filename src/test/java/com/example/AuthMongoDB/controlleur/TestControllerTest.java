package com.example.AuthMongoDB.controlleur;

import com.example.AuthMongoDB.controllers.TestController;
import com.example.AuthMongoDB.models.*;
import com.example.AuthMongoDB.repositories.*;
import com.example.AuthMongoDB.services.CourService;
import com.example.AuthMongoDB.services.QCMservice;
import com.example.AuthMongoDB.services.UserDetailsServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Collections;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;


@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TestControllerTest {
    @Autowired
    private UserDetailsServiceImpl userDetailsService;
    @Autowired
    private CourService courService;
    @Autowired
    private QCMservice qcMservice;
    @Autowired
    private TestController testController;

    @MockBean
    private UserRepository userRepository;
    @MockBean
    private CourRepository courRepository;
    @MockBean
    private QCMrepository qcMrepository;
    @MockBean
    private MessageRepository messageRepository;

    @Test
    public void getAllUsersTest() {
        when(userRepository.findAll()).thenReturn(Stream
                .of(new User("abdo","abdelhaq@mmm.ma","1234",20), new User("aya","aya@mmm.ma","1234",30)).collect(Collectors.toList()));
        assertEquals(2, userDetailsService.getAllUsers().size());
    }
    @Test
    public void getCoursTest(){
        when(courRepository.findAll()).thenReturn(Stream
                .of(new Cour()).collect(Collectors.toList()));
        assertEquals(1,courService.getAllCours().size());
    }
    @Test
    public void getQCMTest(){
        when(qcMrepository.findAll()).thenReturn(Stream
                .of(new QCM()).collect(Collectors.toList()));
        assertEquals(1,qcMservice.getAllQCM().size());
    }
    @Test
    public void getMessageTest(){
        when(messageRepository.findAll()).thenReturn(Stream
                .of(new Message()).collect(Collectors.toList()));
        assertEquals(1,testController.getMessages().size());
    }

    @Test
    public void addQCMTest() {
        // Create a QCM instance
        QCM qcmToAdd = new QCM();
        // Set necessary properties on the qcmToAdd object

        // Mock the behavior of qcMrepository.save() to return the QCM when saved
        when(qcMrepository.save(qcmToAdd)).thenReturn(qcmToAdd);

        // Call the addQCM method on qcMservice
        qcMservice.addQCM(qcmToAdd);

        // Verify that qcMrepository.save() was called with the correct argument
        verify(qcMrepository, times(1)).save(qcmToAdd);

        // Mock the behavior of getAllQCM to return a list with the added QCM
        when(qcMservice.getAllQCM()).thenReturn(Collections.singletonList(qcmToAdd));

        // Now, test if getAllQCM returns the expected size after adding a QCM
        assertEquals(1, qcMservice.getAllQCM().size());
    }

    @Test
    public void addCoourTest() {
        // Create a QCM instance
        Cour courtoadd = new Cour();
        // Set necessary properties on the qcmToAdd object

        // Mock the behavior of qcMrepository.save() to return the QCM when saved
        when(courRepository.save(courtoadd)).thenReturn(courtoadd);

        // Call the addQCM method on qcMservice
        courService.addCour(courtoadd);

        // Verify that qcMrepository.save() was called with the correct argument
        verify(courRepository, times(1)).save(courtoadd);

        // Mock the behavior of getAllQCM to return a list with the added QCM
        when(courService.getAllCours()).thenReturn(Collections.singletonList(courtoadd));

        // Now, test if getAllQCM returns the expected size after adding a QCM
        assertEquals(1, courService.getAllCours().size());
    }
    }



