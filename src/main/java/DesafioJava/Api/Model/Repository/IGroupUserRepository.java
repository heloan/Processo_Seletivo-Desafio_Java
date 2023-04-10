/// :: **************************************************
/// :: Desafio Java | Author: Heloan Marinho | 10/04/2023
/// :: Version 1.0 - 10/04/2023
/// :: **************************************************

package DesafioJava.Api.Model.Repository;

import DesafioJava.Api.Model.GroupUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IGroupUserRepository extends JpaRepository<GroupUser, Integer> {
}
