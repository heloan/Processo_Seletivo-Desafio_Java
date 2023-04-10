/// :: **************************************************
/// :: Desafio Java | Author: Heloan Marinho | 10/04/2023
/// :: Version 1.0 - 10/04/2023
/// :: **************************************************

package DesafioJava.Api.Controller;

import DesafioJava.Api.Model.Dao.GroupDao;
import DesafioJava.Api.Model.Dao.GroupUserDao;
import DesafioJava.Api.Model.Dto.UserGetDto;
import DesafioJava.Api.Model.Group;
import DesafioJava.Api.Model.GroupUser;
import DesafioJava.Api.Model.Repository.IGroupRepository;
import DesafioJava.Api.Model.Repository.IGroupUserRepository;
import DesafioJava.Api.Model.Repository.IUserGetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;

@RestController
@RequestMapping("/api/grupo")
public class GroupController {
    @Autowired
    private IGroupRepository groupRepository;
    @Autowired
    private IGroupUserRepository groupUserRepository;
    @Autowired
    private IUserGetRepository userRepository;
    @PostMapping
    @Transactional
    public ResponseEntity<?> post(@RequestBody GroupDao groupDao, HttpServletRequest request) {
        try {
            /// :: Get username from authentication.
            String username = "";
            String authHeader = request.getHeader("Authorization");
            if (authHeader != null && authHeader.startsWith("Basic ")) {
                String base64Credentials = authHeader.substring("Basic" .length()).trim();
                byte[] decodedCredentials = Base64.getDecoder().decode(base64Credentials);
                String credentials = new String(decodedCredentials, StandardCharsets.UTF_8);
                String[] parts = credentials.split(":", 2);
                username = parts[0];
            }
            else
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();

            /// :: Check if found user.
            if(username == "")
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();

            /// :: Get user info.
            UserGetDto user = userRepository.findByUsuario(username)
                    .orElseThrow(() -> new UsernameNotFoundException("User not found"));

            /// :: Save group and groupuser.
            Group group = groupRepository.save(new Group(groupDao));
            GroupUserDao groupUser = new GroupUserDao(user.getUsuarioid(), group.getGrupoid());
            groupUserRepository.save(new GroupUser(groupUser));

            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (UsernameNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Group>> listar() {
        try {
            List<Group> groups = groupRepository.findAll();
            return ResponseEntity.ok(groups);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Group> getById(@PathVariable Long id) {
        try {
            Group group = groupRepository.findByGrupoid(id)
                    .orElseThrow(() -> new UsernameNotFoundException("Group not found"));
            return ResponseEntity.ok(group);
        } catch (UsernameNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
