package com.revconnect.test;

import com.revconnect.dao.IUserDao;
import com.revconnect.model.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.testng.Assert.assertNull;
import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertNotNull;


@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {

    @Mock
    private IUserDao userDao;

    @InjectMocks
    private UserServiceImpl userService;



    @Test
    void login_success() {
        User mockUser = new User();
        mockUser.setUsername("testuser");

        when(userDao.login("testuser", "1234"))
                .thenReturn(mockUser);

        User result = userService.login("testuser", "1234");

        assertNotNull(result);
        assertEquals("testuser", result.getUsername());
    }

    @Test
    void login_failure() {
        when(userDao.login("wrong", "wrong"))
                .thenReturn(null);

        User result = userService.login("wrong", "wrong");

        assertNull(result);
    }
}