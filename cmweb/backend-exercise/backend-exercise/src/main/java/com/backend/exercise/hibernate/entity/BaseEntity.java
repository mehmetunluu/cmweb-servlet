package com.backend.exercise.hibernate.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

@MappedSuperclass
@JsonIgnoreProperties(ignoreUnknown =true)
public abstract class BaseEntity implements Serializable {
	

	private static final long serialVersionUID = 1914842698571907341L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="OID",length=32)
	private int oid;

	@Column(name = "create_date")
	protected Date create_date;


	public int getOid() {
		return oid;
	}

	public void setOid(int oid) {
		this.oid = oid;
	}

}
