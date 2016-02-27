CREATE TABLE libros (
  id INTEGER IDENTITY PRIMARY KEY,
  titulo VARCHAR(50),
  anio_publicacion INTEGER,
  ebook SMALLINT,
  timestamp_insercion TIMESTAMP DEFAULT NOW()
);