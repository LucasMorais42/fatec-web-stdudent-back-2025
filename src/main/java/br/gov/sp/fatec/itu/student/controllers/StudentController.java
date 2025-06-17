package br.gov.sp.fatec.itu.student.controllers;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.gov.sp.fatec.itu.student.entities.Student;

import br.gov.sp.fatec.itu.student.services.StudentService;
import jakarta.persistence.EntityNotFoundException;

import java.util.List;

@RestController
@RequestMapping("students")
@CrossOrigin
public class StudentController {

    @Autowired
    private StudentService service;

    @GetMapping
    public ResponseEntity<List<Student>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }
    

    @PostMapping
    public ResponseEntity<Student> save(@RequestBody Student student){
        return ResponseEntity.created(null)
                             .body(service.save(student));
    }
    
    @DeleteMapping("{id}") //Os métodos são os protocolos HTTP.
    public ResponseEntity<Void>delete(@PathVariable long id){ //O PathVariable serve como caminho igual faz o Angular, ficando students/1 por exemplo.
        service.delete(id);
        return ResponseEntity.noContent().build(); //Aqui retorna o 204
    }

    @PutMapping("{id}")
    public ResponseEntity<Void> update(@PathVariable long id, @RequestBody Student student){
        service.update(student, id);
        return ResponseEntity.noContent().build();
    }

}
