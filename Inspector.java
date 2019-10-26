//import java.awt.List;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;

public class Inspector {

	int tabSpaces;

    public void inspect(Object obj, boolean recursive) {
        Class c = obj.getClass();
        inspectClass(c, obj, recursive, 0);
    }

    private void inspectClass(Class c, Object obj, boolean recursive, int depth) {
    	System.out.println("Original class name: " + c.getName());
    	System.out.println("Super classes: ");
    	recurseSuperClass(c.getSuperclass());
    	System.out.println();
    	getInterfaces(c);
    	System.out.println();
    	getConstructors(c);
    	System.out.println();
    	getMethods(c);
    	recurseFields(c, recursive, obj);
    }
    
    public void getMethods(Class<?> c) {
    	Method [] methods = c.getDeclaredMethods();
    	for(Method meth: methods) {
    		System.out.println(c.getCanonicalName() + " method: " +meth.getName());
    		Class<?>[] exceptions = meth.getExceptionTypes();
    		for(Class <?> excep : exceptions) {
    			System.out.println(" Has exceptions: \n\t\t" +excep.getName());
    		}
    		Class<?>[] params = meth.getParameterTypes();
    		if(params.length > 0) {
    			System.out.println(" Has parameters: ");
	    		for(Class <?> param : params) {
	    			System.out.println("  "+ param.getName() );
	    		}
    		}
    		else {
    			System.out.println(" Contains no parameters");
    		}
    		System.out.println(" The return type is: " + meth.getReturnType());
    		System.out.println(" Modifiers: " + meth.getModifiers());
    		System.out.println();
    	}	
    }
    
    public void recurseFields(Class<?> c, boolean recursive, Object obj) {
    	if(recursive == false) {
    		Field[] fields = c.getDeclaredFields();
    		System.out.println(c.getName() +" fields: ");
    		for(Field field : fields) {
    			field.setAccessible(true);
    			System.out.println(" Name: " + field.getName());
    			System.out.println("  Type: " +field.getType());
    			System.out.println("  Modifier: " +field.getModifiers());
    			try {
    				System.out.println("\tCurrent value: " + field.get(obj));
    			} catch (IllegalArgumentException | IllegalAccessException e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    			}
    			System.out.println();
    			
    		}
    	}
    	else {
    		Field[] fields = c.getDeclaredFields();
    		System.out.println("Fields: ");
    		for(Field field : fields) {
    			field.setAccessible(true);
    			System.out.println("Name: " + field.getName());
    			System.out.println("\tType: " +field.getType());
    			if(field.getType().isArray()) {
    				
    			}
    			System.out.println("\tModifier: " +field.getModifiers());
    			try {
    				System.out.println("\tCurrent value: " + field.get(obj));
    			} catch (IllegalArgumentException | IllegalAccessException e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    			}
    		}
    		
    	}
    	
    }
    
    public void recurseSuperClass(Class<?> c) {
    	if(c.getSuperclass() == null) {
    		if(c.getName().equals("java.lang.Object")) {
    			return;
    		}
    		else {
    			return;
    		}
    	}
    	tabSpaces++;
    	System.out.println("\t" + c.getName());
    	recurseSuperClass(c.getSuperclass());

    }
    

    public void getInterfaces(Class<?> c) {
    	Class <?> [] interfaces = c.getInterfaces();
    	System.out.print(c.getName() + " interfaces: ");
    	for(Class <?> inter: interfaces) {
    		System.out.print(inter.getName());	
    	}
    	if(c.getSuperclass() != null) {
    		getRecurseInterfaces(c);
    	}
    }
    
    
    public void getRecurseInterfaces(Class<?> c) {
    	if(c.getSuperclass() != null) {
    		
    		c= c.getSuperclass();
	    	Class <?> [] interfaces = c.getInterfaces();
	    	System.out.println();
	    	if(interfaces.length > 0) {
	    		System.out.print(c.getName() + " interfaces: ");
		    	for(Class <?> inter: interfaces) {
		    		System.out.println(inter.getName() + " ");	
		    	}
	    	}
	    	if(c.getSuperclass() != null) {
	    		getRecurseInterfaces(c);
	    	}
    	}
    	else {
    		return;
    	}

    }
   
   
    public void getConstructors(Class<?> c) {
    	Constructor<?>[] constructors = c.getConstructors();
    	for(Constructor<?> con: constructors) {
    		System.out.println("Constructor Name: " + con.getName());
    		Class<?>[] parameters = con.getParameterTypes();
    		if(parameters.length > 0) {
	    		System.out.println(" Parameters: ");   
	    		for(Class<?> para : parameters) {
	    			System.out.println("\t" + para.getName());
	    			if(para.getSuperclass() != null) {
	    				recurseSuperClass(c);
	    			}
	    		}
    		}
    		else {
    			System.out.println(" Constructor has no parameters");
    			
    		}
    		System.out.println(" Modifiers: " + con.getModifiers());
    		System.out.println();
    	}
    }
    
    
    public static void main (String [] args) {
    	ClassB a;
		try {
			a = new ClassB();
			Inspector inspec = new Inspector ();
			inspec.inspect(a, false);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }

}