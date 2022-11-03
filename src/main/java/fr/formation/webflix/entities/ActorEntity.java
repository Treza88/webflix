package fr.formation.webflix.entities;

import fr.formation.webflix.enums.Gender;
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
@Table(name = "actor")
public class ActorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,length = 100)
    private String firstname;

    @Column( length = 100)
    private String lastname;

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Calendar birthday;

    @Column(nullable = false,columnDefinition = "VARCHAR(10) default 'MR'")
    @Enumerated(EnumType.STRING)
    private Gender gender;

    private String photo;

    @Column(columnDefinition = "TINYINT DEFAULT FALSE")
    private Boolean producer;

    @ManyToMany(mappedBy = "actors")
    private Collection<VideoEntity> videos;
}
