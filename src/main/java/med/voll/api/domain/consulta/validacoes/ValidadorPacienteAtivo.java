package med.voll.api.domain.consulta.validacoes;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.consulta.DadosAgentamentoConsulta;
import med.voll.api.domain.medico.MedicoRepository;
import med.voll.api.domain.paciente.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class ValidadorPacienteAtivo {

    @Autowired
    PacienteRepository PacienteRepository;

    public void validar(DadosAgentamentoConsulta dados){
        if(dados.idPaciente() == null){
            return;
        }

        var pacienteEstaAtivo = PacienteRepository.findAtivoById(dados.idMedico());
        if(!pacienteEstaAtivo){
            throw new ValidacaoException("Consulta não pode ser agendada com um paciente inativo!");
        }
    }

}
