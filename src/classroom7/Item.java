package classroom7;

public class Item {
	private String value;
	public Item(String value) {
		this.value = value;
	}
	public String getValue(){
		return value;
	}
	public void setValue(String value){
		this.value = value;
	}
	
	@Override 
	public boolean equals(Object o) {
		if(this == o) {
			return true;
		}
		if(o == null || getClass() != o.getClass()) {
			return false;
		}
		Item item = (Item)o;
		return (value == null? item == null:value.equals(item.value));
	}
	
	@Override
	public int hashCode() {
		return value != null?value.hashCode():0;
	}
}
