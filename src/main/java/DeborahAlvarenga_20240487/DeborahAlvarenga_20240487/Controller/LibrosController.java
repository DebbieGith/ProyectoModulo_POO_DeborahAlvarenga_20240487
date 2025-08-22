package DeborahAlvarenga_20240487.DeborahAlvarenga_20240487.Controller;

import DeborahAlvarenga_20240487.DeborahAlvarenga_20240487.Model.DTO.LibrosDTO;
import DeborahAlvarenga_20240487.DeborahAlvarenga_20240487.Service.LibrosServices;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Controller
@RequestMapping("/api/libros")
public class LibrosController {

    //Aca estoy permitiendo y dando acceso a los metodos del service con el CRUD
    @Autowired
    private LibrosServices access;

    // GET
    @GetMapping("/getLibro")
    public List<LibrosDTO> getAllLibros() {
        return access.getAllLibros();
    }

    // BUSCAR
    @GetMapping("/getLibroOne/{id}")
    public LibrosDTO getLibro(@PathVariable Long id){
        return access.getLibro(id);
    }

    // POST - METODO INSERTAR
    @PostMapping("/insertLibro")
    public ResponseEntity<Map<String, Object>> registerLibro
    (@Valid @RequestBody LibrosDTO librosDTO, BindingResult bindingResult, HttpServletRequest request){
        if (bindingResult.hasErrors()){
            Map<String, String>ValidationErros = new HashMap<>();
            bindingResult.getFieldErrors().forEach(error ->
                    ValidationErros.put(error.getField(),error.getDefaultMessage()
                    ));
            return ResponseEntity.badRequest().body(Map.of(
                    "status", "Error de validación",
                    "errors", ValidationErros
            ));
        }
        try{
            LibrosDTO reply = access.insertLibro(librosDTO);
            if (reply == null){
                //Creamos la respuesta con el codigo (400) badRequest
                return ResponseEntity.badRequest().body(Map.of(
                        "status", "Inserción correcta",
                        "errorType", "VALIDATION_ERROR",
                        "message", "Datos del Libro invalidos"
                ));
            } else {
                return ResponseEntity.status(HttpStatus.CREATED).body(Map.of(
                        "status", "Success",
                        "data", reply
                ));

            }
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of(
                    "status", "error",
                    "message", "Error al registrar el Libro",
                    "detail", e.getMessage()
            ));
        }

    }

    // PUT --- ACTUALIZAR
    @PutMapping("/updateLibro/{id}")
    public ResponseEntity<?> updateLibro
            (@PathVariable Long id, @Valid @RequestBody LibrosDTO librosDTO, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            Map<String, String>ValidationErros = new HashMap<>();
            bindingResult.getFieldErrors().forEach(error ->
                    ValidationErros.put(error.getField(),error.getDefaultMessage()
                    ));
            return ResponseEntity.badRequest().body(Map.of(
                    "status", "ERROR_DE_VALIDACION",
                    "errors", ValidationErros
            ));
        }

    }

}

