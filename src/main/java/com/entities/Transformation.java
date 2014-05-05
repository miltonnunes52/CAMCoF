package com.entities;
// default package
// Generated 5/Mai/2014 15:56:04 by Hibernate Tools 4.0.0

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Transformation generated by hbm2java
 */
@Entity
@Table(name = "transformation", catalog = "camcof")
public class Transformation implements java.io.Serializable {

	private Integer idTransformation;
	private TransformationLevel transformationLevel;
	private String description;

	public Transformation() {
	}

	public Transformation(TransformationLevel transformationLevel) {
		this.transformationLevel = transformationLevel;
	}

	public Transformation(TransformationLevel transformationLevel,
			String description) {
		this.transformationLevel = transformationLevel;
		this.description = description;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "idTransformation", unique = true, nullable = false)
	public Integer getIdTransformation() {
		return this.idTransformation;
	}

	public void setIdTransformation(Integer idTransformation) {
		this.idTransformation = idTransformation;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "Transformation_level_idTransformation_level", nullable = false)
	public TransformationLevel getTransformationLevel() {
		return this.transformationLevel;
	}

	public void setTransformationLevel(TransformationLevel transformationLevel) {
		this.transformationLevel = transformationLevel;
	}

	@Column(name = "description", length = 45)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
