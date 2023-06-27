package br.api.hallel.moduloMoodle.payload.response;

import br.api.hallel.moduloMoodle.model.UserEnrolments;
import lombok.Data;

@Data
public class UserEnrolmentsResponse {
    private Long id;
    private Long status;
    private Long userid;
    private Long enrolid; //id matricula
    private Long timestart;
    private Long timeend;
    private Long modifierId;
    private Long timecreated;
    private Long timemodified;

    public UserEnrolmentsResponse toResponse(UserEnrolments matriculas){
        UserEnrolmentsResponse response = new UserEnrolmentsResponse();

        response.setId(matriculas.getId());
        response.setEnrolid(matriculas.getEnrolid());
        response.setStatus(matriculas.getStatus());
        response.setTimestart(matriculas.getTimestart());
        response.setTimeend(matriculas.getTimeend());
        response.setTimecreated(matriculas.getTimecreated());
        response.setTimemodified(matriculas.getTimemodified());
        response.setModifierId(matriculas.getModifierId());

        return response;
    }

}
