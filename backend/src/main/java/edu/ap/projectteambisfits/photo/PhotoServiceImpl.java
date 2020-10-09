package edu.ap.projectteambisfits.photo;

import java.io.IOException;
import java.util.List;

import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class PhotoServiceImpl implements PhotoService {

    @Autowired
    private PhotoRepository repo;

    @Override
    public List<Photo> findAll() {
        return repo.findAll();
    }

    @Override
    public String addPhoto(String title, MultipartFile file) throws IOException {
        Photo photo = new Photo(title, new Binary(BsonBinarySubType.BINARY, file.getBytes()));
        photo = repo.insert(photo);
        return photo.getId();
    }

    @Override
    public Photo getPhoto(String id) {
        return repo.findById(id).get();
    }

    @Override
    public void deletePhoto(String id) {
        repo.deleteById(id);
    }

}