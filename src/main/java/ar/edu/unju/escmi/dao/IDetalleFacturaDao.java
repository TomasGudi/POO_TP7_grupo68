package ar.edu.unju.escmi.dao;

import ar.edu.unju.escmi.entities.DetalleFactura;
import java.util.List;

public interface IDetalleFacturaDao {
	
    void guardar(DetalleFactura detalleFactura);
    DetalleFactura buscarPorId(Long id);
    void actualizar(DetalleFactura detalleFactura);
    void eliminar(Long id);
    List<DetalleFactura> obtenerTodos();
    
}
