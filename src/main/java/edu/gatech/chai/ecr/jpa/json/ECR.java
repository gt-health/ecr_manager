package edu.gatech.chai.ecr.jpa.json;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize
@JsonDeserialize
public class ECR {
	@JsonProperty("Id")
	private String ecrId;
	@JsonProperty("Status")
	private String status;
	@JsonProperty("StatusLog")
	private String statusLog;
	@JsonProperty("Provider")
	protected List<Provider> provider = new ArrayList<Provider>();
	@JsonProperty("Facility")
	protected Facility facility = new Facility();
	@JsonProperty("Patient")
	protected Patient patient = new Patient();
	@JsonProperty("Sending Application")
	protected String sendingApplication = "";
	@JsonProperty("Notes")
	protected List<String> notes = new ArrayList<String>();
	
	public ECR () {}
	
	@JsonIgnore
	public String getECRId() {
		return ecrId;
	}
	
	@JsonIgnore
	public void setECRId(String ecrId) {
		this.ecrId = ecrId;
	}
	
	@JsonIgnore
	public String getId() {
		return ecrId;
	}
	
	@JsonIgnore
	public void setId(String Id) {
		this.ecrId = Id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getStatusLog() {
		return statusLog;
	}

	public void setStatusLog(String statusLog) {
		this.statusLog = statusLog;
	}

	public List<Provider> getProvider() {
		return provider;
	}
	
	public void setProvider(List<Provider> Provider) {
		this.provider = Provider;
	}

	public Facility getFacility() {
		return facility;
	}

	public void setFacility(Facility Facility) {
		this.facility = Facility;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient Patient) {
		this.patient = Patient;
	}
	
	public String getSendingApplication() {
		return sendingApplication;
	}

	public void setSendingApplication(String sendingApplication) {
		this.sendingApplication = sendingApplication;
	}
	
	public List<String> getNotes() {
		return notes;
	}

	public void setNotes(List<String> notes) {
		this.notes = notes;
	}
	
	public ParentGuardian findParentGuardianWithName(Name name) {
		for(ParentGuardian pg: patient.getparentsGuardians()) {
			if(pg.getname().compareTo(name) == 0)
				return pg;
		}
		return null;
	}
	
	public void update(ECR newECR) {
		if(newECR != null) {
			if(this.provider != null && newECR.getProvider() != null) {
				Collections.sort(newECR.getProvider());
				Collections.sort(this.provider);
				for(int i=0;i<newECR.provider.size();i++) { //Whack-a-Mole sort for unordered and non-unique lists
					Provider newECRProvider = newECR.getProvider().get(i);
					if(this.provider.contains(newECR.getProvider().get(i))){
						Provider oldECRProvider = null;
						for(int j=0;j<this.provider.size();j++) {
							oldECRProvider = this.provider.get(j);
							if(oldECRProvider.equals(newECRProvider)) { 
								oldECRProvider.update(newECRProvider);
							}
						}
					}
					else {
						this.provider.add(newECRProvider);
					}
				}
			}
			
			if(this.facility != null && newECR.getFacility() != null) {
				this.facility.update(newECR.getFacility());
			}
			
			if(this.patient != null && newECR.getPatient() != null) {
				this.patient.update(newECR.getPatient());
			}
			
			for(String newNote : newECR.getNotes()) {
				if(!this.notes.contains(newNote)) {
					this.notes.add(newNote);
				}
			}
			if(newECR.getSendingApplication() != null){
				this.sendingApplication = newECR.getSendingApplication();
			}
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ecrId == null) ? 0 : ecrId.hashCode());
		result = prime * result + ((facility == null) ? 0 : facility.hashCode());
		result = prime * result + ((notes == null) ? 0 : notes.hashCode());
		result = prime * result + ((patient == null) ? 0 : patient.hashCode());
		result = prime * result + ((provider == null) ? 0 : provider.hashCode());
		result = prime * result + ((sendingApplication == null) ? 0 : sendingApplication.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof ECR))
			return false;
		ECR other = (ECR) obj;
		if (ecrId == null) {
			if (other.ecrId != null)
				return false;
		} else if (!ecrId.equals(other.ecrId))
			return false;
		if (facility == null) {
			if (other.facility != null)
				return false;
		} else if (!facility.equals(other.facility))
			return false;
		if (notes == null) {
			if (other.notes != null)
				return false;
		} else if (!notes.equals(other.notes))
			return false;
		if (patient == null) {
			if (other.patient != null)
				return false;
		} else if (!patient.equals(other.patient))
			return false;
		if (provider == null) {
			if (other.provider != null)
				return false;
		} else if (!provider.equals(other.provider))
			return false;
		if (sendingApplication == null) {
			if (other.sendingApplication != null)
				return false;
		} else if (!sendingApplication.equals(other.sendingApplication))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ECR [ecrId=" + ecrId + ", provider=" + provider + ", facility=" + facility + ", patient=" + patient
				+ ", sendingApplication=" + sendingApplication + ", notes=" + notes + "]";
	}
}
