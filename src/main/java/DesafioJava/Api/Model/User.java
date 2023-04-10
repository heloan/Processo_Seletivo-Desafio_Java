/// :: **************************************************
/// :: Desafio Java | Author: Heloan Marinho | 10/04/2023
/// :: Version 1.0 - 10/04/2023
/// :: **************************************************

package DesafioJava.Api.Model;

import DesafioJava.Api.Model.Dao.SignInDao;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

@Getter
@EqualsAndHashCode(of = "usuarioid")
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "User")
@Table(name = "sis_usuario")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long usuarioid;

    private String usuario;
    private String nome;
    private String senha;
    private Date datacriacao;
    private Date dataalteracao;

    public User(SignInDao basic){
        var passwordEncoder = new BCryptPasswordEncoder();
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        Date utcDate = calendar.getTime();
        this.usuario = basic.usuario();
        this.nome = basic.nome();
        this.senha = passwordEncoder.encode(basic.senha());
        this.datacriacao = utcDate;
        this.dataalteracao = utcDate;
    }
}
