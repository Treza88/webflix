package fr.formation.webflix.repositories;


import fr.formation.webflix.entities.CategoryEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends CrudRepository<CategoryEntity,Long> {

}
