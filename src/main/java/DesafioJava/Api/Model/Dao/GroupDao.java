/// :: **************************************************
/// :: Desafio Java | Author: Heloan Marinho | 10/04/2023
/// :: Version 1.0 - 10/04/2023
/// :: **************************************************

package DesafioJava.Api.Model.Dao;

import javax.validation.constraints.NotBlank;

public record GroupDao(
        @NotBlank
        String nome
) {
}
