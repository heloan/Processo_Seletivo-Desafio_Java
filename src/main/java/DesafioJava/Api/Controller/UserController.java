/// :: **************************************************
/// :: Desafio Java | Author: Heloan Marinho | 10/04/2023
/// :: Version 1.0 - 10/04/2023
/// :: **************************************************

package DesafioJava.Api.Controller;

import DesafioJava.Api.Model.Dao.SignInDao;
import DesafioJava.Api.Model.Dto.UserGetDto;
import DesafioJava.Api.Model.Repository.ISignInRepository;
import DesafioJava.Api.Model.Repository.IUserGetRepository;
import DesafioJava.Api.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@RestController
@RequestMapping("/api/usuario")
public class UserController {

    @Autowired
    private IUserGetRepository userRepository;
    private final ISignInRepository signInRepository;

    @Autowired
    public UserController(ISignInRepository signInRepository) {
        this.signInRepository = signInRepository;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<Map<String, String>> post(@RequestBody SignInDao signIn) {
        try {
            User user = signInRepository.findByUsuario(signIn.usuario())
                    .orElse(null);

            if (user == null) {
                signInRepository.save(new User(signIn));
                Map<String, String> response = new HashMap<>();
                response.put("message", "User created");

                return ResponseEntity.ok(response);
            } else {
                Map<String, String> response = new HashMap<>();
                response.put("message", "Unauthorized");

                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
            }
        } catch (Exception e) {
            Map<String, String> response = new HashMap<>();
            response.put("message", "Error creating user");
            response.put("error", e.getMessage());

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
    @GetMapping
    public ResponseEntity<List<UserGetDto>> listar() {
        try {
            List<UserGetDto> usuarios = userRepository.findAll();

            return ResponseEntity.ok(usuarios);
        } catch (Exception e) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("message", "Error retrieving users");
            errorResponse.put("error", e.getMessage());

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserGetDto> getById(@PathVariable Long id) {
        try {
            UserGetDto user = userRepository.findByUsuarioid(id)
                    .orElseThrow(() -> new UsernameNotFoundException("User not found"));
            return ResponseEntity.ok(user);
        } catch (UsernameNotFoundException ex) {
            return ResponseEntity.notFound().build();
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
