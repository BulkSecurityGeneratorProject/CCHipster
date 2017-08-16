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
    private Long caseNr;

    @Column(name = "case_no")
    private String caseNo;

    @Column(name = "person_nr")
    private String personNr;

    @Column(name = "company_nr")
    private String companyNr;

    @Column(name = "agios_node_name")
    private String agiosNodeName;

    @Column(name = "workflow_uid")
    private String workflowUid;

    @Column(name = "reasons")
    private String reasons;

    @Column(name = "status_uid")
    private String statusUid;

    @Column(name = "current_step_uid")
    private String currentStepUid;

    @Column(name = "code_name")
    private String codeName;

    @Column(name = "evt_open")
    private String evtOpen;

    @Column(name = "display_name")
    private String displayName;

    @Column(name = "evtclose")
    private String evtclose;

    @Column(name = "agios_entity")
    private String agiosEntity;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCaseNr() {
        return caseNr;
    }

    public AgiosCase caseNr(Long caseNr) {
        this.caseNr = caseNr;
        return this;
    }

    public void setCaseNr(Long caseNr) {
        this.caseNr = caseNr;
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

    public String getReasons() {
        return reasons;
    }

    public AgiosCase reasons(String reasons) {
        this.reasons = reasons;
        return this;
    }

    public void setReasons(String reasons) {
        this.reasons = reasons;
    }

    public String getStatusUid() {
        return statusUid;
    }

    public AgiosCase statusUid(String statusUid) {
        this.statusUid = statusUid;
        return this;
    }

    public void setStatusUid(String statusUid) {
        this.statusUid = statusUid;
    }

    public String getCurrentStepUid() {
        return currentStepUid;
    }

    public AgiosCase currentStepUid(String currentStepUid) {
        this.currentStepUid = currentStepUid;
        return this;
    }

    public void setCurrentStepUid(String currentStepUid) {
        this.currentStepUid = currentStepUid;
    }

    public String getCodeName() {
        return codeName;
    }

    public AgiosCase codeName(String codeName) {
        this.codeName = codeName;
        return this;
    }

    public void setCodeName(String codeName) {
        this.codeName = codeName;
    }

    public String getEvtOpen() {
        return evtOpen;
    }

    public AgiosCase evtOpen(String evtOpen) {
        this.evtOpen = evtOpen;
        return this;
    }

    public void setEvtOpen(String evtOpen) {
        this.evtOpen = evtOpen;
    }

    public String getDisplayName() {
        return displayName;
    }

    public AgiosCase displayName(String displayName) {
        this.displayName = displayName;
        return this;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getEvtclose() {
        return evtclose;
    }

    public AgiosCase evtclose(String evtclose) {
        this.evtclose = evtclose;
        return this;
    }

    public void setEvtclose(String evtclose) {
        this.evtclose = evtclose;
    }

    public String getAgiosEntity() {
        return agiosEntity;
    }

    public AgiosCase agiosEntity(String agiosEntity) {
        this.agiosEntity = agiosEntity;
        return this;
    }

    public void setAgiosEntity(String agiosEntity) {
        this.agiosEntity = agiosEntity;
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
            ", caseNo='" + getCaseNo() + "'" +
            ", personNr='" + getPersonNr() + "'" +
            ", companyNr='" + getCompanyNr() + "'" +
            ", agiosNodeName='" + getAgiosNodeName() + "'" +
            ", workflowUid='" + getWorkflowUid() + "'" +
            ", reasons='" + getReasons() + "'" +
            ", statusUid='" + getStatusUid() + "'" +
            ", currentStepUid='" + getCurrentStepUid() + "'" +
            ", codeName='" + getCodeName() + "'" +
            ", evtOpen='" + getEvtOpen() + "'" +
            ", displayName='" + getDisplayName() + "'" +
            ", evtclose='" + getEvtclose() + "'" +
            ", agiosEntity='" + getAgiosEntity() + "'" +
            "}";
    }
}
