package ancillary;

import java.util.ArrayList;

public class Menu {

	ArrayList<MenuItem> items = new ArrayList<>();
	
	public Menu(){
		items = new ArrayList<>();
	}
	
	public boolean add(MenuItem m){
		if(!contains(m)){
			items.add(m);
			return true;
		}
		return false;
	}
	
	public boolean contains(MenuItem m){
		return items.contains(m);
	}
	
	public void print(){
		for(MenuItem item : items){
			System.out.println(item);
		}
	}
	
	@Override
	public String toString(){
		String out = "";
		for(MenuItem item : items){
			out += item + "\n";
		}
		return out;
	}

}
