package med.voll.api.domain.consulta.validacoes.cancelamento;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.consulta.Consulta;
import med.voll.api.domain.consulta.ConsultaRepository;
import med.voll.api.domain.consulta.DadosCancelamentoConsulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class AntecedenciaCancelamentoConsulta implements ValidadorCancelamentoConsulta{

    @Autowired
    ConsultaRepository consultaRepository;

    @Override
    public void validar(DadosCancelamentoConsulta dados) {

        Consulta consulta = consultaRepository.getReferenceById(dados.idConsulta());

        if(LocalDateTime.now().plusHours(24).isAfter(consulta.getData())){
            throw new ValidacaoException("Consultas só podem ser canceladas com 24 horas de antecedencia");
        }

    }
}
