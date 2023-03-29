package br.api.hallel.payload.resposta;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReceitasSemanaAtualResponse {
    private ArrayList<String> datas;
    private ArrayList<Double> valores;
}
