package fr.formation.webflix.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="category")
public class CategoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 100,nullable = false)
    private String name;
    @OneToMany(mappedBy = "category")
    private Collection<VideoEntity> videos;
}
