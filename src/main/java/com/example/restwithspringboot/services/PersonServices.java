package com.example.restwithspringboot.services;


import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.restwithspringboot.exceptions.ResourceNotFoundException;
import com.example.restwithspringboot.model.Person;
import com.example.restwithspringboot.repository.PersonRepository;

// Utilizado pelo spring para injetar dados em outras classes durante a aplicação
@Service
public class PersonServices {

    private Logger logger = Logger.getLogger(PersonServices.class.getName());

    @Autowired
    PersonRepository repository;

    public List<Person> findAll(){
        logger.info("Finding All people");
        
        return repository.findAll();
    }

    public Person create(Person person){
        logger.info("Creating one people");
        return repository.save(person);
    }

    public Person update(Person person){
        logger.info("Updating one people");

        // Procura a pessoa pelo Id e após achar coloca em um atributo
        // para poder mudar os atributos
        Person entity = repository.findById(person.getId())
                        .orElseThrow(() -> new ResourceNotFoundException("ID não encontrado"));

        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setGender(person.getGender());
        entity.setAdress(person.getAdress());

        return repository.save(person);
    }

    public void delete(Long id){
        logger.info("Deleting one people");

         Person entity = repository.findById(id)
                        .orElseThrow(() -> new ResourceNotFoundException("ID não encontrado"));

        repository.delete(entity);
    }

    public Person findById(Long id){
        logger.info("Finding one person");

        return repository.findById(id)
                        .orElseThrow(() -> new ResourceNotFoundException("ID não encontrado"));
    }
}
