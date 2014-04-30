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
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * TransformationLevel generated by hbm2java
 */
@Entity
@Table(name = "transformation_level", catalog = "camcof")
public class TransformationLevel implements java.io.Serializable {

	private Integer idTransformationLevel;
	private String level;
	private Set<Transformation> transformations = new HashSet<Transformation>(0);
	private Set<SensingData> sensingDatas = new HashSet<SensingData>(0);

	public TransformationLevel() {
	}

	public TransformationLevel(String level,
			Set<Transformation> transformations, Set<SensingData> sensingDatas) {
		this.level = level;
		this.transformations = transformations;
		this.sensingDatas = sensingDatas;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "idTransformation_level", unique = true, nullable = false)
	public Integer getIdTransformationLevel() {
		return this.idTransformationLevel;
	}

	public void setIdTransformationLevel(Integer idTransformationLevel) {
		this.idTransformationLevel = idTransformationLevel;
	}

	@Column(name = "level", length = 45)
	public String getLevel() {
		return this.level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "transformationLevel")
	public Set<Transformation> getTransformations() {
		return this.transformations;
	}

	public void setTransformations(Set<Transformation> transformations) {
		this.transformations = transformations;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "transformationLevel")
	public Set<SensingData> getSensingDatas() {
		return this.sensingDatas;
	}

	public void setSensingDatas(Set<SensingData> sensingDatas) {
		this.sensingDatas = sensingDatas;
	}

}
