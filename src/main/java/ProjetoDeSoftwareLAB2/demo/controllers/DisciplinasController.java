package ProjetoDeSoftwareLAB2.demo.controllers;

import ProjetoDeSoftwareLAB2.demo.entities.Disciplina;
import ProjetoDeSoftwareLAB2.demo.tools.EntradaDeDados;
import ProjetoDeSoftwareLAB2.demo.services.DisciplinasService;
import ProjetoDeSoftwareLAB2.demo.services.JWTService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class DisciplinasController {

    private DisciplinasService disciplinasService;
    private JWTService jwtService;

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
        return new ResponseEntity<>(disciplinasService.getDisciplinas(), HttpStatus.OK);
    }

    @DeleteMapping("/disciplinas/{id}")
    public ResponseEntity<Disciplina> deleteDisciplina(@PathVariable Long id){
        if (disciplinasService.getDisciplina(id).isPresent()){
            return new ResponseEntity<>(disciplinasService.deletaDisciplina(id), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/disciplinas/likes/{id}")
    public ResponseEntity<Disciplina> incrementaLikes(@PathVariable Long id){
        if(disciplinasService.getDisciplina(id).isPresent()){
            return new ResponseEntity<>(disciplinasService.incrementaLikes(id), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/disciplinas/nota/{id}")
    public ResponseEntity<Disciplina> modificaNota(@PathVariable Long id, @RequestBody EntradaDeDados dados){
        if(disciplinasService.getDisciplina(id).isPresent()){
            return new ResponseEntity<>(disciplinasService.modificarNota(id, dados.getNota()), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/disciplinas/comentarios/{id}")
    public ResponseEntity<Disciplina> adicionarComentario(@PathVariable Long id, @RequestBody EntradaDeDados dados){
        if(disciplinasService.getDisciplina(id).isPresent()){
            return new ResponseEntity<>(disciplinasService.adicionarComentario(id, dados.getComentario()), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/disciplinas/ranking/notas")
    public ResponseEntity<List<Disciplina>> listarPorNotas(){
        return new ResponseEntity<>(disciplinasService.listarPorNota(), HttpStatus.OK);
    }


    @GetMapping("/disciplinas/ranking/likes")
    public ResponseEntity<List<Disciplina>> listarPorLikes(){
        return new ResponseEntity<>(disciplinasService.listarPorLikes(), HttpStatus.OK);
    }

}
