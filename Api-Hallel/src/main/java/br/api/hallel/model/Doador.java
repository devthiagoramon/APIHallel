package br.api.hallel.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Setter
@Getter
@Document
public class Doador extends Usuario{

    private Double docao;

}
