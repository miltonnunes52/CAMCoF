package com.entities;
// default package
// Generated 30/Abr/2014 16:41:17 by Hibernate Tools 3.4.0.CR1

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
 * ValueUnits generated by hbm2java
 */
@Entity
@Table(name = "value_units", catalog = "camcof")
public class ValueUnits implements java.io.Serializable {

	private Integer idvalueUnits;
	private SensingDataValue sensingDataValue;
	private String description;
	private String symbol;

	public ValueUnits() {
	}

	public ValueUnits(SensingDataValue sensingDataValue) {
		this.sensingDataValue = sensingDataValue;
	}

	public ValueUnits(SensingDataValue sensingDataValue, String description,
			String symbol) {
		this.sensingDataValue = sensingDataValue;
		this.description = description;
		this.symbol = symbol;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "idvalueUnits", unique = true, nullable = false)
	public Integer getIdvalueUnits() {
		return this.idvalueUnits;
	}

	public void setIdvalueUnits(Integer idvalueUnits) {
		this.idvalueUnits = idvalueUnits;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "Sensing_data_value_idSensing_data_value", nullable = false)
	public SensingDataValue getSensingDataValue() {
		return this.sensingDataValue;
	}

	public void setSensingDataValue(SensingDataValue sensingDataValue) {
		this.sensingDataValue = sensingDataValue;
	}

	@Column(name = "description", length = 45)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "symbol", length = 45)
	public String getSymbol() {
		return this.symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

}
