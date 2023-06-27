package br.api.hallel.moduloMoodle.payload.request;

import br.api.hallel.moduloMoodle.model.CourseEnrol;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseEnrolRequest {

    private Long status;
    private String enrol;
    private Long courseId;
    private Long timecreated;
    private Long timemodified;

    public CourseEnrol toCourseEnrolRequest (){

        CourseEnrol curso = new CourseEnrol();
        curso.setStatus(getStatus());
        curso.setEnrol(getEnrol());
        curso.setCourseId(getCourseId());
        curso.setTimecreated(0L);
        curso.setTimemodified(0L);

        return curso;
    }

}
