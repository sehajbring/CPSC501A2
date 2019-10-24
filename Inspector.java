public class Inspector {

    public void inspect(Object obj, boolean recursive) {
        Class c = obj.getClass();
        inspectClass(c, obj, recursive, 0);
    }

    private void inspectClass(Class c, Object obj, boolean recursive, int depth) {
    	System.out.println("Class name: " + c.getName());
    	System.out.println("Super class name: " + c.getSuperclass().getName());
    	
    	Class[] list = c.getInterfaces();
    	
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