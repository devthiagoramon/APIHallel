package br.api.hallel.moduloMoodle.payload.request;

import br.api.hallel.moduloMoodle.model.EnrolAssignments;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EnrolAssignmentsReq {
    private Long roleid;
    private Long contextid;
    private Long userid;
    private Long timemodified;
    private Long modifierid;
    private Long itemid;
    private Long sortorder;

    public EnrolAssignments toEnrolAssignmentsRequest(){

        EnrolAssignments request = new EnrolAssignments();
        request.setRoleid(getRoleid());
        request.setTimemodified(getTimemodified());
        request.setContextid(getContextid());
        request.setItemid(getItemid());
        request.setModifierid(getModifierid());
        request.setSortorder(getSortorder());
        request.setUserid(getUserid());

        return request;
    }

}
