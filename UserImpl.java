package com.enviance.api.common;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(name = "ENVIANCE_USER")
@NamedQueries(value = {
		@NamedQuery(name = "User.getUsersByName", query = "select user from UserImpl user where user.name = :name")})
public class UserImpl implements Serializable {

	private static final long serialVersionUID = 1L;

	@XmlElement
	@Id
	@Column(name = "USER_ID")
	private Integer id;

	@XmlElement
	@Column(name = "FIRST_NAME")
	private String name;

	@XmlElement
	@Column(name = "LAST_NAME")
	private String surname;

	@XmlElement
	private String address;

	@XmlElement
	private String phone;

	@XmlElement
	private String occupation;

	public UserImpl() {
	}

	public UserImpl(Integer id, String name, String surname, String address, String phone, String occupation) {
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.address = address;
		this.phone = phone;
		this.occupation = occupation;
	}

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

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getOccupation() {
		return occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	@Override
	public boolean equals(Object o) {
		if (o instanceof UserImpl) {
			UserImpl u = (UserImpl) o;
			return id.equals(u.getId());
		} else if (o instanceof Integer) {
			return id.equals(o);
		} else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return id.hashCode();
	}
}
