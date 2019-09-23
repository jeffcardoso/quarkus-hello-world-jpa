package org.acme.service;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import javax.transaction.Transactional;

@QuarkusTest
@Transactional
public class UserServiceTest {

    @Inject
    private UserService userService;

    @Test
    public void getUserShouldReturnUser() {
        // TODO
    }
}
