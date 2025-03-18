package models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class AddListOfBooksModel {
    String userId;
    List<IsbnModel> collectionOfIsbns;


}
