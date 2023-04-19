package br.api.hallel.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ModulosCurso {
    private Integer numModulo;
    private String tituloModulo;
    private ArrayList<VideosModulo> videosModulo;

}
