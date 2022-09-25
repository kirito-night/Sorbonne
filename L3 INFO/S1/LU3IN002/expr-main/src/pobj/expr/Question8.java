package pobj.expr;
import java.util.*;
public class Question8 {
	public static Map<String , Integer> env1(){
		return new HashMap<>();
	}
	
	
	public static Map<String , Integer> env2(){
		Map<String,Integer> map =  new HashMap<>();
		map.put("x", 10);
		map.put("y", 20);
		return map;
	}
	
	public static Map<String , Integer> env3(){
		Map<String,Integer> map =  new HashMap<>();
		map.put("z", 9);
		
		return map;
	}
}
