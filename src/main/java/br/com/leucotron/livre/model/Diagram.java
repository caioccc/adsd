package br.com.leucotron.livre.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import br.com.leucotron.livre.core.model.Model;

/**
 * Model for table: Diagram.
 *
 * @author Virtus
 */
@Entity(name = "diagram")
public class Diagram extends Model<Integer> {

	/**
	 * Diagram ID.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "iddiagram")
	private Integer idDiagram;

	/**
	 * Name.
	 */
	@Column(name = "name")
	private String name;

	/**
	 * Version.
	 */
	@Column(name = "version")
	private String version;

	/**
	 * Tags.
	 */
	@Column(name = "tags")
	private String tags;

	/**
	 * Date for Created and Update.
	 */
	@Column(name = "dateupdate")
	private Date dateUpdate;

	/**
	 * Date for Created and Update.
	 */
	@Column(name = "startdate")
	private Date startDate;

	/**
	 * Date for Created and Update.
	 */
	@Column(name = "enddate")
	private Date endDate;

	/**
	 * User.
	 */
	@OneToOne()
	@JoinColumn(name = "iduser")
	private User user;

	/**
	 * Project.
	 */
	@OneToOne()
	@JoinColumn(name = "idproject")
	private Project project;

	/**
	 * Constructor.
	 */
	public Diagram() {
	}

	public Diagram(String name, String version, String tags, Date dateUpdate, Date startDate,
			Date endDate, User user, Project project) {
		super();
		this.name = name;
		this.version = version;
		this.tags = tags;
		this.dateUpdate = dateUpdate;
		this.startDate = startDate;
		this.endDate = endDate;
		this.user = user;
		this.project = project;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public Date getDateUpdate() {
		return dateUpdate;
	}

	public void setDateUpdate(Date dateUpdate) {
		this.dateUpdate = dateUpdate;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Integer getIdDiagram() {
		return idDiagram;
	}

	public void setIdDiagram(Integer idDiagram) {
		this.idDiagram = idDiagram;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	@Override
	public Integer getId() {
		return this.idDiagram;
	}

	@Override
	public void setId(Integer id) {
		this.idDiagram = id;

	}

}
