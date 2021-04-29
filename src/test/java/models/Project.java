package models;

import enums.ProjectType;

public class Project {
    private String name;
    private String announcement;
    private boolean isShowAnnouncement;
    private ProjectType type;


    public String getName() {
        return name;
    }

    public Project setName(String name) {
        this.name = name;
        return this;
    }

    public String getAnnouncement() {
        return announcement;
    }

    public Project setAnnouncement(String announcement) {
        this.announcement = announcement;
        return this;
    }

    public boolean isShowAnnouncement() {
        return isShowAnnouncement;
    }

    public Project setShowAnnouncement(boolean showAnnouncement) {
        isShowAnnouncement = showAnnouncement;
        return this;
    }

    public ProjectType getType() {
        return type;
    }

    public Project setType(ProjectType type) {
        this.type = type;
        return this;
    }
}
