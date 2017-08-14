package com.mycompany.myapp.service.dto;


import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the AgiosCase entity.
 */
public class AgiosCaseDTO implements Serializable {

    private Long id;

    private String caseNr;

    private String personNr;

    private String companyNr;

    private String agiosNodeName;

    private String workflowUid;

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

    public void setCaseNr(String caseNr) {
        this.caseNr = caseNr;
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

    public String getCaseNo() {
        return caseNo;
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
            ", personNr='" + getPersonNr() + "'" +
            ", companyNr='" + getCompanyNr() + "'" +
            ", agiosNodeName='" + getAgiosNodeName() + "'" +
            ", workflowUid='" + getWorkflowUid() + "'" +
            ", caseNo='" + getCaseNo() + "'" +
            "}";
    }
}
