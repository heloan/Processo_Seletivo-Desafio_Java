/// :: **************************************************
/// :: Desafio Java | Author: Heloan Marinho | 10/04/2023
/// :: Version 1.0 - 10/04/2023
/// :: **************************************************

package DesafioJava.Api;

import DesafioJava.Api.Controller.UserController;
import DesafioJava.Api.Model.Dao.SignInDao;
import DesafioJava.Api.Model.Repository.ISignInRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

/// :: ALERT: After run remove row from sis_usuario.
@RunWith(SpringRunner.class)
@SpringBootTest
public class SignInTest {

    @Autowired
    private ISignInRepository signInRepository;

    /// :: Test if user was add.
    @Test
    public void testSingInNewUser() {

        // Arrange
        SignInDao signIn = new SignInDao("maria123", "Maria","mundo123");
        UserController signInController = new UserController(signInRepository);

        // Act
        ResponseEntity<Map<String, String>> response = signInController.post(signIn);

        // Assert
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    /// :: Test error if user all ready exist.
    @Test
    public void testSignInErrorCreatingUser() {

        // Arrange
        SignInDao signIn = new SignInDao("maria123", "Maria","mundo123");
        UserController signInController = new UserController(signInRepository);

        // Act
        ResponseEntity<Map<String, String>> response = signInController.post(signIn);

        // Assert
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
        assertThat(response.getBody().get("message")).isEqualTo("Error creating user");
        assertThat(response.getBody().get("error")).isEqualTo("Database error");
    }

}
