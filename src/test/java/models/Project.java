package models;

import enums.ProjectType;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Builder
public class Project {
    private String name;
    private String announcement;
    private boolean isShowAnnouncement;
    private ProjectType type;
}
