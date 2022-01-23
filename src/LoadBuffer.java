
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
public class LoadBuffer {
	private HashMap<String, Integer> buffer;
	private int[] latencies;
	private int latency;
	public LoadBuffer() {
		buffer = new HashMap<String,Integer>();
		initializeBuffer();
		latencies=new int[5];
		try {
			initializeLatency();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private void initializeBuffer() {
		buffer.put("L1", null);
		buffer.put("L2", null);
		buffer.put("L3", null);
		buffer.put("L4", null);
		buffer.put("L5", null);
		latencies=new int[5];


	}
	private void initializeLatency() throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader("src/Files/Latencies.txt"));
		String line = reader.readLine();
		while (line != null) {
			String op = line.split(" ")[0];
			if (op.equals("LD")) {
				int lat = Integer.parseInt(line.split(" ")[1]);
				this.latency = lat;
				for (int i = 0; i < 5; i++) {
					latencies[i] = lat;
				}

			}
			line = reader.readLine();
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

	public HashMap<String,Float> execute(MemoryUnit memory) {
		HashMap<String,Float> returned = new HashMap<String,Float>();
		int address=-1;
		for(int i=0;i<5;i++) {
			if(latencies[i]==-1) {
				address=buffer.get("L"+(i+1));
				buffer.put("L"+(i+1), null);
				latencies[i]=latency;
				Float data = memory.load(address);
				returned.put("L"+(i+1),data);
				break;
			}
		}
		return returned;
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
				busy[i-1]=1;
			}
		}
		return busy;
	}
	public String toString(){
		String s = "Load Buffer : \n";
		for(int i=1;i<=5;i++){
			s+= "[ L"+ i + ", " + buffer.get("L"+i) + "] \n";
		}
		return s;
	}

	public static void main(String[] args) {
		LoadBuffer b = new LoadBuffer();
		MemoryUnit m = new MemoryUnit();
		System.out.println(b.add(8));
		System.out.println(b.toString());
		b.cycle();
		b.cycle();
		b.cycle();
		System.out.println(b.execute(m));
		System.out.println(m.toString());

	}





	}




