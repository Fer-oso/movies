package movies.service;

import java.util.List;
import javax.transaction.Transactional;
import movies.entity.Genero;
import movies.repository.GeneroRepository;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class GeneroService {

    private final GeneroRepository generoRepository;

    public GeneroService(GeneroRepository generoRepository) {
        this.generoRepository = generoRepository;
    }

    public Genero save(Genero genero) throws Exception {
        try {
            return generoRepository.saveAndFlush(genero);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public List<Genero> findAll() throws Exception {
        if (generoRepository.findAll().isEmpty()) {
           throw new Exception();
        }
        return generoRepository.findAll();
    }

    public Genero update(Genero genero) throws Exception {
        try {
            return generoRepository.saveAndFlush(genero);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public boolean deleteById(int id) throws Exception {
        if (generoRepository.existsById(id)) {
            generoRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

}
