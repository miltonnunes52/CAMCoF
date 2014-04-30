package com.entities;
// default package
// Generated 30/Abr/2014 16:41:17 by Hibernate Tools 3.4.0.CR1

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * HighlevelInformation generated by hbm2java
 */
@Entity
@Table(name = "highlevel-information", catalog = "camcof")
public class HighlevelInformation implements java.io.Serializable {

	private Integer idHighlevelInformation;
	private DataContext dataContext;
	private String description;
	private Set<MidlevelInformation> midlevelInformations = new HashSet<MidlevelInformation>(
			0);

	public HighlevelInformation() {
	}

	public HighlevelInformation(DataContext dataContext) {
		this.dataContext = dataContext;
	}

	public HighlevelInformation(DataContext dataContext, String description,
			Set<MidlevelInformation> midlevelInformations) {
		this.dataContext = dataContext;
		this.description = description;
		this.midlevelInformations = midlevelInformations;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "idHighlevel-information", unique = true, nullable = false)
	public Integer getIdHighlevelInformation() {
		return this.idHighlevelInformation;
	}

	public void setIdHighlevelInformation(Integer idHighlevelInformation) {
		this.idHighlevelInformation = idHighlevelInformation;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "Data_context_idData_context", nullable = false)
	public DataContext getDataContext() {
		return this.dataContext;
	}

	public void setDataContext(DataContext dataContext) {
		this.dataContext = dataContext;
	}

	@Column(name = "description", length = 45)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "highlevelInformation")
	public Set<MidlevelInformation> getMidlevelInformations() {
		return this.midlevelInformations;
	}

	public void setMidlevelInformations(
			Set<MidlevelInformation> midlevelInformations) {
		this.midlevelInformations = midlevelInformations;
	}

}
