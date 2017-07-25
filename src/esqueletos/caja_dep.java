package esqueletos;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

public class caja_dep {
	private int idcaja_dep,iddep;
	
	private caja caj;
	private departamento dep;
	
	@Column(name = "f_alta",columnDefinition="DATETIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date f_alta;
	
	@Column(name = "f_baja",columnDefinition="DATETIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date f_baja;
	
	

	

	public int getIdcaja_dep() {
		return idcaja_dep;
	}

	public void setIdcaja_dep(int idcaja_dep) {
		this.idcaja_dep = idcaja_dep;
	}

	public int getIddep() {
		return iddep;
	}

	public void setIddep(int iddep) {
		this.iddep = iddep;
	}

	public caja getCaj() {
		return caj;
	}

	public void setCaj(caja caj) {
		this.caj = caj;
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

	public departamento getDep() {
		return dep;
	}

	public void setDep(departamento dep) {
		this.dep = dep;
	}
	
	
	

}
