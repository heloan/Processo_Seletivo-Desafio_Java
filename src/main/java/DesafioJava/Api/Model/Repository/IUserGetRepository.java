/// :: **************************************************
/// :: Desafio Java | Author: Heloan Marinho | 10/04/2023
/// :: Version 1.0 - 10/04/2023
/// :: **************************************************

package DesafioJava.Api.Model.Repository;

import DesafioJava.Api.Model.Dto.UserGetDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IUserGetRepository extends JpaRepository<UserGetDto, Integer> {
    Optional<UserGetDto> findByUsuarioid(Long usuarioid);
    Optional<UserGetDto> findByUsuario(String usuario);
}