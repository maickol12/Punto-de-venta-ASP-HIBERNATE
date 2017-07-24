package esqueletos;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

public class Usuario {
	private int idusuario,idtipo_usuario;
	private String username,password,email,nombre,direccion,telefono;
	private int is_active;
	private tipo_usuario t_u;
	
	@Column(name = "f_alta",columnDefinition="DATETIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date f_alta;
	
	@Column(name = "f_baja",columnDefinition="DATETIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date f_baja;
	
	@Column(name = "f_update",columnDefinition="DATETIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date f_update;
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public int getIs_active() {
		return is_active;
	}

	public void setIs_active(int is_active) {
		this.is_active = is_active;
	}

	public int getIdusuario() {
		return idusuario;
	}

	public void setIdusuario(int idusuario) {
		this.idusuario = idusuario;
	}

	public int getIdtipo_usuario() {
		return idtipo_usuario;
	}

	public void setIdtipo_usuario(int idtipo_usuario) {
		this.idtipo_usuario = idtipo_usuario;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public tipo_usuario getT_u() {
		return t_u;
	}

	public void setT_u(tipo_usuario t_u) {
		this.t_u = t_u;
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
