package ar.edu.unju.escmi.entities;
import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

	@Entity
	@Table(name = "facturas")
	public class Factura {

	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    @Column(name = "fecha", nullable = false)
	    private LocalDate fecha;

	    @ManyToOne
	    @JoinColumn(name = "cliente_id", nullable = false) 
	    private Cliente cliente;

	    @Column(name = "domicilio", nullable = false, length = 100)
	    private String domicilio;

	    @Column(name = "total", nullable = false)
	    private double total;

	    @Column(name = "estado", nullable = false)
	    private boolean estado;
	    
	    @OneToMany(mappedBy = "factura", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	    private List<DetalleFactura> detalles = new ArrayList<>();

	    public Factura() {
	        this.fecha = LocalDate.now(); 
	    }

	    public Factura(Cliente cliente, String domicilio, double total, boolean estado) {
	        this.fecha = LocalDate.now();
	        this.cliente = cliente;
	        this.domicilio = domicilio;
	        this.total = total;
	        this.estado = estado;
	    }

	    public Long getId() {
	        return id;
	    }

	    public void setId(Long id) {
	        this.id = id;
	    }

	    public LocalDate getFecha() {
	        return fecha;
	    }

	    public void setFecha(LocalDate fecha) {
	        this.fecha = fecha;
	    }

	    public Cliente getCliente() {
	        return cliente;
	    }

	    public void setCliente(Cliente cliente) {
	        this.cliente = cliente;
	    }

	    public String getDomicilio() {
	        return domicilio;
	    }

	    public void setDomicilio(String domicilio) {
	        this.domicilio = domicilio;
	    }

	    public double getTotal() {
	        return total;
	    }

	    public void setTotal(double total) {
	        this.total = total;
	    }

	    public boolean isEstado() {
	        return estado;
	    }

	    public void setEstado(boolean estado) {
	        this.estado = estado;
	    }
	    public List<DetalleFactura> getDetalles() {
			return detalles;
		}

		public void setDetalles(List<DetalleFactura> detalles) {
			this.detalles = detalles;
		}

		@Override
	    public String toString() {
	        return "Factura:" +
	               "\nID=" + id +
	               "\nFecha=" + fecha +
	               "\nCliente=" + cliente.getNombre() + " " + cliente.getApellido() +
	               "\nTotal=" + total +
	               "\nEstado=" + (estado ? "Activa" : "Eliminada");
	    }

	}