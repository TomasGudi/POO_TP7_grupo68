package ar.edu.unju.escmi.dao;

import java.util.List;

import ar.edu.unju.escmi.entities.Factura;

public interface IFacturaDao {
	
	void guardar(Factura factura);
    Factura buscarPorId(Long id);
    void actualizar(Factura factura);
    void eliminar(Long id);
    List<Factura> obtenerTodos();
   
}
