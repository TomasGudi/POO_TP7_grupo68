package ar.edu.unju.escmi.dao.imp;

import java.util.List;

import javax.persistence.EntityManager;

import ar.edu.unju.escmi.config.EmfSingleton;
import ar.edu.unju.escmi.dao.IFacturaDao;
import ar.edu.unju.escmi.entities.Factura;

public class FacturaDAOImpl implements IFacturaDao {
	
	private static EntityManager manager = EmfSingleton.getInstance().getEmf().createEntityManager();	
	
    @Override
	public void guardar(Factura factura) {
		try {
            manager.getTransaction().begin();
            manager.persist(factura);
            manager.getTransaction().commit();
        } catch (Exception e) {
        	if(manager.getTransaction() != null)
        		manager.getTransaction().rollback();
            System.out.println("No se pudo guardar la factura ");
        } finally {
            manager.close();
        }
	}
    
    @Override
	public Factura buscarPorId(Long id) {
    	Factura factura = null;
        try {
            factura = manager.find(Factura.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            manager.close();
        }
        return factura;
    }
    public void actualizar(Factura factura) {
    	try {
            manager.getTransaction().begin();
            manager.merge(factura);
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
            Factura factura = manager.find(Factura.class, id);
            if (factura != null) {
                manager.remove(factura);
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
    public List<Factura> obtenerTodos(){
    	List<Factura> facturas = null;
        try {
            facturas = manager.createQuery("FROM Factura", Factura.class).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            manager.close();
        }
        return facturas;
	}
}
