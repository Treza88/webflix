package fr.formation.webflix.controllers.api;

import fr.formation.webflix.entities.VideoEntity;
import fr.formation.webflix.services.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ApiVideoControler {

    @Autowired
    private VideoService videoService;
    //@GetMapping(value = "/api/videos", Produces = MediaType.APPLICATION_ATOM_XML) // pour du XML

    @GetMapping("/api/videos") // pour du JSON
    @CrossOrigin("*")
    public Iterable<VideoEntity> indexVideo(){
        return videoService.findAllByDateDeletedIsNull();
    }

    @PostMapping(value = "/api/videos", consumes = {"application/json"}) // pour du JSON
    public VideoEntity addVideo(@RequestBody VideoEntity videoEntity){// recupere le corps de la requuet avec un videoEntity
       return videoService.save(videoEntity);
    }

    @GetMapping("/api/videos/{id}")
    public VideoEntity findOne(@PathVariable Long id) throws Exception {
        return videoService.findByIdAndDateDeletedIsNull(id).orElseThrow(()-> new Exception("Video non trouvé"));
    }

    @PutMapping("/api/videos/{id}")
    public VideoEntity update(@PathVariable Long id, @RequestBody  VideoEntity videoEntity){
        return videoService.findById(id).map(video -> {
            video.setName(videoEntity.getName());
            video.setDuration(videoEntity.getDuration());
            video.setOriginCountry(videoEntity.getOriginCountry());
            video.setSynopsis(videoEntity.getSynopsis());
            video.setUrlVideo(videoEntity.getUrlVideo());
            video.setCover(videoEntity.getCover());
            video.setProductYear(videoEntity.getProductYear());
            video.setDatePublished(videoEntity.getDatePublished());
            video.setCategory(videoEntity.getCategory());
            return videoService.save(video);
        })
                .orElseGet(()->{
           // videoEntity.setId(id);  /supprimmer mais sans savoir pourquoi
        return videoService.save(videoEntity);
        });
    }

    @DeleteMapping("/api/videos/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        videoService.deleteById(id);
        String msg = """
                {
                "message": "Vidéo supprimée"
                }
                """;
        return ResponseEntity.ok()
                .header("Content-Type","application/json; charset=UTF-8")
                .body(msg);

    }

}
