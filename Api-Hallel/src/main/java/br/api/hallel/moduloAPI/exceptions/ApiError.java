package br.api.hallel.moduloAPI.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiError {
    private Integer errorCode;
    private String description;
    private Date date;

}
