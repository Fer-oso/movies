package movies.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UploadFilesService {

    final Path rutaCarpeta = Paths.get("src//main//resources//images");
    
      public String copy(MultipartFile file) throws IOException {
       String uniqueFilename = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
       Files.copy(file.getInputStream(),rutaCarpeta.resolve(uniqueFilename));
       return uniqueFilename;
    }
}