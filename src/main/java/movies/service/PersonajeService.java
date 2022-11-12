package movies.service;

import java.util.List;
import javax.transaction.Transactional;
import movies.entity.Personaje;
import movies.repository.PersonajeRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@Transactional
public class PersonajeService {

    private final PersonajeRepository personajeRepository;
    private final ImagenService imagenService;

    public PersonajeService(PersonajeRepository personajeRepository, ImagenService imagenService) {
        this.personajeRepository = personajeRepository;
        this.imagenService = imagenService;
    }

    public Personaje save(Personaje personaje, MultipartFile imagen) throws Exception {
        try {
            if (imagen == null || imagen.isEmpty()) {
                personaje.setImagen(imagenService.setDefaultImagen());
            } else {
                personaje.setImagen(imagenService.save(imagen));
            }
            return personajeRepository.saveAndFlush(personaje);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public List<Personaje> findAll() throws Exception {
        if (!personajeRepository.findAll().isEmpty()) {
            return personajeRepository.findAll();
        } else {
            throw new Exception("no content loaded");
        }
    }

    public Personaje update(int id, Personaje personaje, MultipartFile imagen) throws Exception {

        if (findById(id).getId() == personaje.getId()) { // if the recieved id is equals to id of parameter personaje
            if (imagen == null || imagen.isEmpty()) { // i ask if the image recieved is equals null o empty
                if (findById(id).getImagen() == null) {// if it is true i ask if the personaje saved in the database DOESNT have one image 
                    personaje.setImagen(imagenService.setDefaultImagen()); //if it is true  set the defaultimage
                } else {
                    personaje.setImagen(findById(id).getImagen());//else set the image of the database
                }
                return personajeRepository.saveAndFlush(personaje);//save the personaje in databass and return the response
            }
            personaje.setImagen(imagenService.save(imagen));//if image doest empty or null  set the image recieved
            return personajeRepository.saveAndFlush(personaje);// save in the database and return the response
        } else {
            throw new Exception("id not missmatch");//if the id doest missmatch. throw expection message
        }
    }

    public Boolean deleteById(int id) throws Exception {
        personajeRepository.deleteById(id);
        return true;
    }

    public Personaje findById(int id) throws Exception {
        return personajeRepository.findById(id).orElseThrow();
    }
    
   
    
}
