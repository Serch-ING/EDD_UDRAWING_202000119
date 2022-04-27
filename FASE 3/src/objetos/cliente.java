package objetos;

public class cliente {
	public long DPI; 
	public String nombre,usuario,correo,password,direccion;
	public int telefono,id_municipio;
	
	public cliente(long DPI,String nombre,String usuario,String correo,String password,String direccion,int telefono,int id_municipio) {
		this.DPI= DPI;
		this.nombre= nombre;
		this.usuario= usuario;
		this.correo= correo;
		this.password= password;
		this.direccion= direccion;
		this.telefono= telefono;
		this.id_municipio= id_municipio;		
	}
}
