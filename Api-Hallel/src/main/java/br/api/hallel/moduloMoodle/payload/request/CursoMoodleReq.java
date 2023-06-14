package br.api.hallel.moduloMoodle.payload.request;

import br.api.hallel.moduloMoodle.model.CursoMoodle;
import jakarta.persistence.Id;
import lombok.Data;

@Data
public class CursoMoodleReq {

    private String fullname;
    private String shortname;
    private String idnumber;
    private String summary;
    private Integer summaryformat;
    private String format;
    private Integer showgrades;
    private boolean enablecompletion;
    private boolean completionnotify;

    public CursoMoodle toCursoMoodle() {
        CursoMoodle cursoMoodle = new CursoMoodle();
        cursoMoodle.setFullname(this.fullname);
        cursoMoodle.setShortname(this.shortname);
        cursoMoodle.setIdnumber(this.idnumber);
        cursoMoodle.setSummary(this.summary);
        cursoMoodle.setFormat(this.format);
        cursoMoodle.setShowgrades(this.showgrades);
        cursoMoodle.setCompletionnotify(this.completionnotify);
        cursoMoodle.setSummaryformat(this.summaryformat);
        return cursoMoodle;
    }
}
