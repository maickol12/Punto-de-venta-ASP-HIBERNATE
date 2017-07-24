package esqueletos;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

public class venta {
	private int idventa,is_active;
	private String n_ticket,total,moneda,comentario;
	private double subtotal,descuento,iva,o_retenciones,monto_recibido,monto_cambio,t_cambio;
	
	
	@Column(name = "f_alta",columnDefinition="DATETIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date f_alta;
	
	@Column(name = "f_baja",columnDefinition="DATETIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date f_baja;
	
	private cliente cli;
	
	

	public int getIdventa() {
		return idventa;
	}

	public void setIdventa(int idventa) {
		this.idventa = idventa;
	}

	public int getIs_active() {
		return is_active;
	}

	public void setIs_active(int is_active) {
		this.is_active = is_active;
	}

	public String getN_ticket() {
		return n_ticket;
	}

	public void setN_ticket(String n_ticket) {
		this.n_ticket = n_ticket;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public double getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(double subtotal) {
		this.subtotal = subtotal;
	}

	public double getDescuento() {
		return descuento;
	}

	public void setDescuento(double descuento) {
		this.descuento = descuento;
	}

	public double getIva() {
		return iva;
	}

	public void setIva(double iva) {
		this.iva = iva;
	}

	public double getO_retenciones() {
		return o_retenciones;
	}

	public void setO_retenciones(double o_retenciones) {
		this.o_retenciones = o_retenciones;
	}

	public double getMonto_recibido() {
		return monto_recibido;
	}

	public void setMonto_recibido(double monto_recibido) {
		this.monto_recibido = monto_recibido;
	}

	public double getMonto_cambio() {
		return monto_cambio;
	}

	public void setMonto_cambio(double monto_cambio) {
		this.monto_cambio = monto_cambio;
	}

	public String getMoneda() {
		return moneda;
	}

	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}

	public double getT_cambio() {
		return t_cambio;
	}

	public void setT_cambio(double t_cambio) {
		this.t_cambio = t_cambio;
	}

	

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public Date getF_alta() {
		return f_alta;
	}

	public void setF_alta(Date f_alta) {
		this.f_alta = f_alta;
	}

	public Date getF_baja() {
		return f_baja;
	}

	public void setF_baja(Date f_baja) {
		this.f_baja = f_baja;
	}

	public cliente getCli() {
		return cli;
	}

	public void setCli(cliente cli) {
		this.cli = cli;
	}

	

	
	
	
	
}
