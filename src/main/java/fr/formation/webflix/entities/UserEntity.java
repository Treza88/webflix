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
@Table(name="user")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Temporal(TemporalType.DATE)
    private Calendar birthday;

    @Column(nullable = false, columnDefinition = "VARCHAR(10) default 'MR'")
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(length = 100, nullable = false)
    private String firstname;

    @Column(length = 100, nullable = false)
    private String lastname;

    @Column(length = 100, nullable = false)
    private String country;

    private Calendar dateCreated;
    private Calendar dateModified;
    private Calendar dateDeleted;

@OneToMany(mappedBy = "user")
    private Collection<ProfileEntity> profils;
}
