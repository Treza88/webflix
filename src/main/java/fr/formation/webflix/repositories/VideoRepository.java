package fr.formation.webflix.repositories;

import fr.formation.webflix.entities.VideoEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VideoRepository extends CrudRepository<VideoEntity,Long> {// Long = type de la primary key

    Iterable<VideoEntity> findAllByDateDeletedIsNull();
    Optional<VideoEntity> findByIdAndDateDeletedIsNull(Long id);
}
