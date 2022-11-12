package fr.formation.webflix.services;

import fr.formation.webflix.entities.CategoryEntity;
import fr.formation.webflix.entities.VideoEntity;

import fr.formation.webflix.repositories.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VideoService {

    @Autowired
    private VideoRepository videoRepository;

    public VideoEntity save(VideoEntity video) {
        return videoRepository.save(video);
    }

    public Iterable<VideoEntity> findAll() {
        return videoRepository.findAll();
    }

    public Optional<VideoEntity> findById(Long id) {
        return videoRepository.findById(id);
    }

    public void deleteAll() {
        videoRepository.deleteAll();
    }

    public void deleteById(Long id) {
        videoRepository.deleteById(id);
    }

    public Iterable<VideoEntity> findAllByDateDeletedIsNull() {
        return videoRepository.findAllByDateDeletedIsNull();
    }
}
