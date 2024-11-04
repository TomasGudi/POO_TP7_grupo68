package ar.edu.unju.escmi.test;

import static org.junit.jupiter.api.Assertions.*;

import ar.edu.unju.escmi.dao.IClienteDao;
import ar.edu.unju.escmi.dao.imp.ClineteDaoImp;
import org.junit.jupiter.api.Test;
import ar.edu.unju.escmi.entities.Cliente;

class AutorTest {
	IClienteDao autorDao = new ClineteDaoImp();

	@Test
	void testGuardarAutor() {
		Cliente autor = new Cliente("María del Valle Martinez","Argentina");
		autorDao.guardarAutor(autor);
		assertTrue(autor != null);
	}

}
