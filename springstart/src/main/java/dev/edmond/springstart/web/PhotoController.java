package dev.edmond.springstart.web;

import java.io.IOException;
import java.net.http.HttpHeaders;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import dev.edmond.springstart.models.Photo;
import dev.edmond.springstart.repository.PhotoRepository;

@RestController
@RequestMapping("/photos")
public class PhotoController {

    @Autowired
    private PhotoRepository repo;

    @GetMapping("")
    public Set<UUID> getAllPersons() {
        System.out.println("test");
        List<Photo> allPhotos = (List<Photo>) repo.findAll();
        Set<UUID> allPhotosIds = new HashSet<>();
        for (Photo photo : allPhotos) {
            allPhotosIds.add(photo.getId());
        }

        return allPhotosIds;
    }

    @GetMapping("/{photoId}")
    public ResponseEntity<byte[]> getPhotoById(@PathVariable String photoId) {
        Photo photo = repo.findById(UUID.fromString(photoId)).get();
        return ResponseEntity.ok().header("Content-Type", photo.getContentType()).body(photo.getContent());
    }

    @PostMapping("")
    public String handleImageUpload(@RequestParam("image") MultipartFile file,
            RedirectAttributes redirectAttributes) throws IOException {

        Photo newPhoto = Photo
                .builder()
                .content(file.getBytes())
                .contentType(file.getContentType())
                .originalFilename(file.getOriginalFilename())
                .build();

        Photo photo = repo.save(newPhoto);

        return photo.getId().toString();
    }

}
