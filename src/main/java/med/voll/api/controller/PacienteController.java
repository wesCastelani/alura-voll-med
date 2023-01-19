package med.voll.api.controller;

import med.voll.api.paciente.DadosCadastroPacientes;
import med.voll.api.paciente.Paciente;
import med.voll.api.paciente.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("pacientes")
public class PacienteController {

    @Autowired
    PacienteRepository pacienteRepository;
    @PostMapping
    public void cadastroPacientes(@RequestBody DadosCadastroPacientes dados){
        pacienteRepository.save(new Paciente(dados));
    }
}
