package ar.edu.unju.escmi.dao;

import ar.edu.unju.escmi.entities.Producto;
import java.util.List;

public interface IProductoDao {
	
    void guardar(Producto producto);
    Producto buscarPorId(Long id);
    void actualizar(Producto producto);
    void eliminar(Long id);
    List<Producto> obtenerTodos();
    
}
