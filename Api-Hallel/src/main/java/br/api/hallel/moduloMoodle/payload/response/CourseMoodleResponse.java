package br.api.hallel.moduloMoodle.payload.response;

import br.api.hallel.moduloMoodle.model.CursoMoodle;
import lombok.Data;

@Data
public class CourseMoodleResponse {

    private Long id;
    private String fullname;
    private String shortname;
    private String idnumber;
    private String summary;
    private Integer summaryformat;
    private String format;
    private Integer showgrades;
    private boolean enablecompletion;
    private boolean completionnotify;

    public CourseMoodleResponse toResponse(CursoMoodle cursoMoodle){

        CourseMoodleResponse response = new CourseMoodleResponse();
        response.setId(cursoMoodle.getId());
        response.setFullname(cursoMoodle.getFullname());
        response.setShortname(cursoMoodle.getShortname());
        response.setCompletionnotify(cursoMoodle.isCompletionnotify());
        response.setSummary(cursoMoodle.getSummary());
        response.setSummaryformat(cursoMoodle.getSummaryformat());
        response.setFormat(cursoMoodle.getFormat());
        response.setEnablecompletion(cursoMoodle.isEnablecompletion());
        response.setShowgrades(cursoMoodle.getShowgrades());

        return response;
    }

}
