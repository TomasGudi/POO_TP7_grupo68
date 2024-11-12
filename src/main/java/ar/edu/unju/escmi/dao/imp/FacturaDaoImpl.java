package ar.edu.unju.escmi.dao.imp;

import java.util.List;
import javax.persistence.EntityManager;
import ar.edu.unju.escmi.config.EmfSingleton;
import ar.edu.unju.escmi.dao.IFacturaDao;
import ar.edu.unju.escmi.entities.Factura;

public class FacturaDAOImpl implements IFacturaDao {

    // Obtenemos el EntityManager del Singleton
    private static EntityManager manager = EmfSingleton.getEmf().createEntityManager();

    @Override
    public void guardar(Factura factura) {
        try {
            manager.getTransaction().begin();
            manager.persist(factura);
            manager.getTransaction().commit();
        } catch (Exception e) {
            if (manager.getTransaction().isActive()) {
                manager.getTransaction().rollback();
            }
            throw new RuntimeException("No se pudo guardar la factura", e);
        }
    }

    @Override
    public Factura buscarPorId(Long id) {
        try {
            return manager.find(Factura.class, id);
        } catch (Exception e) {
            throw new RuntimeException("Error al buscar la factura con ID " + id, e);
        }
    }

    @Override
    public void actualizar(Factura factura) {
        try {
            manager.getTransaction().begin();
            manager.merge(factura);
            manager.getTransaction().commit();
        } catch (Exception e) {
            if (manager.getTransaction().isActive()) {
                manager.getTransaction().rollback();
            }
            throw new RuntimeException("Error al actualizar la factura", e);
        }
    }

    @Override
    public void eliminar(Long id) {
        try {
            manager.getTransaction().begin();
            Factura factura = manager.find(Factura.class, id);
            if (factura != null) {
                manager.remove(factura);
            }
            manager.getTransaction().commit();
        } catch (Exception e) {
            if (manager.getTransaction().isActive()) {
                manager.getTransaction().rollback();
            }
            throw new RuntimeException("Error al eliminar la factura", e);
        }
    }

    @Override
    public List<Factura> obtenerTodos() {
        try {
            return manager.createQuery("FROM Factura", Factura.class).getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener todas las facturas", e);
        }
    }
}
