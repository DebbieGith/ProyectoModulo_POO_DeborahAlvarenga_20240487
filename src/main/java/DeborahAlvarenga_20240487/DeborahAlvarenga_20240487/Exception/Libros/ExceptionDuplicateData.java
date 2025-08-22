package DeborahAlvarenga_20240487.DeborahAlvarenga_20240487.Exception.Libros;

import lombok.Getter;

public class ExceptionDuplicateData extends RuntimeException {
    @Getter
    private String duplicateField;

    public ExceptionDuplicateData(String message, String duplicateField) {

        super(message);
        this.duplicateField = duplicateField;
    }
    public ExceptionDuplicateData(String duplicateField){
        this.duplicateField = duplicateField;
    }
}
