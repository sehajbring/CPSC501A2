import java.io.*;

public class TestCases {
	public static void main (String [] args)throws Exception {

		TestCases tc = new TestCases();
		tc.testMethod("TestFileA.txt", new ClassA(), false);
		tc.testMethod("TestFileB.txt", new ClassB(), false);
		tc.testMethod("TestFileD.txt", new ClassD(), false);
	}

	private void testMethod(String filename, Object testObj, boolean recursive) {
		
		 try {
	            PrintStream old = System.out;
	            Inspector i = new Inspector();
	            File file = new File(filename);
	            FileOutputStream fos = new FileOutputStream(file);
	            PrintStream ps = new PrintStream(fos);
	            System.setOut(ps);
	            System.out.println("======================================================");
	            System.out.println("Filename: " + filename);
	            System.out.println("Running Test: " + testObj);
	            System.out.println("Test will print the methods in the class provided.");
	            System.out.println("Recursive: " + recursive);
	            System.out.println();
	            i.getMethods(testObj.getClass());
	            System.out.println("======================================================");
	            ps.flush();
	            fos.flush();
	            ps.close();
	            fos.close();
	            System.setOut(old);
	        } catch (IOException ioe) {
	            System.err.println("Unable to open file: " + filename);
	        } catch (Exception e) {
	            System.err.println("Unable to compleatly run test: " + testObj);
	            e.printStackTrace();
	        }
	    }
		
	}
	
	

