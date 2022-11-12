package movies.controller;

import movies.entity.Genero;
import movies.service.GeneroService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/generos")
public class GeneroController {

    private final GeneroService generoService;

    public GeneroController(GeneroService generoService) {
        this.generoService = generoService;
    }

    @PostMapping
    private ResponseEntity<?> save(@RequestBody Genero genero) throws Exception {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(generoService.save(genero));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping
    private ResponseEntity<?> findAll() throws Exception{
        try {
            return ResponseEntity.status(HttpStatus.OK).body(generoService.findAll());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(e.getMessage());
        }
    }
    @PutMapping("/{id}")
    private ResponseEntity<?> update(@PathVariable int id,@RequestBody Genero genero)throws Exception{
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(generoService.update(genero));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }    
    }
    
    @DeleteMapping("/{id}")
    private ResponseEntity<?> deleteById(@PathVariable int id)throws Exception{
        try {
            return ResponseEntity.status(HttpStatus.OK).body(generoService.deleteById(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
