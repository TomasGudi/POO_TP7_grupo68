package ar.edu.unju.escmi.dao.imp;

import ar.edu.unju.escmi.config.EmfSingleton;
import ar.edu.unju.escmi.dao.IDetalleFacturaDao;
import ar.edu.unju.escmi.entities.DetalleFactura;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;

public class DetalleFacturaDaoImpl implements IDetalleFacturaDao {

	private static EntityManager manager = EmfSingleton.getInstance().getEmf().createEntityManager();

    @Override
    public void guardar(DetalleFactura detalleFactura) {
        EntityTransaction transaction = manager.getTransaction();
        try {
            transaction.begin();
            manager.persist(detalleFactura);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            throw e;
        }
    }

    @Override
    public DetalleFactura buscarPorId(Long id) {
        return manager.find(DetalleFactura.class, id);
    }

    @Override
    public void actualizar(DetalleFactura detalleFactura) {
        EntityTransaction transaction = manager.getTransaction();
        try {
            transaction.begin();
            manager.merge(detalleFactura);
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
            DetalleFactura detalleFactura = buscarPorId(id);
            if (detalleFactura != null) {
                detalleFactura.setCantidad(0);
                manager.merge(detalleFactura);
            }
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            throw e;
        }
    }

    @Override
    public List<DetalleFactura> obtenerTodos() {
        return manager.createQuery("SELECT d FROM DetalleFactura d", DetalleFactura.class).getResultList();
    }
}
