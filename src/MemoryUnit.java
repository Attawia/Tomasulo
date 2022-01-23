import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
public class MemoryUnit {
	private Float[] memory;
	
	public MemoryUnit() {
		memory = new Float[20];
		try {
			fetchData();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public Float load(int address) {
		return memory[address];
	}
	
	 private void fetchData() throws IOException {
	        BufferedReader reader = new BufferedReader(new FileReader("src/memory.txt"));
	        String line = reader.readLine();
	        while (line != null) {
	            int address = Integer.parseInt(line.split(":")[0].replaceAll("\\s",""));
	            Float data =Float.parseFloat(line.split(":")[1].replaceAll("\\s",""));
	            memory[address]=data;
	            line = reader.readLine();
	        }
	    }
	
	public void store(int address,Float value) {
		memory[address]=value;
	}
	
	public String toString() {
		String s="";
		int l = 0;
		for(int i=0;i<memory.length;i++) {
			String str = "[" +  i + ": " + memory[i] + "]";
			l = 20 - str.length();
			if((i+1)%2==0)
				str+="\n";
			else{
				while(l>=0) {
					str += " ";
					l--;
				}
			}
			s+=str;
		}
		return s;
	}
	public static void main(String[] args) {
		MemoryUnit m = new MemoryUnit();
		System.out.println(m.load(17));
	}
}
