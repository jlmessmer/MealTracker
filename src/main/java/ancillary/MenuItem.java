package ancillary;

public class MenuItem {
	private String title;
	boolean vegan;
	boolean vegetarian;
	boolean glutenFree;
	
	public MenuItem(String s){
		title = s;
		vegan = false;
		vegetarian = false;
		glutenFree = false;
	}
	
	public MenuItem(String s, boolean v, boolean vg, boolean gf){
		title = s;
		vegan = v;
		vegetarian = vg;
		glutenFree = gf;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public boolean isVegan() {
		return vegan;
	}

	public void setVegan(boolean vegan) {
		this.vegan = vegan;
	}

	public boolean isVegetarian() {
		return vegetarian;
	}

	public void setVegetarian(boolean vegetarian) {
		this.vegetarian = vegetarian;
	}

	public boolean isGlutenFree() {
		return glutenFree;
	}

	public void setGlutenFree(boolean glutenFree) {
		this.glutenFree = glutenFree;
	}
	
	@Override
	public String toString(){
		return title;
	}
	
	@Override
	public boolean equals(Object o){
		if (o == null) return false;
		if (o == this) return true;
		if (!(o instanceof MenuItem)) return false;
		MenuItem cmp = (MenuItem)o;
		return title.equals(cmp.getTitle());
	}
	
}
