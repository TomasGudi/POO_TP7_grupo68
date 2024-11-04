package ar.edu.unju.escmi.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import ar.edu.unju.escmi.dao.IBibliotecaDao;
import ar.edu.unju.escmi.dao.ISocioDao;
import ar.edu.unju.escmi.dao.imp.BibliotecaDaoImp;
import ar.edu.unju.escmi.dao.imp.SocioDaoImp;
import ar.edu.unju.escmi.entities.Biblioteca;
import ar.edu.unju.escmi.entities.Socio;

class SocioTest {
	ISocioDao socioDao = new SocioDaoImp();
	IBibliotecaDao bibliotecaDao = new BibliotecaDaoImp();

	@Test
	void testGuardarSocio() {
		Biblioteca biblioteca = bibliotecaDao.obtenerBibliotecaPorId(1L);
		Socio socio = new Socio(26130888,"Marta","Diaz",biblioteca);
		socioDao.guardarSocio(socio);
		assertTrue(socio != null);
	}

}
