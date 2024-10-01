package br.api.hallel.moduloAPI.exceptions;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ApiError {
    private Integer errorCode;
    private String description;
    private Date date;
}
