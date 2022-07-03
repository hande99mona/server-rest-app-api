package com.axyya.task1.service;

import java.util.List;
import java.util.Optional;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import com.axyya.task1.model.ServerEntity;
@Service
public interface ServerService {
	 public ServerEntity addServer(ServerEntity server);
	 public ServerEntity findServerById(String id);
	 public List<ServerEntity> getAllServers()throws EmptyResultDataAccessException;
	 public ServerEntity updateServer(ServerEntity server)throws NotFoundException;
	 public List<ServerEntity> findServer(String name);
}
