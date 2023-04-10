/// :: **************************************************
/// :: Desafio Java | Author: Heloan Marinho | 10/04/2023
/// :: Version 1.0 - 10/04/2023
/// :: **************************************************

package DesafioJava.Api.Controller;

import DesafioJava.Api.Model.Dao.LoginDao;
import DesafioJava.Api.Model.Repository.IUserRepository;
import DesafioJava.Api.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/login")
public class LoginController {
    @Autowired
    private IUserRepository userRepository;
    @GetMapping
    public ResponseEntity<Map<String, String>> post(@RequestBody LoginDao login) {
        try {
            /// :: Get user from database.
            var passwordEncoder = new BCryptPasswordEncoder();
            User user = userRepository.findByUsuario(login.usuario())
                    .orElseThrow(() -> new UsernameNotFoundException("User not found"));

            /// :: Check if password is equal.
            if (passwordEncoder.matches(login.senha(), user.getSenha())) {
                String auth = login.usuario() + ":" + login.senha();
                byte[] encodedAuth = Base64.encode(auth.getBytes());
                String header = "Basic " + new String(encodedAuth);

                Map<String, String> response = new HashMap<>();
                response.put("message", "Success");
                response.put("authorization", header);

                return ResponseEntity.ok(response);
            } else {
                Map<String, String> response = new HashMap<>();
                response.put("message", "Unauthorized");

                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
            }
        }catch (Exception e) {
            Map<String, String> response = new HashMap<>();
            response.put("message", "Error");
            response.put("error", e.getMessage());

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}
