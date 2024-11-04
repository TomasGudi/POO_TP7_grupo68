package ar.edu.unju.escmi.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import ar.edu.unju.escmi.dao.IBibliotecaDao;
import ar.edu.unju.escmi.dao.imp.BibliotecaDaoImp;
import ar.edu.unju.escmi.entities.Biblioteca;

class BibliotecaTest {
	
	IBibliotecaDao bibliotecaDao = new BibliotecaDaoImp();

	/*@Test
	void testGuardaBiblioteca() {
		Biblioteca biblioteca = new Biblioteca("Antonio Paleari","Mina 9 de Octubre 120");
		bibliotecaDao.guardarBiblioteca(biblioteca);
		assertTrue(biblioteca != null);
	}*/
	
	@Test
	void testMostrarDatosBiblioteca() {
		Biblioteca biblioteca = bibliotecaDao.obtenerBibliotecaPorId(1L);
		System.out.println(biblioteca);
		assertTrue(biblioteca != null);
	}

}
