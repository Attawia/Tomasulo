import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.swing.text.html.parser.Element;

public class ReservationStation {
	
	private Reservation[] reservations;
	private int length;
	private String type;
	private HashMap<String, String> waitingValues;

	public ReservationStation(int length,String type) {
		this.reservations = new Reservation[length];
		this.length = length;
		this.type = type;
		waitingValues = new HashMap<String, String>();
	}
	
	public String addReservation(Reservation r) { // Add new reservation
		for(int i = 0;i<this.length;i++) {
			if(reservations[i] == null) {
				reservations[i] = r;
				int tagNumber = i + 1; //concatenate the number to the type to make a tag (A1,M1)
				reservations[i].setTag(this.type + "" + tagNumber);
				if(reservations[i].getQj() != null) { //There is Qj ??
					this.waitingValues.put(reservations[i].getTag() + "qj", reservations[i].getQj());
				}
				if(reservations[i].getQk() != null) { //There is Qk ??
					this.waitingValues.put(reservations[i].getTag() + "qk", reservations[i].getQk());
				}
				
				return reservations[i].getTag();//return the tag of the reservation
			}
		}
		return "";
	}
	
	public boolean freeSpace(){
		for(int i = 0;i<this.length;i++) {
			if(reservations[i] == null) {
				return true;
			}
		}
		return false;
	}
	
	public HashMap<String, Float> check() {
		HashMap<String, Float> returned = new HashMap<String, Float>();
		
		for(int i = 0;i<this.length;i++) { //looping on all reservations
			Float value = 0.0f;
			
			if(reservations[i] != null) {
				if((reservations[i].getQj() == null) && (reservations[i].getQk() == null)) { // If ready to execute
					reservations[i].decTime();
					
					if(reservations[i].getRemainingtime() == -1) { //time to execute
						switch(reservations[i].getOp()) { //which operation
						case "ADD.D" : value = reservations[i].getVj() + reservations[i].getVk();
						case "SUB.D" : value = reservations[i].getVj() - reservations[i].getVk();
						case "MUL.D" : value = reservations[i].getVj() * reservations[i].getVk();
						case "DIV.D" : value = reservations[i].getVj() / reservations[i].getVk();
						}
						
						//put the result of the execution to be written on the bus
						returned.put(reservations[i].getTag(), value);
						//remove the executed reservation from the station
						reservations[i] = null;
					}
				}
			}
		}
		
		return returned;
	}
	
	public void resolveBus(CommonDataBus bus) {
		String[] toBeRemoved = new String[waitingValues.size() + 1];
		int toBeRemovedIndex = 0;
		Iterator iterator = waitingValues.entrySet().iterator();
		
		while(iterator.hasNext()) {
			Map.Entry element = (Map.Entry)iterator.next();
			if(bus.search(element.getValue().toString()) != null) {
				int index = Integer.parseInt(element.getKey().toString().substring(1, 2)) - 1; //compute index
				String q = element.getKey().toString().substring(2, 4); //compute which q to update
				
				if(q.equals("qj")) { //if qj
					reservations[index].setVj(bus.search(element.getValue().toString()));
					reservations[index].setQj(null);
				}
				else { //if qk
					reservations[index].setVk(bus.search(element.getValue().toString()));
					reservations[index].setQk(null);
				}
				
				//remove the waiting value from the HashMap
				toBeRemoved[toBeRemovedIndex] = element.getKey().toString();
				toBeRemovedIndex++;
			}
		}
		
		for(int i = 0;i < toBeRemoved.length;i++) { //remove all used keys in waiting values list
			waitingValues.remove(toBeRemoved[i]);
		}
	}

	public static void main(String[] args) {
		
	}

	public Reservation[] getReservations() {
		return reservations;
	}
	

}
