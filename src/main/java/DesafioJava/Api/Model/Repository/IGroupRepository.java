/// :: **************************************************
/// :: Desafio Java | Author: Heloan Marinho | 10/04/2023
/// :: Version 1.0 - 10/04/2023
/// :: **************************************************

package DesafioJava.Api.Model.Repository;

import DesafioJava.Api.Model.Group;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IGroupRepository extends JpaRepository<Group, Integer> {
    Optional<Group> findByGrupoid(Long grupoid);
}