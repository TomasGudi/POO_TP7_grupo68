package ar.edu.unju.escmi.test;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import ar.edu.unju.escmi.dao.ILibroDao;
import ar.edu.unju.escmi.dao.IPrestamoDao;
import ar.edu.unju.escmi.dao.ISocioDao;
import ar.edu.unju.escmi.dao.imp.LibroDaoImp;
import ar.edu.unju.escmi.dao.imp.PrestamoDaoImp;
import ar.edu.unju.escmi.dao.imp.SocioDaoImp;
import ar.edu.unju.escmi.entities.Libro;
import ar.edu.unju.escmi.entities.Prestamo;
import ar.edu.unju.escmi.entities.Socio;

class PrestamoTest {
	IPrestamoDao prestamoDao = new PrestamoDaoImp();
	ILibroDao libroDao = new LibroDaoImp();
	ISocioDao socioDao = new SocioDaoImp();

	@Test
	void testGuardarPrestamo() {
		Libro libro1 = libroDao.obtenerLibroPorId(1L);
		Libro libro2 = libroDao.obtenerLibroPorId(2L);
		List<Libro> libros = new ArrayList<Libro>();
		libros.add(libro1);
		libros.add(libro2);
		Socio socio = socioDao.obtenerSocioPorId(1L);
		Prestamo prestamo = new Prestamo(LocalDate.now(),null,socio,libros);
		prestamoDao.guardarPrestamo(prestamo);
		assertTrue(libros != null);
	}

}
