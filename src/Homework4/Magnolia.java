package Homework4;

import Homework4.Tree.Type;

public class Magnolia extends Conifer{
	
	public Magnolia(String name,int age){
		super(name,age);
		this.strSubType = "Conifer";
	}
	
	private final String strSubType;
	
	public  boolean isSeedEnclosed(){
		return true;
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
		   Magnolia that = (Magnolia)o;
		return (strSubType==that.getStrSubType()) &&(super.equals(that));
	   }
		    @Override public int hashCode(){
			int hash = super.hashCode();
			hash = 37*hash+(getStrSubType()==null?0:getStrSubType().hashCode());
			return hash;
		    }
}
