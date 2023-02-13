package br.api.hallel.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Getter
@Setter
public class MembroGoogle extends  Membro{

    private String nomeGoogle;
    private String emailGoogle;
    private String senhaGoogle;
    private String image;
    public MembroGoogle(){

    }

}
