package DAO;

import java.io.*;
import java.util.*;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.*;
import javax.xml.transform.*;
import javax.xml.transform.stream.*;
import javax.persistence.*;
import javax.print.attribute.standard.Media;

import org.*;

@Path("/Site")
public class SiteDao {

    EntityManagerFactory factory = Persistence.createEntityManagerFactory("org");
	EntityManager em = null;
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Site findSite(@PathParam("id") int siteId) {
		Site site = null;

		em = factory.createEntityManager();
		em.getTransaction().begin();

		site = em.find(Site.class, siteId);

		em.getTransaction().commit();
		em.close();
		
		return site;  
	}
	
	
	@GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Site> findAllSites() {
		List<Site> sites = new ArrayList<Site>();

		em = factory.createEntityManager();
		em.getTransaction().begin();

		Query query = em.createNamedQuery("Site.findAll");
		sites = query.getResultList();

		em.getTransaction().commit();
		em.close();

		return sites;		
	}
	
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public List<Site> createSite(Site site) {
		List<Site> sites = new ArrayList<Site>();

		em = factory.createEntityManager();
		em.getTransaction().begin();

		em.persist(site);
		Query query = em.createNamedQuery("Site.findAll");
		sites = query.getResultList();

		em.getTransaction().commit();
		em.close();
		return sites;
	}
	
	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Site> removeSite(@PathParam("id") int siteId) {
		List<Site> sites = new ArrayList<Site>();

		Site site = null;

		em = factory.createEntityManager();
		em.getTransaction().begin();

		site = em.find(Site.class, siteId);
		em.remove(site);

		Query query = em.createNamedQuery("Site.findAll");
		sites = query.getResultList();

		em.getTransaction().commit();
		em.close();

		return sites;
	}
	
	@PUT
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public List<Site> updateSite(@PathParam("id") int siteId, Site site) {
		List<Site> sites = new ArrayList<Site>();

		em = factory.createEntityManager();
		em.getTransaction().begin();

		site.setId(siteId);
		em.merge(site);

		Query query = em.createNamedQuery("Site.findAll");
		sites = query.getResultList();

		em.getTransaction().commit();
		em.close();
		return sites;
	}
	
	
}
