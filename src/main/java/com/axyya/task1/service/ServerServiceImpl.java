package com.axyya.task1.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import com.axyya.task1.model.ServerEntity;
import com.axyya.task1.repository.ServerRepository;

@Service
public class ServerServiceImpl implements ServerService {

	@Autowired
    ServerRepository serverRepo;     
	
	@Override
	public ServerEntity addServer(ServerEntity server) {
		return serverRepo.save(server);
		
	}

	@Override
	public ServerEntity findServerById(String id) {
		return serverRepo.findById(id);
		
	}

	@Override
	public List<ServerEntity> getAllServers() throws EmptyResultDataAccessException {
		
		return serverRepo.findAll();
	}

	@Override
	public ServerEntity updateServer(ServerEntity server)throws NotFoundException {
	
	      return serverRepo.save(server);
	
	}
	@Override
	public List<ServerEntity> findServer(String name){
		return serverRepo.findByserverNameContaining(name);
	}
	
}
