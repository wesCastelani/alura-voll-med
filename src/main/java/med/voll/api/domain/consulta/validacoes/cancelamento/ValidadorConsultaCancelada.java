package med.voll.api.domain.consulta.validacoes.cancelamento;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.consulta.Consulta;
import med.voll.api.domain.consulta.ConsultaRepository;
import med.voll.api.domain.consulta.DadosCancelamentoConsulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorConsultaCancelada implements ValidadorCancelamentoConsulta{

    @Autowired
    ConsultaRepository consultaRepository;

    @Override
    public void validar(DadosCancelamentoConsulta dadosCancelamentoConsulta) {
        Consulta consulta = consultaRepository.getReferenceById(dadosCancelamentoConsulta.idConsulta());

        if (consulta.getMotivoCancelamento() != null){
            throw  new ValidacaoException("Consulta já foi cancelada anteriormente!");
        }


    }
}
