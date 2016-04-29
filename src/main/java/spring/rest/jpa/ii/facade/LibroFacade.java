package spring.rest.jpa.ii.facade;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import spring.rest.jpa.ii.bean.Libro;
import spring.rest.jpa.ii.exception.ResourceNotFoundException;
import spring.rest.jpa.ii.repository.LibroJpaRepository;

@Service
public class LibroFacade {
	
	@Autowired
	private LibroJpaRepository libroJpaRepository;

	//devuelve a una pag. de libros
	public Page<Libro> findAll(Pageable pageable) { 
		return libroJpaRepository.findAll(pageable);
	} 

	public Libro findOne(Integer id) {
		Libro libro = libroJpaRepository.findOne(id);
		if (libro == null) {
			throw new ResourceNotFoundException(id);
		}
		return libro;
	}

	public Libro update(Libro libro) { 
		if(!libroJpaRepository.exists(libro.getId())){
			throw new ResourceNotFoundException(libro.getId());
		}
		return libroJpaRepository.save(libro);
	}

	public Libro save(Libro entity) {
		entity.setId(null); 
		return libroJpaRepository.save(entity); 
	}

	public void delete(Integer id) {
		if(!libroJpaRepository.exists(id)){
			throw new ResourceNotFoundException(id);
		}
		libroJpaRepository.delete(id);		
	}
	
	public List<Libro> findByTituloIgnoreCase(String titulo) {
		return libroJpaRepository.findByTituloIgnoreCase(titulo);
	}

	public List<Libro> findByAnioPublicacionBetween(Integer from, Integer to) {
		return libroJpaRepository.findByAnioPublicacionBetween(from, to);
	}

	public List<Libro> findByTimestampInsercionBetween(Date insercionDesde, Date insercionHasta) {
		return libroJpaRepository.findByTimestampInsercionBetween(insercionDesde, insercionHasta);
	}

	public List<Libro> findByAnioPublicacionBetweenAndTimestampInsercionBetween(Integer publicadoDesde, Integer publicadoHasta, Date insercionDesde, Date insercionHasta) {
		return libroJpaRepository.findByAnioPublicacionBetweenAndTimestampInsercionBetween(publicadoDesde, publicadoHasta, insercionDesde, insercionHasta);
	}
	
}
