
import java.util.*;
public class LoadBuffer {
	private HashMap<String, Integer> buffer;
	private int[] latencies;
	private int latency;
	public LoadBuffer(int latency) {
		buffer = new HashMap<String,Integer>();
		initializeBuffer();
		latencies=new int[5];
		initializeLatency(latency);
		this.latency=latency;
	}
	private void initializeBuffer() {
		buffer.put("L1", null);
		buffer.put("L2", null);
		buffer.put("L3", null);
		buffer.put("L4", null);
		buffer.put("L5", null);
		latencies=new int[5];
		
		
	}
	private void initializeLatency(int latency) {
		for(int i=0;i<5;i++) {
			latencies[i]=latency;
		}
	}
	public boolean isAvailable() {
		int count=0;
		for(int i=1;i<=5;i++) {
			if(buffer.get("L"+i)!=null) {
				count++;
			}
		}
		if(count==5)return false;
		return true;
	}
	public String add(int address) {
		for(int i=1;i<=5;i++) {
			if(buffer.get("L"+i)==null) {
				buffer.put("L"+i, address);
				return "L"+i;
			}
		}
		return null;
	}
	
	public Integer execute() {
		int address=-1;
		for(int i=0;i<5;i++) {
			if(latencies[i]==-1) {
				address=buffer.get("L"+(i+1));
				buffer.put("L"+(i+1), null);
				latencies[i]=latency;
				break;
			}
		}
		if(address==-1)return null;
		return address;
	}
	public void cycle() {
		for(int i=0;i<5;i++) {
			if(buffer.get("L"+(i+1))!=null) latencies[i]--;
		}
	}
	public int[]busy(){
		int[] busy=new int[5];
		for(int i=1;i<=5;i++) {
			if(buffer.get("L"+i)==null) {
				busy[i-1]=0;
			}
			else {
				busy[i-1]=5;
			}
		}
		return busy;
	}
	public static void main(String[] args) {
		LoadBuffer b= new LoadBuffer(2);
		if(b.isAvailable()) {
			System.out.println(b.add(500));
		}
		b.cycle();
		b.add(400);
		b.cycle();
		System.out.println(b.execute());
		b.cycle();
		System.out.println(b.execute());
		System.out.println(b.execute());


	}



}
