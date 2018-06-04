package br.com.leucotron.livre.dto;

import java.util.Date;

import javax.validation.constraints.NotNull;

import br.com.leucotron.livre.core.dto.ModelDTO;

/**
 * Data Transient Object for: Diagram.
 * 
 * @author virtus
 *
 */
public class DiagramDTO extends ModelDTO {

	private Integer id;

	@NotNull
	private String name;

	@NotNull
	private String version;

	@NotNull
	private Integer idProject;

	private Integer idOrganization;

	private String tags;

	private Date dateUpdate;

	@NotNull
	private Date startDate;

	@NotNull
	private Date endDate;

	private String user;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public Integer getIdProject() {
		return idProject;
	}

	public void setIdProject(Integer idProject) {
		this.idProject = idProject;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public Date getDateUpdate() {
		return dateUpdate;
	}

	public void setDateUpdate(Date dateUpdate) {
		this.dateUpdate = dateUpdate;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Integer getIdOrganization() {
		return idOrganization;
	}

	public void setIdOrganization(Integer idOrganization) {
		this.idOrganization = idOrganization;
	}

}
