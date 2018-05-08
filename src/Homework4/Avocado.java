package Homework4;

import Homework4.Tree.Type;

public class Avocado extends Tree{
	public Avocado(String name,int age){
		super(name,age);
		this.strSubType = "Avocado";
	}
	
	private final String strSubType;
	
	public  boolean isSeedEnclosed(){
		return false;
	}
	public  Type getType(){
		return Type.Evergreen;
	}
	
	public String getStrSubType(){
		return strSubType;
	}
	   @Override public boolean equals(Object o){
		   if(o==this){
			   return true;
		   }
		   if(o==null || getClass()!=o.getClass()){
			   return false;
		   }
		   Avocado that = (Avocado)o;
		return (strSubType==that.getStrSubType()) &&(super.equals(that));
	   }
		    @Override public int hashCode(){
			int hash = (getName()==null?0:getName().hashCode());
			hash = 37*hash+(getStrSubType()==null?0:getStrSubType().hashCode());
			hash = 37*hash+getAge();
			return hash;
		    }
}
