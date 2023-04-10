/// :: **************************************************
/// :: Desafio Java | Author: Heloan Marinho | 10/04/2023
/// :: Version 1.0 - 10/04/2023
/// :: **************************************************

package DesafioJava.Api.Config.Service;

import DesafioJava.Api.Model.Repository.IUserRepository;
import DesafioJava.Api.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;

/// :: Create a custom user details service to get user from database.
@Component
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private IUserRepository repository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repository.findByUsuario(username)
                .orElseThrow(()-> new UsernameNotFoundException("User not found"));
        List<GrantedAuthority> authorityListUser = AuthorityUtils.createAuthorityList("USER");
        return new org.springframework.security.core.userdetails
                .User(user.getNome(), user.getSenha(), authorityListUser);
    }
}
