package ar.edu.unju.escmi.dao.imp;

import ar.edu.unju.escmi.dao.IDetalleFacturaDao;
import ar.edu.unju.escmi.entities.DetalleFactura;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class DetalleFacturaDaoImpl implements IDetalleFacturaDao {

    private EntityManager entityManager = Persistence.createEntityManagerFactory("miUnidadPersistencia").createEntityManager();

    @Override
    public void guardar(DetalleFactura detalleFactura) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(detalleFactura);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            throw e;
        }
    }

    @Override
    public DetalleFactura buscarPorId(Long id) {
        return entityManager.find(DetalleFactura.class, id);
    }

    @Override
    public void actualizar(DetalleFactura detalleFactura) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.merge(detalleFactura);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            throw e;
        }
    }

    @Override
    public void eliminar(Long id) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            DetalleFactura detalleFactura = buscarPorId(id);
            if (detalleFactura != null) {
                detalleFactura.setCantidad(0); //representa la eliminacion logica al establecer cantidad en 0
                entityManager.merge(detalleFactura);
            }
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            throw e;
        }
    }

    @Override
    public List<DetalleFactura> obtenerTodos() {
        return entityManager.createQuery("SELECT d FROM DetalleFactura d", DetalleFactura.class).getResultList();
    }
}
