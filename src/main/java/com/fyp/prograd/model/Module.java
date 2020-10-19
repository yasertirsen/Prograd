package com.fyp.prograd.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.ManyToMany;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.Set;

@Entity
@Table(name = "module")
public class Module {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long moduleId;
    private String moduleName;
    private String moduleDescription;
    @ManyToMany(cascade = CascadeType.ALL)
    private Set<Skill> moduleSkills;
    @OneToOne(cascade = CascadeType.ALL)
    private Industry moduleIndustry;

    public Module(String moduleName, String moduleDescription, Set<Skill> moduleSkills) {
        this.moduleName = moduleName;
        this.moduleDescription = moduleDescription;
        this.moduleSkills = moduleSkills;
    }

    public Module() {

    }

    public long getModuleId() {
        return moduleId;
    }

    public void setModuleId(long moduleId) {
        this.moduleId = moduleId;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public String getModuleDescription() {
        return moduleDescription;
    }

    public void setModuleDescription(String moduleDescription) {
        this.moduleDescription = moduleDescription;
    }

    public Set<Skill> getModuleSkills() {
        return moduleSkills;
    }

    public void setModuleSkills(Set<Skill> moduleSkills) {
        this.moduleSkills = moduleSkills;
    }

    public Industry getModuleIndustry() {
        return moduleIndustry;
    }

    public void setModuleIndustry(Industry moduleIndustry) {
        this.moduleIndustry = moduleIndustry;
    }
}
