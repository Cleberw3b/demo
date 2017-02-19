package com.ipayso.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Version;

/**
 * AbstractModelClass.class -> This class is an model for model classes which implements ModelObject and Serializable for transaction purposes,
 * 							   the annotation @MappedSuperclass say that it can be mapped in the same way as an entity except that the
 *							   mappings will apply only to its subclasses since no table exists for the mapped superclass itself which makes this
 *							   class perfect for models inherit. 
 * @author Cleber Oliveira
 * @version 1.0
 * @see ModelObject
 * @see Serializable
 * @see @MappedSuperclass
 */
@MappedSuperclass
public class AbstractModelClass implements ModelObject, Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * The column id is auto inclemented by @GeneratedValue and signed as id by @Id
	 * @see @GeneratedValue
	 * @see @Id
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Integer id;
	
	/**
	 * The version is used to ensure integrity on this entity
	 * @see @Version
	 */
	@Version
	private Integer version;

	private Date dateCreated;
	private Date lastUpdated;

	@Override
	public Integer getId() {
		return this.id;
	}

	@Override
	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	/**
	 * @return the creation Date of the instance as Entity which will extend this class
	 */
	public Date getDateCreated() {
		return dateCreated;
	}
	
	/**
	 * @return the last update as Date on the instance as Entity which will extend this class
	 */
	public Date getLastUpdated() {
		return lastUpdated;
	}
	
	/**
	 * Before an update or creation of instance as Entity this method is called to update the field lastUpdated or create a creation date
	 */
	@PreUpdate
	@PrePersist
	public void updateTimeStamps() {
		lastUpdated = new Date();
		if (dateCreated==null) {
			dateCreated = new Date();
		}
	}
}
