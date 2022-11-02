package fr.formation.webflix.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Collection;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="video")
public class VideoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private  String name;

    @Column(nullable = false,columnDefinition = "smallint unsigned")
    private  Integer duration;

    @Column(length = 60,nullable = false)
    private  String originCountry;

    @Column(columnDefinition = "text")
    private  String synopsis;

    @Column(nullable = false)
    private  String url_video;

    @Column(nullable = false)
    private  String cover;

    @Column(columnDefinition = "smallint unsigned")
    private  Integer product_year;

    private Calendar datePublished;

    private Calendar dateDeleted;

    @ManyToMany(mappedBy = "videos")
    private Collection<ProfileEntity> profiles;

}
