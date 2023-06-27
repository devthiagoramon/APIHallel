package br.api.hallel.moduloMoodle.payload.response;

import br.api.hallel.moduloMoodle.model.EnrolAssignments;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EnrolAssignmentsResponse {
    private Long id;
    private Long roleid;
    private Long contextid;
    private Long userid;
    private Long timemodified;
    private Long modifierid;
    private Long itemid;
    private Long sortorder;

    public EnrolAssignmentsResponse toEnrolAssignmentsResponse(EnrolAssignments enrolAssignments){

        EnrolAssignmentsResponse response = new EnrolAssignmentsResponse();
        response.setId(enrolAssignments.getId());
        response.setContextid(enrolAssignments.getContextid());
        response.setUserid(enrolAssignments.getUserid());
        response.setTimemodified(enrolAssignments.getTimemodified());
        response.setModifierid(enrolAssignments.getModifierid());
        response.setItemid(enrolAssignments.getItemid());
        response.setSortorder(enrolAssignments.getSortorder());

        return response;
    }

}
