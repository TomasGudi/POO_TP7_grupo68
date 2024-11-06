package ar.edu.unju.escmi.dao.imp;

import ar.edu.unju.escmi.config.EmfSingleton;
import ar.edu.unju.escmi.dao.IProductoDao;
import ar.edu.unju.escmi.entities.Producto;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;

public class ProductoDaoImpl implements IProductoDao {

	private static EntityManager manager = EmfSingleton.getInstance().getEmf().createEntityManager();
	
    @Override
    public void guardar(Producto producto) {
        EntityTransaction transaction = manager.getTransaction();
        try {
            transaction.begin();
            manager.persist(producto);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            throw e;
        }
    }

    @Override
    public Producto buscarPorId(Long id) {
        return manager.find(Producto.class, id);
    }

    @Override
    public void actualizar(Producto producto) {
        EntityTransaction transaction = manager.getTransaction();
        try {
            transaction.begin();
            manager.merge(producto);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            throw e;
        }
    }

    @Override
    public void eliminar(Long id) {
        EntityTransaction transaction = manager.getTransaction();
        try {
            transaction.begin();
            Producto producto = buscarPorId(id);
            if (producto != null) {
                producto.setEstado(false);
                manager.merge(producto);
            }
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            throw e;
        }
    }

    @Override
    public List<Producto> obtenerTodos() {
        return manager.createQuery("SELECT p FROM Producto p", Producto.class).getResultList();
    }
}
