package br.api.hallel.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VideosModulo {
    private int numVideo;
    private String linkVideo;
    private String tituloVideo;
}
