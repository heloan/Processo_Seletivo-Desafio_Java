/// :: **************************************************
/// :: Desafio Java | Author: Heloan Marinho | 10/04/2023
/// :: Version 1.0 - 10/04/2023
/// :: **************************************************

package DesafioJava.Api.Model.Dao;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
public record SignInDao(
        @NotBlank
        @Size(min = 3, message = "{validation.usuario.size.too_short}")
        @Size(max = 50, message = "{validation.usuario.size.too_long}")
        String usuario,
        @NotBlank
        @Size(min = 3, message = "{validation.nome.size.too_short}")
        @Size(max = 100, message = "{validation.nome.size.too_long}")
        String nome,
        @NotBlank
        @Size(min = 3, message = "{validation.senha.size.too_short}")
        @Size(max = 50, message = "{validation.senha.size.too_long}")
        String senha
) {}
