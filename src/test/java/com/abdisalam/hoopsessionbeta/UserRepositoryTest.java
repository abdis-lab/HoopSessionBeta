package com.abdisalam.hoopsessionbeta;

import com.abdisalam.hoopsessionbeta.model.User;
import com.abdisalam.hoopsessionbeta.repository.UserRepository;
import com.abdisalam.hoopsessionbeta.services.UserService;
import com.abdisalam.hoopsessionbeta.services.UserServiceImp;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

/**
 * This class contains unit tests for the UserRepository class.
 * It uses Mockito to mock the UserRepository dependency and,
 * InjectMocks to inject the UserRepository into the UserService implementation.
 */
@ExtendWith(MockitoExtension.class)
public class UserRepositoryTest {
@Mock
private UserRepository userRepository;

    @InjectMocks
    private UserServiceImp userServiceImp;

    @Test
    void testFindByEmail() {
        User mockUser = new User();
        mockUser.setEmail("test@example.com");
        when(userRepository.findByEmail("test@example.com")).thenReturn(mockUser);

        User foundUser = userServiceImp.findUserByEmail("test@example.com");
        assertEquals("test@example.com", foundUser.getEmail());
    }

    @Test
    void testFindByUserName() {
        User mockUser = new User();
        mockUser.setUserName("testUser");
        when(userRepository.findByUserName("testUser")).thenReturn(mockUser);

        User foundUser = userServiceImp.findUserByUserName("testUser");
        assertEquals("testUser", foundUser.getUserName());
    }


    /**
     * Retrieves a user from the UserRepository by their user ID.
     *
     * @param id The ID of the user to retrieve.
     * @return The User object corresponding to the given user ID.
     */
    @ParameterizedTest
    @ValueSource(longs = {1L, 2L, 3L})
    void testFindUserByUserId(Long id) {
        User mockUser = new User();
        mockUser.setUserId(id);
        when(userRepository.findUserByUserId(id)).thenReturn(mockUser);

        User foundUser = userRepository.findUserByUserId(id);
        assertEquals(id, foundUser.getUserId());
    }
}
