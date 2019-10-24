public class Inspector {

    public void inspect(Object obj, boolean recursive) {
        Class c = obj.getClass();
        inspectClass(c, obj, recursive, 0);
    }

    private void inspectClass(Class c, Object obj, boolean recursive, int depth) {
    	System.out.println("Class name: " + c.getName());
    	System.out.print("Super classes in order from highest parent to child class: ");
    	recurseSuperClass(c);
    	System.out.println();
    	getInterfaces(c);
    	
    	Class[] list = c.getInterfaces();
    }
    
    public void recurseSuperClass(Class<?> c) {
		
    	if(c.getSuperclass() == null) {
    		if(c.getName().equals("java.lang.Object")) {
    			return;
    		}
    		else {
    			System.out.print (c.getName() + ", ");
    			return;
    		}
    	}
    	recurseSuperClass(c.getSuperclass());
    		System.out.print(c.getName() + ", ");
    }
    
    public void getInterfaces(Class<?> c) {
    	Class <?> [] interfaces = c.getInterfaces();
    	System.out.print(c.getName() + " interfaces: ");
    	for(Class <?> inter: interfaces) {
    		System.out.print(inter.getName() + ", ");	
    	}
    	if(c.getSuperclass() != null) {
    		getRecurseInterfaces(c);
    	}
    }
    
    public void getRecurseInterfaces(Class<?> c) {
    	if(c.getSuperclass() != null) {
    		c= c.getSuperclass();
    		System.out.println();
	    	Class <?> [] interfaces = c.getInterfaces();
	    	if(interfaces.length > 0) {
	    		System.out.print(c.getName() + " interfaces: ");
		    	for(Class <?> inter: interfaces) {
		    		System.out.println(inter.getName()+ ", ");	
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