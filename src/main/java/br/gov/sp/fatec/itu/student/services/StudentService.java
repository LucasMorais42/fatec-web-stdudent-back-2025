package br.gov.sp.fatec.itu.student.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.gov.sp.fatec.itu.student.entities.Student;
import br.gov.sp.fatec.itu.student.repositories.StudentRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class StudentService {

    @Autowired
    private StudentRepository repository;

    public List<Student> getAll(){
        return repository.findAll();
    }
    
    public Student save(Student student){
        return repository.save(student);
    }

    
    public void update(Student student, long id){
        Student aux = repository.getReferenceById(id); //Pegando a referência de um estudante. Quando é necessário mostrar os dados desse aluno é ideal usar o findbyId
        aux.setCourse(student.getCourse());
        aux.setName(student.getName());

        repository.save(aux); //Esse comando na prática representa um UPDATE do SQL. Estamos salvando ele no banco de dados novamente.
    }

    public void delete(long id){ //Para remover você passa o ID do student que quer remover e não retorna nada.
        if(repository.existsById(id)){ //Verificando se no repositório existe um aluno com o ID informado.
            repository.deleteById(id);
        }
        else{
            throw new EntityNotFoundException("Aluno não cadastrado!"); //Se o aluno não existir estamos retornando essa mensagem.
        }
    }
 

}
