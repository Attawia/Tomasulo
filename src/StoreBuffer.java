import java.util.*;
public class StoreBuffer {
	private HashMap<String, Integer> buffer;
	private HashMap<String,Object> values;
	int latency;
	private HashMap<String, Integer> latencies;
	private HashMap<String,Integer> cycles;
	
	public StoreBuffer(int latency) {
		buffer=new HashMap<String,Integer>();
		values=new HashMap<String,Object>();
		latencies= new HashMap<String,Integer>();
		cycles= new HashMap<String,Integer>();
		this.latency=latency;
		initializeBuffer();
		initializeValues();
		initializeLatencies();
		initializeCycles();
	}
	
	private void initializeBuffer() {
		buffer.put("S1", null);
		buffer.put("S2", null);
		buffer.put("S3", null);
		buffer.put("S4", null);
		buffer.put("S5", null);
	}
	
	private void initializeValues() {
		values.put("S1", null);
		values.put("S2", null);
		values.put("S3", null);
		values.put("S4", null);
		values.put("S5", null);
	}
	
	private void initializeLatencies() {
		latencies.put("S1", latency);
		latencies.put("S2", latency);
		latencies.put("S3", latency);
		latencies.put("S4", latency);
		latencies.put("S5", latency);
	}

	private void initializeCycles() {
		cycles.put("S1", null);
		cycles.put("S2", null);
		cycles.put("S3", null);
		cycles.put("S4", null);
		cycles.put("S5", null);
	}
	
	public boolean isAvailable() {
		int count=0;
		for(int i=1;i<=5;i++) {
			if(buffer.get("S"+i)!=null) {
				count++;
			}
		}
		if(count==5)return false;
		return true;	
	}
	
	public String add(int address,Object value,int cycle) {
		for(int i=1;i<=5;i++) {
			if(buffer.get("S"+i)==null) {
				buffer.put("S"+i, address);
				cycles.put("S"+i,cycle);
				values.put("S"+i,value);
				return "S"+i;
			}
		}
		return null;
	}

	public Object[] execute(){
		Object[] returned=new Object[2];
		int toExecute=0;
		int cycle=-1;
		for(int i=1;i<=5;i++) {
			if(buffer.get("S"+i)!=null) {
				if((values.get("S"+i)) instanceof Float && latencies.get("S"+i)==-1) {
					if(cycle==-1) {
						cycle=cycles.get("S"+i);
						toExecute=i;
					}
					else if(cycles.get("S"+i)<cycle) {
						cycle=cycles.get("S"+i);
						toExecute=i;
					}
				}
			}
		}
		if(cycle!=-1) {
			returned[0]=buffer.get("S"+toExecute);
			returned[1]=(Float)values.get("S"+toExecute);
			buffer.put("S"+toExecute, null);
			values.put("S"+toExecute, null);
			cycles.put("S"+toExecute, null);
			latencies.put("S"+toExecute, latency);
			return returned;
		}
		return null;
	}
	
	public void cycle(CommonDataBus CDB) {
		for(int i=1;i<=5;i++) {
			if(buffer.get("S"+i)!=null) {
				latencies.put("S"+i,latencies.get("S"+i)-1);
			}
		}
		updateValues(CDB);
	}
	
	public void updateValues(CommonDataBus CDB) {
		for(int i=1;i<=5;i++) {
			if(buffer.get("S"+i)!=null && values.get("S"+i) instanceof String) {
				if(CDB.search((String)values.get("S"+i))!=null) {
					values.put("S"+i, CDB.search((String)values.get("S"+i)));
				}
			}
		}
	}
	
	
}
