package spring.rest.jpa.ii.bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Type;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "libros")
public class Libro {
 
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column 
	private String titulo;
	
	//nombre de columna diferente al de la propiedad del bean
	@Column(name = "anio_publicacion") 
	private Integer anioPublicacion;
	
	//de número a boolean
	@Type(type = "org.hibernate.type.NumericBooleanType")
	@Column 
	private boolean ebook;  
	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm", timezone="GMT+2"/*ya que en bd está sin timezone.*/)
	@Temporal(TemporalType.TIMESTAMP) 
	@Column(name = "timestamp_insercion") 
	private Date timestampInsercion;

	//------------------------------- Getters & Setters -------------------------------------

	public Integer getId() {
		return id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Integer getAnioPublicacion() {
		return anioPublicacion;
	}

	public void setAnioPublicacion(Integer anioPublicacion) {
		this.anioPublicacion = anioPublicacion;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public boolean isEbook() {
		return ebook;
	}

	public void setEbook(boolean ebook) {
		this.ebook = ebook;
	}

	public Date getTimestampInsercion() {
		return timestampInsercion;
	}

	public void setTimestampInsercion(Date timestampInsercion) {
		this.timestampInsercion = timestampInsercion;
	}

}
