package ProjetoDeSoftwareLAB2.demo.controllers;

import ProjetoDeSoftwareLAB2.demo.entities.Disciplina;
import ProjetoDeSoftwareLAB2.demo.services.DisciplinasService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class DisciplinasController {

    private DisciplinasService disciplinasService;

    public DisciplinasController(DisciplinasService disciplinasService){
        super();
        this.disciplinasService = disciplinasService;
    }

    @PostMapping("/disciplinas")
    public ResponseEntity<Disciplina> adicionaDisciplina(@RequestBody Disciplina disciplina){
        return new ResponseEntity<Disciplina>(disciplinasService.adicionaDisciplina(disciplina), HttpStatus.CREATED);
    }

    @GetMapping("/disciplinas/{id}")
    public ResponseEntity<Disciplina> getDisciplina(@PathVariable Long id){
        Optional<Disciplina> disciplina = disciplinasService.getDisciplina(id);
        if(disciplina.isPresent()){
            return new ResponseEntity<Disciplina>(disciplina.get(),HttpStatus.OK);
        }
        else{
            return new ResponseEntity<Disciplina>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/disciplinas")
    public ResponseEntity<List<Disciplina>> getDisciplinas(){
        return new ResponseEntity<List<Disciplina>>(disciplinasService.getDisciplinas(), HttpStatus.OK);
    }
}
