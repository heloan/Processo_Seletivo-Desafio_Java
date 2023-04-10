/// :: **************************************************
/// :: Desafio Java | Author: Heloan Marinho | 10/04/2023
/// :: Version 1.0 - 10/04/2023
/// :: **************************************************

package DesafioJava.Api.Model;

import DesafioJava.Api.Model.Dao.GroupDao;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

@Getter
@EqualsAndHashCode(of = "grupoid")
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "sis_grupo")
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long grupoid;
    private String nome;
    private Date datacriacao;
    private Date dataalteracao;

    /// :: Custruct group.
    public Group(GroupDao group){
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        Date utcDate = calendar.getTime();
        this.nome = group.nome();
        this.datacriacao = utcDate;
        this.dataalteracao = utcDate;
    }

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
