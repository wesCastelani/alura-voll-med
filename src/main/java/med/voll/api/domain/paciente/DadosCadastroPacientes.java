package med.voll.api.domain.paciente;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.voll.api.domain.endereco.DadosEndereco;

public record DadosCadastroPacientes(
        @NotBlank(message = "Nome é obrigatório")
        String nome,
        @NotBlank(message = "Email é obrigatório")
        @Email(message = "Formato de email inválido")
        String email,
        @NotBlank(message = "Telefone é obrigatório")
        @Pattern(regexp = "\\d{9}", message = "Formato de telefone inválido")
        String telefone,
        @NotBlank(message = "CPF é obrigatório")
        @Pattern(regexp = "\\d{6}", message = "Formato de CPF inválido")
        String cpf,
        @NotNull (message = "Endereco é obrigatório")
        @Valid
        DadosEndereco endereco) {
}
