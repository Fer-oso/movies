package movies.service;

import java.util.List;
import javax.transaction.Transactional;
import movies.entity.Imagen;
import movies.repository.ImagenRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@Transactional
public class ImagenService {

    private final ImagenRepository imagenRepository;
    private final UploadFilesService uploadFilesService;

    public ImagenService(ImagenRepository imagenRepository,UploadFilesService uploadFilesService) {
        this.imagenRepository = imagenRepository;
        this.uploadFilesService = uploadFilesService;
    }
    
    public Imagen save(MultipartFile imagen)throws Exception{
        
        return imagenRepository.saveAndFlush(new Imagen(uploadFilesService.copy(imagen), imagen.getContentType()));
    }
    
    public List<Imagen> findAll()throws Exception{
        return imagenRepository.findAll();
    }
    
    public Imagen setDefaultImagen()throws Exception{
     String nombre = "default.jpg";
    return imagenRepository.findByNombre(nombre);
    }
   
}