package com.ipayso.services;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

/**
 * CRUDService.class -> This interface assign basics methods which any CRUDService should implement
 * @author Cleber Oliveira
 * @version 1.0
 * @see CrudRepository
 */
public interface CRUDService<T> {
	
	/**
	 * List all of the object type
	 * @return List of objects
	 */
	List<?> listAll();
	
	/**
	 * Get an Object by its ID
	 * @return Object
	 */
    T getById(Integer id);
    
    /**
	 * Save or update an Object and return the same Object after the transaction on DB
	 * @return Object
	 */
    T saveOrUpdate(T domainObject);
    
    /**
	 * Delete an Object by  its id
	 */
    void delete(Integer id);
}
