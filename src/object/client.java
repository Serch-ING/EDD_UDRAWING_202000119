package object;

public class client {

	public int id;
	public int img_color;
	public int img_bw;
	public String name;

	public client(int id, String name, int img_color, int img_bw) {
		this.id = id;
		this.name = name;
		this.img_color = img_color;
		this.img_bw = img_bw;

	}
}
