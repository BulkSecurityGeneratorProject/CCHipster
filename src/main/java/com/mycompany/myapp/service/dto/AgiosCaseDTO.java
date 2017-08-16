package com.mycompany.myapp.service.dto;


import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the AgiosCase entity.
 */
public class AgiosCaseDTO implements Serializable {

    private Long id;

    private Long caseNr;

    private String caseNo;

    private String personNr;

    private String companyNr;

    private String agiosNodeName;

    private String workflowUid;

    private String reasons;

    private String statusUid;

    private String currentStepUid;

    private String codeName;

    private String evtOpen;

    private String displayName;

    private String evtclose;

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

    public void setCaseNr(Long caseNr) {
        this.caseNr = caseNr;
    }

    public String getCaseNo() {
        return caseNo;
    }

    public void setCaseNo(String caseNo) {
        this.caseNo = caseNo;
    }

    public String getPersonNr() {
        return personNr;
    }

    public void setPersonNr(String personNr) {
        this.personNr = personNr;
    }

    public String getCompanyNr() {
        return companyNr;
    }

    public void setCompanyNr(String companyNr) {
        this.companyNr = companyNr;
    }

    public String getAgiosNodeName() {
        return agiosNodeName;
    }

    public void setAgiosNodeName(String agiosNodeName) {
        this.agiosNodeName = agiosNodeName;
    }

    public String getWorkflowUid() {
        return workflowUid;
    }

    public void setWorkflowUid(String workflowUid) {
        this.workflowUid = workflowUid;
    }

    public String getReasons() {
        return reasons;
    }

    public void setReasons(String reasons) {
        this.reasons = reasons;
    }

    public String getStatusUid() {
        return statusUid;
    }

    public void setStatusUid(String statusUid) {
        this.statusUid = statusUid;
    }

    public String getCurrentStepUid() {
        return currentStepUid;
    }

    public void setCurrentStepUid(String currentStepUid) {
        this.currentStepUid = currentStepUid;
    }

    public String getCodeName() {
        return codeName;
    }

    public void setCodeName(String codeName) {
        this.codeName = codeName;
    }

    public String getEvtOpen() {
        return evtOpen;
    }

    public void setEvtOpen(String evtOpen) {
        this.evtOpen = evtOpen;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getEvtclose() {
        return evtclose;
    }

    public void setEvtclose(String evtclose) {
        this.evtclose = evtclose;
    }

    public String getAgiosEntity() {
        return agiosEntity;
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

        AgiosCaseDTO agiosCaseDTO = (AgiosCaseDTO) o;
        if(agiosCaseDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), agiosCaseDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "AgiosCaseDTO{" +
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
