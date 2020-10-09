package edu.ap.projectteambisfits.photo;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class PhotoController {

    @Autowired
    private PhotoService service;

    @GetMapping(path = "/photos", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Photo> getAllPhotos() {
        return service.findAll();
    }

    @PostMapping("/photo")
    public ResponseEntity<?> addPhoto(@RequestParam("title") String title, @RequestParam("image") MultipartFile image,
            Model model) throws IOException {
        String id = service.addPhoto(title, image);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }

    @GetMapping(path = "/photo/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Photo getPhoto(@PathVariable String id, Model model) {
        Photo photo = service.getPhoto(id);
        model.addAttribute("title", photo.getTitle());
        model.addAttribute("image", Base64.getEncoder().encodeToString(photo.getImage().getData()).toString());
        return photo;
    }

    @DeleteMapping(path = "/photo/{id}")
    public ResponseEntity<?> deleteDefectById(@PathVariable String id) {
        service.deletePhoto(id);
        return new ResponseEntity<>("Photo deleted succesfully", HttpStatus.OK);
    }
}