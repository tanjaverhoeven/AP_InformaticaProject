package edu.ap.projectteambisfits.photo;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public interface PhotoService {

    List<Photo> findAll();

    String addPhoto(String title, MultipartFile file) throws IOException;

    Photo getPhoto(String id);

    void deletePhoto(String id);

}