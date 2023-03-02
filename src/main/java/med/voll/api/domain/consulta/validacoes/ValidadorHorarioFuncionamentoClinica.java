package med.voll.api.domain.consulta.validacoes;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.consulta.DadosAgentamentoConsulta;

import java.time.DayOfWeek;

public class ValidadorHorarioFuncionamentoClinica {

    public void validar(DadosAgentamentoConsulta dados){

        var dataConsulta = dados.data();

        var domingo = dataConsulta.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        var antesDaAberturaDaClinica = dataConsulta.getHour() < 7;
        var depoisDoEncearamentoDaClinica = dataConsulta.getHour() < 18;

        if(domingo || antesDaAberturaDaClinica || depoisDoEncearamentoDaClinica){
            throw new ValidacaoException("Consulta fora do horario de funcionamento da clinica");
        }

    }


}
