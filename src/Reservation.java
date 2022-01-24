
public class Reservation {
	
	private int remainingtime;
	private String tag;
	private String op;
	private Float vj;
	private Float vk;
	private String qj;
	private String qk;
	private String a;

	public Reservation(int remainingtime, String op, Float vj, Float vk, String qj, String qk,
			String a) {
		this.remainingtime = remainingtime;
		this.tag = "";
		this.op = op;
		this.vj = vj;
		this.vk = vk;
		this.qj = qj;
		this.qk = qk;
		this.a = a;
	}
	
	public void decTime() {
		this.remainingtime = this.remainingtime - 1; 
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public int getRemainingtime() {
		return remainingtime;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getOp() {
		return op;
	}

	public void setOp(String op) {
		this.op = op;
	}

	public Float getVj() {
		return vj;
	}

	public void setVj(Float vj) {
		this.vj = vj;
	}

	public Float getVk() {
		return vk;
	}

	public void setVk(Float vk) {
		this.vk = vk;
	}

	public String getQj() {
		return qj;
	}

	public void setQj(String qj) {
		this.qj = qj;
	}

	public String getQk() {
		return qk;
	}

	public void setQk(String qk) {
		this.qk = qk;
	}

	public String getA() {
		return a;
	}

	public void setA(String a) {
		this.a = a;
	}
}
