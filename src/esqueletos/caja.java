package esqueletos;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

public class caja {
	private int idcaja,is_open,is_active;
	private String codigo_caja;
	
	@Column(name = "f_alta",columnDefinition="DATETIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date f_alta;

	@Column(name = "f_baja",columnDefinition="DATETIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date f_baja;

	@Column(name = "f_update",columnDefinition="DATETIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date f_update;
	
	public int getIdcaja() {
		return idcaja;
	}

	public void setIdcaja(int id_caja) {
		this.idcaja = id_caja;
	}

	public int getIs_open() {
		return is_open;
	}

	public void setIs_open(int is_open) {
		this.is_open = is_open;
	}

	public int getIs_active() {
		return is_active;
	}

	public void setIs_active(int is_active) {
		this.is_active = is_active;
	}

	public String getCodigo_caja() {
		return codigo_caja;
	}

	public void setCodigo_caja(String codigo_caja) {
		this.codigo_caja = codigo_caja;
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
