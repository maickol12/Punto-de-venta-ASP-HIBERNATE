package esqueletos;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

public class cliente {
	private int idcliente,numero,is_active;
	private String razon_social,rfc,calle,ciudad,municipio,estado;
	private String pais,cp,email,telefono;
	
	@Column(name = "f_alta", columnDefinition="DATETIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date f_alta;
	
	@Column(name = "f_baja",columnDefinition="DATETIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date f_baja;
	
}
