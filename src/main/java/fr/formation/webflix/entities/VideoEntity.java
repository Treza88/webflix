package fr.formation.webflix.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Collection;
import java.util.Optional;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "video")
public class VideoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, columnDefinition = "smallint unsigned")
    private Integer duration;

    @Column(length = 60, nullable = false)
    private String originCountry;

    @Column(columnDefinition = "text")
    private String synopsis;

    @Column(nullable = false)
    private String urlVideo;

    @Column(nullable = false)
    private String cover;

    @Column(columnDefinition = "smallint unsigned")
    private Integer productYear;

    private Calendar datePublished;

    private Calendar dateDeleted;

    //    @ManyToMany(mappedBy = "videos")  //a été modifier en OneToMany cidessous
//    private Collection<ProfileEntity> profiles;
    @OneToMany(mappedBy = "video")
    private Collection<VideoProfileEntity> profiles;

    @ManyToMany
    @JoinTable(
            name = "video_actor",
            joinColumns = @JoinColumn(name = "video_id"),
            inverseJoinColumns = @JoinColumn(name = "actor_id")
    )

    private Collection<ActorEntity> actors;

    @JsonBackReference
    @ToString.Exclude
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
//, fetch = FetchType.LAZY) ne vas pas recupérer la class categorie
    private CategoryEntity category;


}
