package DeborahAlvarenga_20240487.DeborahAlvarenga_20240487.Model.DTO;


// En el DTO no se necesita agregar ninguna anotacion en especifico, como e el entity o las demas

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class LibrosDTO {

    private Long id;

    @NotBlank (message = "El titulo es un campo obligatorio")
    private String titulo;

    @Max(value = 20, message = "El maximo de caracteres del ISBN es 20")
    private String isbn;


    @NotBlank
    private String genero;

    private

}
