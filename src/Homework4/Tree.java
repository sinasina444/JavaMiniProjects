package Homework4;

public abstract class Tree {
    //name and age are not defined by the class as required
	private final String name;
	private final int age;
	
	protected Tree(String name,int age){
		this.name = name;
		this.age = age;
	}
	
	protected enum Type{
		Evergreen,Deciduous;
	}
	public abstract boolean isSeedEnclosed();
	public abstract Type getType();

	public  String getName(){
		return name;
    }
    public int getAge(){
    	return age;
    }

    @Override public boolean equals(Object o){
    	if(this == o){
    		return true;
    	}
    	System.out.println("this is tree");
    	/*if(o==null || getClass() != o.getClass()){
    		return false;
    	}*/
    	Tree that  = (Tree)o;
    		return ((getName()==null?that.getName()==null:getName().equals(that.getName()))
    				&& (getAge()==that.getAge()));
    	}
    
    @Override public int hashCode(){
    	System.out.println("this is tree hascode");
    	int hash = (getName()==null?0:getName().hashCode());
    	hash = 37*hash + getAge();
    	return hash;
    	}
}
