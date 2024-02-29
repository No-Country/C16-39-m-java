package com.c1639.backend.model.favorite;

/*ESTA ENTIDAD NO SE REQUERIRA YA QUE LA ASOCIACION PARA
LOS FAVORITOS SE CREA SOLA CON ANOTACIONES DE HIBERNATE*/

/*@Entity(name = "favorites")
@Getter
@Setter
@ToString
@RequiredArgsConstructor*/
/**
 * User * --> 1 Favorite 1 --> * Movie
 * User * --> * Review
 * */

public class Favorite {

    /*@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;*/

    /*// OneToMany relationship needs to be added to the User model
    @ManyToOne
    private Movie movie;

    @ManyToMany(mappedBy = "movies")
    private Set<User> users = new HashSet<>();*/


}
