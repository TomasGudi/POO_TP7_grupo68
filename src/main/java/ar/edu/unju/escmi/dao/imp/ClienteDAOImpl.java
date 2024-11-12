package ar.edu.unju.escmi.dao.imp;

import java.util.List;
import javax.persistence.EntityManager;
import ar.edu.unju.escmi.config.EmfSingleton;
import ar.edu.unju.escmi.dao.IClienteDao;
import ar.edu.unju.escmi.entities.Cliente;

public class ClienteDAOImpl implements IClienteDao {
    
    private static EntityManager manager = EmfSingleton.getInstance().getEmf().createEntityManager();

    @Override
    public void guardar(Cliente cliente) {
        try {
            manager.getTransaction().begin();
            manager.persist(cliente);
            manager.getTransaction().commit();
        } catch (Exception e) {
            manager.getTransaction().rollback();
            throw new RuntimeException("Error al guardar el cliente", e);
        }
    }

    @Override
    public Cliente buscarPorId(Long id) {
        Cliente cliente = null;
        try {
            cliente = manager.find(Cliente.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cliente; // NO cerrar el EntityManager aquí
    }

    @Override
    public void actualizar(Cliente cliente) {
        try {
            manager.getTransaction().begin();
            manager.merge(cliente);
            manager.getTransaction().commit();
        } catch (Exception e) {
            manager.getTransaction().rollback();
            throw new RuntimeException("Error al actualizar el cliente", e);
        }
    }

    @Override
    public void eliminar(Long id) {
        Cliente cliente = buscarPorId(id);
        try {
            manager.getTransaction().begin();
            cliente.setEstado(false);
            manager.merge(cliente);
            manager.getTransaction().commit();
        } catch (Exception e) {
            manager.getTransaction().rollback();
            throw new RuntimeException("Error al eliminar el cliente", e);
        }
    }

    @Override
    public List<Cliente> obtenerTodos() {
        return manager.createQuery("FROM Cliente WHERE estado = true", Cliente.class).getResultList();
    }
}

