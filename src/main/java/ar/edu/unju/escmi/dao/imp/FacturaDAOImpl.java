package ar.edu.unju.escmi.dao.imp;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import org.hibernate.Hibernate;
import ar.edu.unju.escmi.config.EmfSingleton;
import ar.edu.unju.escmi.dao.IFacturaDao;
import ar.edu.unju.escmi.entities.Factura;

public class FacturaDAOImpl implements IFacturaDao {

    // Obtenemos el EntityManager del Singleton
    private static EntityManager manager = EmfSingleton.getInstance().getEmf().createEntityManager();

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
            System.err.println("Error al guardar la factura: " + e.getMessage());
        }
    }

    @Override
    public Factura buscarPorId(Long id) {
        Factura factura = null;
        try {
            factura = manager.find(Factura.class, id);
            // Verificar si la factura existe y está activa
            if (factura != null && factura.isEstado()) {
                // Inicializar la colección de detalles para evitar LazyInitializationException
                Hibernate.initialize(factura.getDetalles());
            } else {
                System.out.println("Factura con ID " + id + " no encontrada o está inactiva.");
                factura = null;
            }
        } catch (PersistenceException e) {
            System.err.println("Error al buscar la factura con ID " + id + ": " + e.getMessage());
        }
        return factura;
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
            System.err.println("Error al actualizar la factura: " + e.getMessage());
        }
    }

    @Override
    public void eliminar(Long id) {
        try {
            manager.getTransaction().begin();
            Factura factura = manager.find(Factura.class, id);
            if (factura != null && factura.isEstado()) {
                // Eliminación lógica
                factura.setEstado(false);
                manager.merge(factura);
                System.out.println("Factura con ID " + id + " eliminada lógicamente.");
            } else {
                System.out.println("Factura con ID " + id + " no encontrada o ya está eliminada.");
            }
            manager.getTransaction().commit();
        } catch (Exception e) {
            if (manager.getTransaction().isActive()) {
                manager.getTransaction().rollback();
            }
            System.err.println("Error al eliminar la factura: " + e.getMessage());
        }
    }

    @Override
    public List<Factura> obtenerTodos() {
        List<Factura> facturas = null;
        try {
            // Obtener solo facturas activas
            facturas = manager.createQuery("FROM Factura f WHERE f.estado = true", Factura.class).getResultList();
        } catch (Exception e) {
            System.err.println("Error al obtener todas las facturas: " + e.getMessage());
        }
        return facturas;
    }
}
