package ar.edu.unju.escmi.test;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;

import ar.edu.unju.escmi.dao.IClienteDao;
import ar.edu.unju.escmi.dao.ILibroDao;
import ar.edu.unju.escmi.dao.imp.ClineteDaoImp;
import ar.edu.unju.escmi.dao.imp.LibroDaoImp;
import ar.edu.unju.escmi.entities.Cliente;
import ar.edu.unju.escmi.entities.Libro;

class LibroTest {
	ILibroDao libroDao = new LibroDaoImp();
	IClienteDao autorDao = new ClineteDaoImp();

	/*@Test
	void testGuardarLibro() {
		Autor autor = autorDao.obtenerAutorPorId(1L);
		Libro libro = new Libro("22145","Literatura II",LocalDate.of(2008,06,15),autor);
		
		libroDao.guardarLibro(libro);
		assertTrue(libro != null);
	}*/
	
	/*@Test
	void testModificarLibro() {
		Libro libro = libroDao.obtenerLibroPorId(1L);
		//modificar libro con id 1
		//libro.setTitulo("Programación orientada a objetos");
		
		libroDao.modificarLibro(libro);
		assertTrue(libro != null);
	}*/
	
	/*@Test
	void testObtenerTodosLosLibros() {
		List<Libro> libros = libroDao.obtenerLibros();
		//mostrar todos los libros
		libros.stream().forEach(System.out::println);
		assertTrue(libros != null);
	}*/
	
	/*@Test
	void testBorrarUnLibro() {
		Libro libro = libroDao.obtenerLibroPorId(3L);
		//eliminar libro con id 3
		libroDao.borrarLibro(libro);
		assertTrue(libro != null);
	}*/
	
	

}
