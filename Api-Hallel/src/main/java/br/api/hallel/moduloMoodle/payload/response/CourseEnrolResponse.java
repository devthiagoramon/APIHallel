package br.api.hallel.moduloMoodle.payload.response;

import br.api.hallel.moduloMoodle.model.CourseEnrol;
import lombok.Data;
@Data
public class CourseEnrolResponse {

    private Long id;
    private Long status;
    private String enrol;
    private Long courseId;
    private Long timecreated;
    private Long timemodified;

    public CourseEnrolResponse toCourseEnrolResponse(CourseEnrol courseEnrol){
        CourseEnrolResponse response = new CourseEnrolResponse();
        response.setId(courseEnrol.getId());
        response.setStatus(courseEnrol.getStatus());
        response.setEnrol(courseEnrol.getEnrol());
        response.setCourseId(courseEnrol.getCourseId());
        response.setTimecreated(courseEnrol.getTimecreated());
        response.setTimemodified(courseEnrol.getTimemodified());
        return response;
    }

}
