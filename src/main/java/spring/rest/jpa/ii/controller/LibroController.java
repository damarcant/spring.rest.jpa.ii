package spring.rest.jpa.ii.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import spring.rest.jpa.ii.bean.Libro;
import spring.rest.jpa.ii.facade.LibroFacade;

@RestController("libroController.v1")
@RequestMapping("v1/libros")
public class LibroController {
 
	@Autowired
	private LibroFacade libroFacade;
	 
	@RequestMapping(method = RequestMethod.GET)	 
	public ResponseEntity<Page<Libro>> findAll(Pageable pageable) {		
		Page<Libro> entityPage = libroFacade.findAll(pageable);
		ResponseEntity<Page<Libro>> responseEntity = new ResponseEntity<Page<Libro>>(entityPage, HttpStatus.OK);
		return responseEntity;
	} 
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)	 
	public Libro findOne(@PathVariable(value = "id") Integer id) {	
		return libroFacade.findOne(id);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = "application/json")
	public Libro update(@RequestBody Libro libro, @PathVariable(value = "id") Integer id) {
		libro.setId(id);
		return libroFacade.update(libro);	 
	}
	
	@RequestMapping(method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<Libro> save(@RequestBody Libro libro)   {
			 
		libro = libroFacade.save(libro);
			
		HttpHeaders headers = new HttpHeaders();
		URI locationUri = ServletUriComponentsBuilder.fromCurrentRequest().path("/").path(String.valueOf(libro.getId())).build().toUri();
		headers.setLocation(locationUri);	 
		ResponseEntity<Libro> responseEntity = new ResponseEntity<Libro>(libro, headers, HttpStatus.CREATED);
	
		return responseEntity;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable(value = "id") Integer id) {
		libroFacade.delete(id);		 
	}
	 
	//----------------------------------------- SEARCH ---------------------------------------------------
	
	@RequestMapping(value = "/q", method = RequestMethod.GET)
	public List<Libro> findByTitulo(@RequestParam(required = false, value = "titulo") String titulo, 
			@RequestParam(required = false, value = "publicadoDesde") Integer publicadoDesde,
			@RequestParam(required = false, value = "publicadoHasta") Integer publicadoHasta,
			@RequestParam(required = false, value = "insercionDesde") @DateTimeFormat(pattern = "yyyy-MM-dd") Date insercionDesde,
			@RequestParam(required = false, value = "insercionHasta") @DateTimeFormat(pattern = "yyyy-MM-dd") Date insercionHasta) {

		List<Libro> listaLibros = new ArrayList<Libro>();
	 
		if (titulo != null) {
			listaLibros = libroFacade.findByTituloIgnoreCase(titulo);
		} else if (publicadoDesde != null && publicadoHasta != null && insercionDesde != null && insercionHasta != null) {
			listaLibros = libroFacade.findByAnioPublicacionBetweenAndTimestampInsercionBetween(publicadoDesde, publicadoHasta, insercionDesde, insercionHasta);
		} else if (publicadoDesde != null && publicadoHasta != null) {
			listaLibros = libroFacade.findByAnioPublicacionBetween(publicadoDesde, publicadoHasta);
		} else if (insercionDesde != null && insercionHasta != null) {
			listaLibros = libroFacade.findByTimestampInsercionBetween(insercionDesde, insercionHasta);
		}
	  
		return listaLibros; 
	}
 
}
