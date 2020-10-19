package com.fyp.prograd.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.Set;

@Entity
public class Position {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long positionId;
    private String positionName;
    private String positionDescription;
    //optional
    private double positionSalary;
    //optional
    private String positionUrl;
    private int clicks;
    @ManyToOne
    private Company company;
    @OneToMany
    private Set<Skill> requirements;

    public Position() {

    }

    public long getPositionId() {
        return positionId;
    }

    public void setPositionId(long positionId) {
        this.positionId = positionId;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    public String getPositionDescription() {
        return positionDescription;
    }

    public void setPositionDescription(String positionDescription) {
        this.positionDescription = positionDescription;
    }

    public double getPositionSalary() {
        return positionSalary;
    }

    public void setPositionSalary(double positionSalary) {
        this.positionSalary = positionSalary;
    }

    public String getPositionUrl() {
        return positionUrl;
    }

    public void setPositionUrl(String positionUrl) {
        this.positionUrl = positionUrl;
    }

    public int getClicks() {
        return clicks;
    }

    public void setClicks(int clicks) {
        this.clicks = clicks;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Set<Skill> getRequirements() {
        return requirements;
    }

    public void setRequirements(Set<Skill> requirements) {
        this.requirements = requirements;
    }
}
