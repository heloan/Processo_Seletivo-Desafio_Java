/// :: **************************************************
/// :: Desafio Java | Author: Heloan Marinho | 10/04/2023
/// :: Version 1.0 - 10/04/2023
/// :: **************************************************

package DesafioJava.Api.Model.Repository;

import DesafioJava.Api.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/// :: Create repository to access user info data base.
public interface IUserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsuario(String usuario);
}
