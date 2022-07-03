package com.axyya.task1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.axyya.task1.model.ServerEntity;
import com.axyya.task1.repository.ServerRepository;
import com.axyya.task1.service.ServerService;

@RestController
@RequestMapping
@CrossOrigin	
public class ServerController {
	@Autowired
	 ServerService service;
	
	@Autowired
	ServerRepository repo;
	
	@PostMapping("/add-servers")
	  public ResponseEntity<ServerEntity> createServer(@RequestBody ServerEntity server) {
	    try {
	    ServerEntity serverInfo = service.addServer(server);
	      return new ResponseEntity<>(serverInfo, HttpStatus.CREATED);
	    } catch (Exception e) {
	      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	  }

	@GetMapping("/get-all")
	  public ResponseEntity<List<ServerEntity>> getAllServrs() {
	    try {
	      List<ServerEntity>servers= service.getAllServers();
	      if (servers.isEmpty()) {
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	      }
	      return new ResponseEntity<>(servers, HttpStatus.OK);
	    } catch (Exception e) {
	      return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
	    }
	  }
	
	 @GetMapping("/find-server/{id}")
	  public ResponseEntity<ServerEntity> getserverById(@PathVariable("id") String id) {
	    ServerEntity serverInfo = service.findServerById(id);
	    if (serverInfo!=null) {
	      return new ResponseEntity<>(serverInfo, HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	  }
	 
	 @PutMapping(path="/update-server")
		public ResponseEntity<String>ubdateServerDetails(@RequestBody ServerEntity server)
		{     
			try {
				service.updateServer(server);
						return new ResponseEntity<String>("DATA UPDATED SUCESSFULLY",HttpStatus.OK);
						
			} catch (NotFoundException e) {
				return new ResponseEntity<String>(e.getMessage(),HttpStatus.OK);
			}
		
			
		}
	 
	 @DeleteMapping("/delete-server/{id}")
	  public ResponseEntity<String> deleteTServer(@PathVariable("id") String id) throws NotFoundException{
	   
	     boolean res=repo.deleteById(id);
	     if (!res) {
	    	 return new ResponseEntity<String>("no such id found",HttpStatus.INTERNAL_SERVER_ERROR);
		}
	     return new ResponseEntity<String>("Deleted sucessfully",HttpStatus.INTERNAL_SERVER_ERROR);
	  }

	 @GetMapping(path = "/servers/find")
		public ResponseEntity<List<ServerEntity>> findServers(@RequestParam(name = "name") String name) {
			List<ServerEntity> servers = service.findServer(name);
			return servers.size() > 0 ? new ResponseEntity<List<ServerEntity>>(servers, HttpStatus.OK) :
				new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
}
