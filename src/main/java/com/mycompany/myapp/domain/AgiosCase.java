package com.mycompany.myapp.domain;

import io.swagger.annotations.ApiModel;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * not an ignored comment
 */
@ApiModel(description = "not an ignored comment")
@Entity
@Table(name = "agios_case")
public class AgiosCase implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "case_nr")
    private String caseNr;

    @Column(name = "person_nr")
    private String personNr;

    @Column(name = "company_nr")
    private String companyNr;

    @Column(name = "agios_node_name")
    private String agiosNodeName;

    @Column(name = "workflow_uid")
    private String workflowUid;

    @Column(name = "case_no")
    private String caseNo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCaseNr() {
        return caseNr;
    }

    public AgiosCase caseNr(String caseNr) {
        this.caseNr = caseNr;
        return this;
    }

    public void setCaseNr(String caseNr) {
        this.caseNr = caseNr;
    }

    public String getPersonNr() {
        return personNr;
    }

    public AgiosCase personNr(String personNr) {
        this.personNr = personNr;
        return this;
    }

    public void setPersonNr(String personNr) {
        this.personNr = personNr;
    }

    public String getCompanyNr() {
        return companyNr;
    }

    public AgiosCase companyNr(String companyNr) {
        this.companyNr = companyNr;
        return this;
    }

    public void setCompanyNr(String companyNr) {
        this.companyNr = companyNr;
    }

    public String getAgiosNodeName() {
        return agiosNodeName;
    }

    public AgiosCase agiosNodeName(String agiosNodeName) {
        this.agiosNodeName = agiosNodeName;
        return this;
    }

    public void setAgiosNodeName(String agiosNodeName) {
        this.agiosNodeName = agiosNodeName;
    }

    public String getWorkflowUid() {
        return workflowUid;
    }

    public AgiosCase workflowUid(String workflowUid) {
        this.workflowUid = workflowUid;
        return this;
    }

    public void setWorkflowUid(String workflowUid) {
        this.workflowUid = workflowUid;
    }

    public String getCaseNo() {
        return caseNo;
    }

    public AgiosCase caseNo(String caseNo) {
        this.caseNo = caseNo;
        return this;
    }

    public void setCaseNo(String caseNo) {
        this.caseNo = caseNo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AgiosCase agiosCase = (AgiosCase) o;
        if (agiosCase.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), agiosCase.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "AgiosCase{" +
            "id=" + getId() +
            ", caseNr='" + getCaseNr() + "'" +
            ", personNr='" + getPersonNr() + "'" +
            ", companyNr='" + getCompanyNr() + "'" +
            ", agiosNodeName='" + getAgiosNodeName() + "'" +
            ", workflowUid='" + getWorkflowUid() + "'" +
            ", caseNo='" + getCaseNo() + "'" +
            "}";
    }
}
