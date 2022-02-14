package object;
//Objet client 
public class client {
	public int id, img_color, img_bw,img_colorTotal, img_bwTotal;
	public String name;
	public int VentanillaIngresada;
	public int PasoIngresado;
	public int PasoSalida;
	
	
	public client(int id, String name, int img_color, int img_bw) {
		this.id = id;
		this.name = name;
		this.img_color = img_color;
		this.img_bw = img_bw;
		this.img_colorTotal = img_color;
		this.img_bwTotal = img_bw;	
	}
}
