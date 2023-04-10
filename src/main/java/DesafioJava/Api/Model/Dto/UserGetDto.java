/// :: **************************************************
/// :: Desafio Java | Author: Heloan Marinho | 10/04/2023
/// :: Version 1.0 - 10/04/2023
/// :: **************************************************

package DesafioJava.Api.Model.Dto;

import DesafioJava.Api.Model.Group;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@EqualsAndHashCode(of = "usuarioid")
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "sis_usuario")
public class UserGetDto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long usuarioid;
    private String usuario;
    private String nome;
    private Date datacriacao;
    private Date dataalteracao;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "sis_grupo_usuario",
        joinColumns = @JoinColumn(name = "usuarioid"),
        inverseJoinColumns = @JoinColumn(name = "grupoid"))
    private Set<Group> grupos = new HashSet<>();

    /// :: Convert date format.
    public String getDatacriacao() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        return formatter.format(datacriacao);
    }

    /// :: Convert date format.
    public String getDataalteracao() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        return formatter.format(dataalteracao);
    }
}
