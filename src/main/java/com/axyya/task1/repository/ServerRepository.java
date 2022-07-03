package com.axyya.task1.repository;


import java.util.List;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.axyya.task1.model.ServerEntity;

public interface ServerRepository extends MongoRepository<ServerEntity, Integer> {

	ServerEntity findById(String id);

	boolean deleteById(String id)throws NotFoundException;
	List<ServerEntity> findByserverNameContaining(String serverName);

}
