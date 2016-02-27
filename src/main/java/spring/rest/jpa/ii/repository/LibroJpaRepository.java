package spring.rest.jpa.ii.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import spring.rest.jpa.ii.bean.Libro;


public interface LibroJpaRepository extends PagingAndSortingRepository<Libro, Integer> {
	
	/**
	 *  Extiende de CrudRepository y disponde de dos m�todos adicionales
	 *  
	 *  Iterable<T> findAll(Sort sort);
     *
  	 *	Page<T> findAll(Pageable pageable);
	 * 
	 *  Se ha modificado en el dispacher-servlet.xml la etiqueta 
	 *  mvc:annotation-driven para habilitar la paginaci�n de resultados
	 *  
	 *  GET para paginaci�n: libros?page=0&size=1
	 *  
	 *  GET para ordenaci�n: ?sort=titulo,asc
	 * 
	 */
	
	List<Libro> findByTituloIgnoreCase(String titulo);

	List<Libro> findByAnioPublicacionBetween(Integer from, Integer to);

	List<Libro> findByTimestampInsercionBetween(Date insercionDesde, Date insercionHasta);

	List<Libro> findByAnioPublicacionBetweenAndTimestampInsercionBetween(Integer publicadoDesde, Integer publicadoHasta, Date insercionDesde, Date insercionHasta);
	  
}
