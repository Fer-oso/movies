package movies.controller;

import movies.entity.Personaje;
import movies.service.PersonajeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/personajes")
public class PersonajeController {

    private final PersonajeService personajeService;

    public PersonajeController(PersonajeService personajeService) {
        this.personajeService = personajeService;
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity<?> save(@RequestPart("personaje") Personaje personaje,@RequestPart(value="imagen",required = false) MultipartFile imagen) throws Exception {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(personajeService.save(personaje,imagen));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping
    private ResponseEntity<?> findAll() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(personajeService.findAll());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(e.getMessage());
        }
    }
    
    @GetMapping("/{id}")
    private ResponseEntity<?> findById(@PathVariable int id)throws Exception{
        try {
            return ResponseEntity.status(HttpStatus.OK).body(personajeService.findById(id));
        } catch (Exception e) {
           return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
    
    @PutMapping(value="/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity<?> update(@PathVariable int id,@RequestPart("personaje") Personaje personaje,@RequestPart(value="imagen",required = false)MultipartFile imagen)throws Exception{
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(personajeService.update(id,personaje,imagen));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
    
    @DeleteMapping("/{id}")
    private ResponseEntity<?> deleteById(@PathVariable int id)throws Exception{
        try {
            return ResponseEntity.status(HttpStatus.OK).body(personajeService.deleteById(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

}
