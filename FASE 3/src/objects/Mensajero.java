package objects;

public class Mensajero {
	public long DPI; 
	public String nombre,apellido,tipo_liciencia,genero,direccion;
	public int telefono;
	
	public Mensajero(long DPI,String nombre,String apellido,String tipo_liciencia,String genero,String direccion,int telefono) {
		this.DPI= DPI;
		this.nombre= nombre;
		this.apellido= apellido;
		this.tipo_liciencia= tipo_liciencia;
		this.genero= genero;
		this.direccion= direccion;
		this.telefono= telefono;
	
	}
}
