package DeborahAlvarenga_20240487.DeborahAlvarenga_20240487.Entity;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

//Colocamos la anotación "@Entity" para indicarle que debe comportarse como una Entidad
@Entity
@Getter @Setter @EqualsAndHashCode @ToString
@Table (name = "LIBROS")

public class LibrosEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_AUTORES_ID")
    @SequenceGenerator(name = "SQ_AUTORES_ID", sequenceName = "SQ_AUTORES_ID", allocationSize = 1)
    @Column (name = "ID")
    private Long id;

    @Column (name = "TITULO")
    private String titulo;

    @Column (name = "ISBN")
    private String isbn;

    @Column (name = "AÑO_PUBLICACION")
    private Long añoPublicacion;

    @Column (name = "GENERO")
    private String genero;

    @Column (name = "AUTOR_ID")
    private Long autorId;


}
