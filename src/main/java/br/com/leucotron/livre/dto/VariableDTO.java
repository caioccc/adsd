package br.com.leucotron.livre.dto;

import java.util.Date;

import javax.validation.constraints.NotNull;

import br.com.leucotron.livre.core.dto.ModelDTO;

public class VariableDTO  extends ModelDTO {
	

    private Integer id;

    @NotNull
    private String name;

    private String description;

    @NotNull
    private String type;

    @NotNull
    private Integer idProject;
    
    //@NotNull
    private Integer idOrganization;

    private String tags;

    private Date dateUpdate;

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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

	public Integer getIdOrganization() {
		return idOrganization;
	}

	public void setIdOrganization(Integer idOrganization) {
		this.idOrganization = idOrganization;
	}
    
}
