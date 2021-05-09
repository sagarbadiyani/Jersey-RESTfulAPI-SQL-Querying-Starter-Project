package com.domain.DemoREST;

import java.sql.SQLException;
import java.util.List;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("aliens")
public class AlienResource {
	
	AlienRepository repo = new AlienRepository();
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Alien> getAliens() throws SQLException {
		System.out.println("Working Get");
		
		return repo.getAliens();
	}
	
	@GET
	@Path("alien/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Alien getAlien(@PathParam("id") int id) throws SQLException {
		System.out.println("Working Get ID");
		
		return repo.getAlien(id);
	}
	
	@POST
	@Path("alien")
	@Consumes(MediaType.APPLICATION_JSON)
	public Alien createAlien(Alien a) throws SQLException {
		System.out.println("Working Post");
		repo.createAlien(a);
		return a;
	}
	
	@PUT
	@Path("alien")
	@Consumes(MediaType.APPLICATION_JSON)
	public Alien updateAlien(Alien a) throws SQLException {
		System.out.println("Working Put" + repo.getAlien(a.getId()));
		if(repo.getAlien(a.getId()).getName() == null)
			repo.createAlien(a);
		repo.updateAlien(a);
		return a;
	}
	
	@DELETE
	@Path("alien/{id}")
	public Alien deleteAlien(@PathParam("id") int id) throws SQLException {
		Alien a = repo.getAlien(id);
		if(repo.getAlien(a.getId()).getName() != null)
			repo.deleteAlien(id);
		return a;
	}
	
}





