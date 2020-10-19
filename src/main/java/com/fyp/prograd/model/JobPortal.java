package com.fyp.prograd.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Set;

@Entity
public class JobPortal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long jobPortalId;
    private String jobPortalUrl;
    private String jobPortalName;
    @OneToMany
    private Set<Position> positions;

    public JobPortal() {

    }

    public long getJobPortalId() {
        return jobPortalId;
    }

    public void setJobPortalId(long jobPortalId) {
        this.jobPortalId = jobPortalId;
    }

    public String getJobPortalUrl() {
        return jobPortalUrl;
    }

    public void setJobPortalUrl(String jobPortalUrl) {
        this.jobPortalUrl = jobPortalUrl;
    }

    public String getJobPortalName() {
        return jobPortalName;
    }

    public void setJobPortalName(String jobPortalName) {
        this.jobPortalName = jobPortalName;
    }

    public Set<Position> getPositions() {
        return positions;
    }

    public void setPositions(Set<Position> positions) {
        this.positions = positions;
    }
}
