package movies.service;

import java.util.List;
import javax.transaction.Transactional;
import movies.entity.Produccion;
import movies.repository.ProduccionRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@Transactional
public class ProduccionService {

    private final ProduccionRepository produccionRepository;
    private final ImagenService imagenService;

    public ProduccionService(ProduccionRepository produccionRepository, ImagenService imagenService) {
        this.produccionRepository = produccionRepository;
        this.imagenService = imagenService;
    }

    public Produccion save(Produccion produccion, MultipartFile imagen) throws Exception {
        try {
            if (imagen == null || imagen.isEmpty()) {
                produccion.setImagen(imagenService.setDefaultImagen());
            } else {
                produccion.setImagen(imagenService.save(imagen));
            }
            return produccionRepository.saveAndFlush(produccion);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public List<Produccion> findAll() throws Exception {
        if (!produccionRepository.findAll().isEmpty()) {
            return produccionRepository.findAll();
        }else{
            throw new Exception();
        }
    }

    public Produccion update(int id, Produccion produccion, MultipartFile imagen) throws Exception {
        if (findById(id).getId() == produccion.getId()) {
            if (imagen == null || imagen.isEmpty()) {
                if (findById(id).getImagen() == null) {
                    produccion.setImagen(imagenService.setDefaultImagen());
                } else {
                    produccion.setImagen(findById(id).getImagen());
                }
                return produccionRepository.saveAndFlush(produccion);
            }
            produccion.setImagen(imagenService.save(imagen));
            return produccionRepository.saveAndFlush(produccion);
        } else {
            throw new Exception("id's not missmatch");
        }
    }

    public boolean deleteById(int id) throws Exception {
        produccionRepository.deleteById(id);
        return true;
    }

    public Produccion findById(int id) {
        return produccionRepository.findById(id).orElseThrow();
    }

}
