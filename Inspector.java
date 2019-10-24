public class Inspector {

    public void inspect(Object obj, boolean recursive) {
        Class c = obj.getClass();
        inspectClass(c, obj, recursive, 0);
    }

    private void inspectClass(Class c, Object obj, boolean recursive, int depth) {
    	System.out.println("Class name: " + c.getName());
    	System.out.print("Super classes in order from highest parent to child class: ");
    	recurseSuperClass(c);
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