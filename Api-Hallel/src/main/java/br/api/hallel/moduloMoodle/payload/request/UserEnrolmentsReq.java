package br.api.hallel.moduloMoodle.payload.request;

import br.api.hallel.moduloMoodle.model.UserEnrolments;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserEnrolmentsReq {

    private Long status;
    private Long userid;
    private Long enrolid; //id matricula
    private Long timestart;
    private Long timeend;
    private Long modifierId;
    private Long timecreated;
    private Long timemodified;

    public UserEnrolments toUserMatriculas(){
        UserEnrolments matriculas = new UserEnrolments();
        matriculas.setEnrolid(getEnrolid());
        matriculas.setUserid(getUserid());
        matriculas.setStatus(getStatus());
        matriculas.setTimecreated(0L);
        matriculas.setTimestart(0L);
        matriculas.setTimeend(0L);
        matriculas.setModifierId(0L);
        matriculas.setTimemodified(0L);

        return  matriculas;
    }

}
