package settings.model;

import lombok.Data;

@Data
public class ResponseModel {

    String errorCode;
    String error;
    String errorMessage;
    String status;
}