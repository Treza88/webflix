package fr.formation.webflix.services;

import fr.formation.webflix.entities.CategoryEntity;
import fr.formation.webflix.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public CategoryEntity save(CategoryEntity category) {
        return categoryRepository.save(category);
    }

    public Iterable<CategoryEntity> findAll() {
        return categoryRepository.findAll();
    }

    public Optional<CategoryEntity> findById(Long id) {
        return categoryRepository.findById(id);
    }

    public void deleteAll() {
        categoryRepository.deleteAll();
    }

    public void deleteById(Long id) {
        categoryRepository.deleteById(id);
    }


//        public CategoryEntity findByTitle(CategoryEntity title){
//            @Query(value = "SELECT id FROM CategoryEntity")
//            List<Long> findByTitle(1);
//       }
}
