package StreamAPI_Lambda_expression;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.testng.Assert;


// Count the number of names starting with alphabet"A"
public class Streams_Lambda_1 {

	public void Arrylist() {
		ArrayList<String> al = new ArrayList<String>();
		al.add("Aman");
		al.add("Madan");
		al.add("Anchal");
		al.add("Meera");
		al.add("Shadow");
		int count = 0;
		for(int i=0; i<al.size(); i++) {
			String names = al.get(i);
			
			if(names.startsWith("A")) {
				count++;
			}
		}
		System.out.println(count);
	}
	
	public void Streams() {
		ArrayList<String> al = new ArrayList<String>();
		al.add("Aman");
		al.add("Madan");
		al.add("Anchal");
		al.add("Meera");
		al.add("Shadow");
		al.add("Aayansh");
		
		// there is no life for intermediate operation if there is no terminal operation.
		// terminal operation will execute only if inter operation(filter) returns true.
		// We can create stream with stream package ( Stream.of() )
		// how to use filter in Stream API
		// s -> s.actions -> we can define any char value without initialization 
		long c = al.stream().filter(s -> {     // if there are multiple conditions then we can use {} braces
			return s.startsWith("A");
		}).count();
		System.out.println(c);
		
		long ak = Stream.of("Aman","Madan","Anchal","Meera","Shadow","Aayansh","Kumar","Maurya").filter(a->a.startsWith("K")).count();
		System.out.println(ak);
		
		//print all the names which has character > 4
		// method 1 :using arraylist
		al.stream().filter(ab->ab.length()>4).forEach(ab->System.out.println(ab));
		// method 2 : using soft stream 
		Stream.of("Aman","Madan","Anchal","Meera","Shadow","Aayansh","Kumar","Maurya").filter(ab->ab.length()>4)
		.forEach(ab->System.out.println(ab));
		
	//	limiting the output result
		Stream.of("Aman","Madan","Anchal","Meera","Shadow","Aayansh","Kumar","Maurya").filter(akm->akm.length()>4).limit(2)
		.forEach(akm->System.out.println(akm));
		
	}
	
	public void Streammap() {
		// map is used in stream to alterate the changes in the filtered set.
		ArrayList<String> al = new ArrayList<String>();
		al.add("Aman");
		al.add("Madan");
		al.add("Anchal");
		al.add("Meera");
		al.add("Shadow");
		al.add("Aayansh");
		
		String [] arr = {"ChandraBali","Nirmala","Piyush","Shweta","Himanshu","Supriya"};
		
		// print names which have last letter "a" In uppercase.
		al.stream().filter(s->s.endsWith("a")).map(s->s.toUpperCase()).forEach(s->System.out.println(s));
		
		//print names which have first letter as "a" with uppercase and sorted
		al.stream().filter(s->s.startsWith("A")).sorted().map(s->s.toUpperCase()).forEach(s->System.out.println(s));
		
		// merging two 2 different arrays
		// since stream works only on arraylist hence will convert the array into arraylist.
		List<String> al2 = Arrays.asList(arr);
		Stream<String> newStream = Stream.concat(al.stream(), al2.stream());
		//newStream.sorted().forEach((s->System.out.println(s)));
		
		// check new concated stream contains word "Dark"
		// above we have used newStream hence we can't do any other operation now on newStream therefore commented it
		boolean flag = newStream.anyMatch(s->s.equalsIgnoreCase("Dark"));
		System.out.println(flag);
		Assert.assertTrue(flag);
		
		
	}

	public void StremCollect() {
		// collectors is used to stores the elements in different datatypes i.e. list,map,set etc eg -> (Collectors.toList()): 
		// filter is used for the pre-req/ conditions
		ArrayList<String> al = new ArrayList<String>();
		al.add("Aman");
		al.add("Madan");
		al.add("Anchal");
		al.add("Meera");
		al.add("Shadow");
		al.add("Aayansh");
		
		List<String> ls = al.stream().filter(s->s.startsWith("A")).map(s->s.toLowerCase()).collect(Collectors.toList());
		System.out.println(ls.get(0));
				
		// sort the array - 3rd index 
		List<Integer> value = Arrays.asList(3,2,2,7,5,1,9,7);
		List<Integer> lsi = value.stream().distinct().sorted().collect(Collectors.toList());
		System.out.println(lsi.get(2));
		
		// Que -> print unique number from this arrayList
		//value.stream().distinct().sorted().forEach(s->System.out.print(s+" "));
		
		// Repeating elements
		Set<Integer> unique = new HashSet<Integer>();
		Set<Integer> missing = value.stream().filter(s->!unique.add(s)).collect(Collectors.toSet());
		System.out.println(missing);
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Streams_Lambda_1 obj =new Streams_Lambda_1();
		//obj.Arrylist();
		//obj.Streams();
		//obj.Streammap();
		obj.StremCollect();
	}

}

/*
  What are Streams ? (datatype of stream is long)
  -> Stream API is new feature available from java 8
  -> By using streams, we can perform various aggregate operations on the data returned from collections classes by drastically 
     reduce the complexity of code.
     
  What are Lambda Expression ?
  -> Lambda expressions introduce the new arrow operator -> into Java. It divides the lambda expressions into two parts :
  * The left side specifies the parameters required by the expression, which could also be empty if no parameters are required.
  * The right side is the lambda body which specifies the actions of the lambda expression.                                                                                                                                                                               
 
 The working of stream can be explained in three stages : 
 1. Create a stream.
 2. Perform intermediate operations on the initial stream to transform it into another stream and so on, on further intermediate
  	operations.
 3. Performed terminal operation on the final stream to get the result.
 
 * An important characteristic of intermediate operations is laziness.
 * When executing this code snippet, nothing is printed to the console. That is because intermediate operations will only be
   executed when a terminal operation is present.
   
 Note : The aggregate operations that we perform on the collection, array or any other data source do not change the data of
        the source, they simply return a new stream.   	
 
 
 
 */
