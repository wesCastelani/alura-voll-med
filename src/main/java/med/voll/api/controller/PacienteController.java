package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.paciente.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("pacientes")
public class PacienteController {

    @Autowired
    PacienteRepository pacienteRepository;
    @PostMapping
    @Transactional
    public ResponseEntity cadastroPacientes(@RequestBody @Valid DadosCadastroPacientes dados,  UriComponentsBuilder uriBuilder){
        var paciente = new Paciente(dados);
        pacienteRepository.save(paciente);

        var uri = uriBuilder.path("/pacientes/{id}").buildAndExpand(paciente.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosDetalhamentoPaciente(paciente));
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemPaciente>> listar(@PageableDefault(size = 10, page = 0, sort = {"nome"}) Pageable pageable){
       var page = pacienteRepository.findByAtivoTrue(pageable).map(DadosListagemPaciente::new);

       return ResponseEntity.ok(page);
    }
    @PutMapping
    @Transactional
    public ResponseEntity atualizarPaciente(@RequestBody DadosAtualizacaoPacientes dados){
        var paciente = pacienteRepository.getReferenceById(dados.id());
        paciente.atualizarCadastro(dados);

        return ResponseEntity.ok(new DadosDetalhamentoPaciente(paciente));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deletarPaciente(@PathVariable Long id){
        var paciente = pacienteRepository.getReferenceById(id);
        paciente.deletarCadastro();

        return ResponseEntity.noContent().build();
    }
}
