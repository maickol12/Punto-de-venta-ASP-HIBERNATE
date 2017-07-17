package esqueletos;

public class Usuario {
	private int idusuario,tipo_usuario;
	private String username;
	
	public Usuario(int idUsuario,int tipo_usuario,String username){
		setIdusuario(idUsuario);
		setTipo_usuario(tipo_usuario);
		setUsername(username);
	}

	public int getIdusuario() {
		return idusuario;
	}

	public void setIdusuario(int idusuario) {
		this.idusuario = idusuario;
	}

	public int getTipo_usuario() {
		return tipo_usuario;
	}

	public void setTipo_usuario(int tipo_usuario) {
		this.tipo_usuario = tipo_usuario;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
}
