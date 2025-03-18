package models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class IsbnModel {
    private String isbn;

    public IsbnModel(String isbn) {
        this.isbn = isbn;
    }
}
