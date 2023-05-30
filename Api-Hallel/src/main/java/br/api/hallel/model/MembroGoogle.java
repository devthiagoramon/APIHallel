package br.api.hallel.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
public class MembroGoogle extends  Membro{

    private String nomeGoogle;
    private String emailGoogle;
    private String senhaGoogle;
    public MembroGoogle(){

    }

}
