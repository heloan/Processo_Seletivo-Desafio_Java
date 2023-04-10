/// :: **************************************************
/// :: Desafio Java | Author: Heloan Marinho | 10/04/2023
/// :: Version 1.0 - 10/04/2023
/// :: **************************************************

package DesafioJava.Api.Model;

import DesafioJava.Api.Model.Dao.GroupUserDao;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@EqualsAndHashCode(of = "grupoid")
@NoArgsConstructor
@AllArgsConstructor
@Entity
@IdClass(GroupUserId.class)
@Table(name = "sis_grupo_usuario")
public class GroupUser implements Serializable {

    @Id
    @Column(name = "grupoid")
    private Long grupoid;

    @Id
    @Column(name = "usuarioid")
    private Long usuarioid;

    public GroupUser(GroupUserDao groupUser){
        this.grupoid = groupUser.grupoid();
        this.usuarioid = groupUser.usuarioid();
    }
}





