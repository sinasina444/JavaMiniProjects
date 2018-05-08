package classroom7;

import java.util.Set;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;

public class EchoChamber {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Echo<Number> numberEcho = new Echo<Number>();
		numberEcho.echo(10f);
		
		//numberEcho.echo(new Echo<Integer>());
		BoundedEcho<Number> boundNum = new BoundedEcho<>();
		//boundNum.echo(new BoundedEcho<Integer>());
		
		Set<Item> set = new HashSet<Item>(2,1.0f);
		Item foo = new Item("foo");
		set.add(foo);
		System.out.printf("%s%n",set.contains(foo));	//true
		System.out.printf("%s%n",set.contains(new Item("foo")));	//true
		
		foo.setValue("foos");
		System.out.printf("%s%n",set.contains(foo));	//false
		
		Collection<String> collection = new ArrayList<>(2);
		Iterator<String> iterator = collection.iterator();
		
	}

}
