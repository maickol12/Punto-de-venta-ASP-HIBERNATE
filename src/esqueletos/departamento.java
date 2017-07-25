package esqueletos;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

public class departamento {
	private int iddepartamento;
	private sucursal suc;
	private String nombre_dep;
	
	@Column(name="f_alta",columnDefinition="DATETIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date f_alta;
	
	@Column(name="f_baja",columnDefinition="DATETIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date f_baja;
	
	@Column(name="f_update",columnDefinition="DATETIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date f_update;

	public int getIddepartamento() {
		return iddepartamento;
	}

	public void setIddepartamento(int iddepartamento) {
		this.iddepartamento = iddepartamento;
	}

	public sucursal getSuc() {
		return suc;
	}

	public void setSuc(sucursal suc) {
		this.suc = suc;
	}

	public String getNombre_dep() {
		return nombre_dep;
	}

	public void setNombre_dep(String nombre_dep) {
		this.nombre_dep = nombre_dep;
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

	public Date getF_update() {
		return f_update;
	}

	public void setF_update(Date f_update) {
		this.f_update = f_update;
	}
	
	
}
