package DeborahAlvarenga_20240487.DeborahAlvarenga_20240487.Service;


import DeborahAlvarenga_20240487.DeborahAlvarenga_20240487.Entities.LibrosEntity;
import DeborahAlvarenga_20240487.DeborahAlvarenga_20240487.Exception.Libros.ExceptionLibrosNotFound;
import DeborahAlvarenga_20240487.DeborahAlvarenga_20240487.Exception.Libros.ExceptionLibrosUnregistered;
import DeborahAlvarenga_20240487.DeborahAlvarenga_20240487.Model.DTO.LibrosDTO;
import DeborahAlvarenga_20240487.DeborahAlvarenga_20240487.Repository.LibrosRepository;
import jakarta.persistence.Id;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class LibrosServices {
    // El Autowired nos ayuda a poder acceder a los metodos de LibrosRepository para poder hacer el CRUD
    @Autowired
    private LibrosRepository repo;

    // METODO GET (OBTENER)

    public List<LibrosDTO> getAllLibros () {
        List<LibrosEntity>librosEntities = repo.findAll();
        return librosEntities.stream().map(this::convertToDTOLibros).collect(Collectors.toList());
    }
    public LibrosDTO getLibro (Long id){
        LibrosEntity librosEntity = repo.findById(id).
                orElseThrow(()-> new ExceptionLibrosNotFound("No se encontro el ID"));
        return convertToDTOLibros(librosEntity);
    }


    // METODO POST (INSERTAR)


    public LibrosDTO insertLibro (LibrosDTO objLibrosDTO){
        if (objLibrosDTO == null){
            throw new IllegalArgumentException("Ningun campo puede estar vacio");
        }try{
            LibrosEntity librosEntity = convertToEntityLibros(objLibrosDTO);

            LibrosEntity librosSave = repo.save(librosEntity);
            return convertToDTOLibros(librosSave);
        } catch (Exception e){
            log.error("Error al ingresar Libro");
            throw new ExceptionLibrosUnregistered("Error al ingresar un Libro");
        }
    }


    // METODO PUT (ACTUALIZAR)


    public LibrosDTO updateLibro (Long id, LibrosDTO librosDTO){
        LibrosEntity librosExisting = repo.findById(id).
                orElseThrow(()-> new ExceptionLibrosNotFound("ID no encontrado"));

        // Ahora vamos a actualizar los campos:
        librosExisting.setTitulo(librosDTO.getTitulo());

    }



    // Nos da los datos del backend para poder mostrarlos en el Fronend
    private LibrosDTO convertToDTOLibros (LibrosEntity librosEntity){
        //Creamos un objeto para accederr a la clase y asignar atributos
        LibrosDTO objLibrosDTO = new LibrosDTO();

        objLibrosDTO.setId(librosEntity.getId());
        objLibrosDTO.setTitulo(librosEntity.getTitulo());
        objLibrosDTO.setIsbn(librosEntity.getIsbn());
        objLibrosDTO.setGenero(librosEntity.getGenero());
        objLibrosDTO.setAñoPublicacion(librosEntity.getAñoPublicacion());
        objLibrosDTO.setAutorId(librosEntity.getAutorId());
        return objLibrosDTO;
    }

    private LibrosEntity convertToEntityLibros (LibrosDTO objLibrosDTO){
        LibrosEntity objLibrosEntity = new LibrosEntity();

        objLibrosEntity.setTitulo(objLibrosDTO.getTitulo());
        objLibrosEntity.setIsbn(objLibrosDTO.getIsbn());
        objLibrosEntity.setGenero(objLibrosDTO.getGenero());
        objLibrosEntity.setAñoPublicacion(objLibrosDTO.getAñoPublicacion());
        objLibrosEntity.setAutorId(objLibrosDTO.getAutorId());
        return objLibrosEntity;
    }

}
