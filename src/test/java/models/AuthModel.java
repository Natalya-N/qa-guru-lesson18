package models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class AuthModel {
    String userId;
    String username;
    String password;
    String token;
    String expires;
    @JsonProperty("created_date")
    String createdDate;
    String isActive;
}