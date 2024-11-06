package ar.edu.unju.escmi.entities;
import javax.persistence.*;
import java.time.LocalDate;

	@Entity
	@Table(name = "facturas")
	public class Factura {

	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY) // ID autoincremental
	    private Long id;

	    @Column(name = "fecha", nullable = false)
	    private LocalDate fecha;

	    @ManyToOne
	    @JoinColumn(name = "cliente_id", nullable = false) // Define la relación con Cliente
	    private Cliente cliente;

	    @Column(name = "domicilio", nullable = false, length = 100)
	    private String domicilio;

	    @Column(name = "total", nullable = false)
	    private double total;

	    @Column(name = "estado", nullable = false)
	    private boolean estado;

	    // Constructor sin argumentos
	    public Factura() {
	        this.fecha = LocalDate.now(); // Asigna la fecha actual por defecto
	    }

	    // Constructor con argumentos
	    public Factura(Cliente cliente, String domicilio, double total, boolean estado) {
	        this.fecha = LocalDate.now();
	        this.cliente = cliente;
	        this.domicilio = domicilio;
	        this.total = total;
	        this.estado = estado;
	    }

	    // Getters y setters
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
	}


