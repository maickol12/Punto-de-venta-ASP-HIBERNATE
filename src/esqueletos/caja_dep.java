package esqueletos;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

public class caja_dep {
	private int idcja_dep,iddep;
	
	private caja caj;
	
	@Column(name = "f_alta",columnDefinition="DATETIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date f_alta;
	
	@Column(name = "f_baja",columnDefinition="DATETIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date f_baja;
	
	@Column(name = "f_update",columnDefinition="DATETIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date f_update;
	

}
