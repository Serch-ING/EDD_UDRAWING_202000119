package objects;

public class Mensajero implements Comparable<Mensajero>{
	public long DPI; 
	public String nombre,apellido,tipo_liciencia,genero,direccion,telefono;
	public int cantidad_entregas;
	
	public Mensajero(long DPI,String nombre,String apellido,String tipo_liciencia,String genero,String direccion,String telefono) {
		this.DPI= DPI;
		this.nombre= nombre;
		this.apellido= apellido;
		this.tipo_liciencia= tipo_liciencia;
		this.genero= genero;
		this.direccion= direccion;
		this.telefono= telefono;
	
	}
	
	
	public int compareTo(Mensajero s) {
		if (cantidad_entregas < s.cantidad_entregas) {
			return 1;
		} else if (cantidad_entregas == s.cantidad_entregas) {
			return 0;
		} else {
			return -1;
		}
	}
}
