package objects;

public class viaje   implements Comparable<viaje> {
	public int inicio,finalizancion,total;
	public viaje(int inicio,int finalizacion,int total) {
		this.inicio=inicio;
		this.finalizancion = finalizacion;
		this.total = total;
	}
	
	
	@Override
	public int compareTo(viaje s) {
		if (finalizancion < s.finalizancion) {
			return 1;
		} else if (finalizancion == s.finalizancion) {
			return 0;
		} else {
			return -1;
		}
	}
}