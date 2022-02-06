package objects;

public class client {
	
	int id,img_color, img_bw;
	String name;

	public client(int id,String name,int img_color,int img_bw){
		this.id = id;
		this.name = name;
		this.img_color = img_color;
		this.img_bw  = img_bw;
	}
	
	public void creation() {
		System.out.println(" creacion");
		
	}
}
