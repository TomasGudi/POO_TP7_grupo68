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
        	if(manager.getTransaction() != null)
        		manager.getTransaction().rollback();
            System.out.println("No se pudo guardar el cliente");
        } finally {
            manager.close();
        }
	}

	@Override
	public Cliente buscarPorId(Long id) {
		Cliente cliente = null;
        try {
            cliente = manager.find(Cliente.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            manager.close();
        }
        return cliente;
	}

	@Override
	public void actualizar(Cliente cliente) {
        try {
            manager.getTransaction().begin();
            manager.merge(cliente);
            manager.getTransaction().commit();
        } catch (Exception e) {
            manager.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            manager.close();
        }
		
	}

	@Override
	public void eliminar(Long id) {
		try {
            manager.getTransaction().begin();
            Cliente cliente = manager.find(Cliente.class, id);
            if (cliente != null) {
                manager.remove(cliente);
            }
            manager.getTransaction().commit();
        } catch (Exception e) {
            manager.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            manager.close();
        }
		
	}

	@Override
	public List<Cliente> obtenerTodos() {
		List<Cliente> clientes = null;
        try {
            clientes = manager.createQuery("FROM Cliente", Cliente.class).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            manager.close();
        }
        return clientes;
	}

}
