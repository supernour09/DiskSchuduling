import java.io.FileInputStream;
import java.util.Collections;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;
import java.util.Vector;

/**
 * @author mazen
 *
 */
public class Disk_scheduler {


	public static int initialPosition;
	public static Vector<Integer> requests = new Vector<Integer>();
	static private final String INPUT = "D:\\Eclipse\\java\\workspace\\Disk_Scheduling\\input.txt";

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		
		int choice;
		
		System.out.println("Welcome from Disk Scheduler\n\n");
		System.out.print("Input from File    :: 1\n"
						+  "Input from console :: 2\n"
						+  "Input from GUI     :: 3\n\n"
						+  "Choice :: ");
		Scanner in = new Scanner(System.in);
		choice = in.nextInt();
		System.out.println();
		if(choice == 1)
		{
			readFile();
			execute();
		}
		else if(choice == 2)
		{
			int n,x;
			System.out.print("Initial Position :: ");
			initialPosition = in.nextInt();
			System.out.println();
			
			System.out.print("Please Enter number of inputs :: ");
			n = in.nextInt();
			System.out.println();
			
			System.out.println("Please Enter the Values :: ");
			for(int i = 0 ; i < n ; i++)
			{
				x = in.nextInt();
				requests.add(x);
			}
			
			execute();
		}
		else if(choice == 3)
		{
			MyGui.createForm();
		}
		else
		{
			return;
		}
		
		
		
		
//		Vector<Integer> tmp = new Vector<Integer>();
//
//		for (int i = 0; i < requests.size(); i++) {
//			tmp.add(requests.get(i));
//		}
//
//		FCFS(tmp, initialPosition);
//		SSTF(tmp, initialPosition);
//		SCAN(tmp, initialPosition);
//		CSCAN(tmp, initialPosition);
//		LOOK(tmp, initialPosition);
//		CLOOK(tmp, initialPosition);

	}
	
	public static void execute()
	{
		Vector<Integer> tmp = new Vector<Integer>();

		for (int i = 0; i < requests.size(); i++) {
			tmp.add(requests.get(i));
		}
		
		FCFS(tmp, initialPosition);
		SSTF(tmp, initialPosition);
		SCAN(tmp, initialPosition);
		CSCAN(tmp, initialPosition);
		LOOK(tmp, initialPosition);
		CLOOK(tmp, initialPosition);
	}

	public static void FCFS(Vector<Integer> tmp, int initialPosition) {
		System.out.println("First :: FCFS Algorithm : \nSequence :: \n\n");
		tmp.add(0, initialPosition);
		for (int i = 0; i < tmp.size() - 1; i += 2) {

			System.out.print(" -> " + tmp.get(i) + " -> " + tmp.get(i + 1));
			if (tmp.size() % 2 != 0 && i + 2 >= tmp.size() - 1) {
				System.out.print(" -> " + tmp.get(tmp.size() - 1));
				break;
			}
		}
		System.out.println();

		System.out.println("Total Movements for FCFS :: " + calcMovements(tmp));
		System.out.println("******************************************************************\n\n");

		tmp.remove(0);
	}

	//TODO the algorithim is wrong
	public static void SSTF(Vector<Integer> tmp, int initialPosition) {
		System.out.println("Second :: SSTF Algorithm : \nSequence :: \n\n");
		Integer currentPosition = initialPosition;
		TreeSet<Integer> theSet = new TreeSet<Integer>();
		for(int i=0;i<tmp.size();i++)theSet.add(tmp.get(i));
		Vector<Integer> theOrder = new Vector<Integer>();
		theOrder.add(initialPosition);
		
		while(!theSet.isEmpty()) {
			Integer low = theSet.lower(currentPosition);
			Integer high = theSet.ceiling(currentPosition);

			
			if(high == null||(low!=null &&   Math.abs(currentPosition - low) < Math.abs(high-currentPosition))){
				theOrder.addElement(low);
				currentPosition = low;
				theSet.remove(low);
			}else{
				theOrder.addElement(high);
				currentPosition = high;
				theSet.remove(high);
			}
		}
		for(int i=0;i<theOrder.size();i++){
			System.out.print(" -> " + theOrder.get(i));
		}
		
		System.out.println();

		System.out.println("Total Movements for SSTF :: " + calcMovements(theOrder));
		System.out.println("******************************************************************\n\n");

		//tmp.remove(0);
	}

	public static void SCAN(Vector<Integer> tmp, int initialPosition) {
		Vector<Integer> moves = new Vector<Integer>();
		System.out.println("Third :: SCAN Algorithm : \nSequence :: \n\n");
		tmp.add(initialPosition);
		Collections.sort(tmp);

		for (int i = tmp.indexOf(initialPosition); i > 0; i -= 2) {
			System.out.print(" -> " + tmp.get(i) + " -> " + tmp.get(i - 1));
			moves.add(tmp.get(i));
			moves.add(tmp.get(i - 1));
			if (tmp.indexOf(initialPosition) % 2 == 0 && (i - 2) <= 0) {
				System.out.print(" -> " + tmp.get(0));
				moves.add(tmp.get(0));
				break;
			}
		}
		if (tmp.indexOf(initialPosition) == 0) {
			System.out.print(" -> " + tmp.get(0));
			moves.add(tmp.get(0));
		}
		System.out.print(" -> 0");
		moves.add(0);
		for (int i = tmp.indexOf(initialPosition) + 1; i < tmp.size() - 1; i += 2) {
			System.out.print(" -> " + tmp.get(i) + " -> " + tmp.get(i + 1));
			moves.add(tmp.get(i));
			moves.add(tmp.get(i + 1));
			if ((tmp.size() - 1 - tmp.indexOf(initialPosition)) % 2 != 0 && i + 2 >= tmp.size() - 1)
																										
																										
																										
			{
				System.out.print(" -> " + tmp.get(tmp.size() - 1));
				moves.add(tmp.get(tmp.size() - 1));
				break;
			}

		}
		System.out.println();

		System.out.println("Total Movements for SCAN :: " + calcMovements(moves));
		System.out.println("******************************************************************\n\n");

		tmp.remove(tmp.indexOf(initialPosition));
	}

	public static void CSCAN(Vector<Integer> tmp, int initialPosition) {
		Vector<Integer> moves = new Vector<Integer>();
		System.out.println("Fourth :: CSCAN Algorithm : \nSequence :: \n\n");
		tmp.add(initialPosition);
		Collections.sort(tmp);

		for (int i = tmp.indexOf(initialPosition); i > 0; i -= 2) {
			System.out.print(" -> " + tmp.get(i) + " -> " + tmp.get(i - 1));
			moves.add(tmp.get(i));
			moves.add(tmp.get(i - 1));
			if (tmp.indexOf(initialPosition) % 2 == 0 && (i - 2) <= 0) {
				System.out.print(" -> " + tmp.get(0));
				moves.add(tmp.get(0));
				break;
			}
		}

		if (tmp.indexOf(initialPosition) == 0) {
			System.out.print(" -> " + tmp.get(0));
			moves.add(tmp.get(0));
		}

		System.out.print(" -> 0");
		moves.add(0);

		if (tmp.indexOf(initialPosition) == tmp.size() - 1) {
			System.out.println();

			System.out.println("Total Movements for CSCAN :: " + calcMovements(moves));
			System.out.println("******************************************************************\n\n");

			tmp.remove(tmp.indexOf(initialPosition));
			return;

		} else {

			for (int i = tmp.size() - 1; i > tmp.indexOf(initialPosition); i -= 2) {
				if ((tmp.size() - 1 - tmp.indexOf(initialPosition)) % 2 != 0 
					&& i - 2 < tmp.indexOf(initialPosition))
				{
					System.out.print(" -> " + tmp.get(i));
					moves.add(tmp.get(i));
					break;
				}
				System.out.print(" -> " + tmp.get(i) + " -> " + tmp.get(i - 1));
				moves.add(tmp.get(i));
				moves.add(tmp.get(i - 1));


			}

		}

		System.out.println();

		System.out.println("Total Movements for CSCAN :: " + calcMovements(moves));
		System.out.println("******************************************************************\n\n");
		tmp.remove(tmp.indexOf(initialPosition));
	}
	
	public static void LOOK(Vector<Integer> tmp, int initialPosition) {
		Vector<Integer> moves = new Vector<Integer>();
		System.out.println("Fifth :: LOOK Algorithm : \nSequence :: \n\n");
		tmp.add(initialPosition);
		Collections.sort(tmp);

		for (int i = tmp.indexOf(initialPosition); i > 0; i -= 2) {
			System.out.print(" -> " + tmp.get(i) + " -> " + tmp.get(i - 1));
			moves.add(tmp.get(i));
			moves.add(tmp.get(i - 1));
			if (tmp.indexOf(initialPosition) % 2 == 0 && (i - 2) <= 0) {
				System.out.print(" -> " + tmp.get(0));
				moves.add(tmp.get(0));
				break;
			}
		}
		if (tmp.indexOf(initialPosition) == 0) {
			System.out.print(" -> " + tmp.get(0));
			moves.add(tmp.get(0));
		}
		
		
		for (int i = tmp.indexOf(initialPosition) + 1; i < tmp.size() - 1; i += 2) {
			System.out.print(" -> " + tmp.get(i) + " -> " + tmp.get(i + 1));
			moves.add(tmp.get(i));
			moves.add(tmp.get(i + 1));
			if ((tmp.size() - 1 - tmp.indexOf(initialPosition)) % 2 != 0 && i + 2 >= tmp.size() - 1)
			{
				System.out.print(" -> " + tmp.get(tmp.size() - 1));
				moves.add(tmp.get(tmp.size() - 1));
				break;
			}

		}
		System.out.println();

		System.out.println("Total Movements for LOOK :: " + calcMovements(moves));
		System.out.println("******************************************************************\n\n");

		tmp.remove(tmp.indexOf(initialPosition));
	}
	
	public static void CLOOK(Vector<Integer> tmp, int initialPosition) {
		Vector<Integer> moves = new Vector<Integer>();
		System.out.println("Sixth :: CLOOK Algorithm : \nSequence :: \n\n");
		tmp.add(initialPosition);
		Collections.sort(tmp);

		for (int i = tmp.indexOf(initialPosition); i > 0; i -= 2) {
			System.out.print(" -> " + tmp.get(i) + " -> " + tmp.get(i - 1));
			moves.add(tmp.get(i));
			moves.add(tmp.get(i - 1));
			if (tmp.indexOf(initialPosition) % 2 == 0 && (i - 2) <= 0) {
				System.out.print(" -> " + tmp.get(0));
				moves.add(tmp.get(0));
				break;
			}
		}

		if (tmp.indexOf(initialPosition) == 0) {
			System.out.print(" -> " + tmp.get(0));
			moves.add(tmp.get(0));
		}


		if (tmp.indexOf(initialPosition) == tmp.size() - 1) {
			System.out.println();

			System.out.println("Total Movements for CLOOK :: " + calcMovements(moves));
			System.out.println("******************************************************************\n\n");

			tmp.remove(tmp.indexOf(initialPosition));
			return;

		} else {

			for (int i = tmp.size() - 1; i > tmp.indexOf(initialPosition); i -= 2) {
				if ((tmp.size() - 1 - tmp.indexOf(initialPosition)) % 2 != 0 
					&& i - 2 < tmp.indexOf(initialPosition))
				{
					System.out.print(" -> " + tmp.get(i));
					moves.add(tmp.get(i));
					break;
				}
				System.out.print(" -> " + tmp.get(i) + " -> " + tmp.get(i - 1));
				moves.add(tmp.get(i));
				moves.add(tmp.get(i - 1));


			}

		}

		System.out.println();

		System.out.println("Total Movements for CLOOK :: " + calcMovements(moves));
		System.out.println("******************************************************************\n\n");
		tmp.remove(tmp.indexOf(initialPosition));
	}
	
	

	public static int calcMovements(Vector<Integer> tmp) {
		int result = 0;
		for (int i = 0; i < tmp.size() - 1; i++) {
			result += Math.abs(tmp.get(i) - tmp.get(i + 1));
		}

		return result;
	}

	@SuppressWarnings("resource")
	public static void readFile() {
		FileInputStream instream = null;

		try {
			instream = new FileInputStream(INPUT);
			System.setIn(instream);
		} catch (Exception e) {
			System.err.println("Error Occurred.");
		}

		Scanner in = new Scanner(System.in);
		initialPosition = in.nextInt();
		for (; in.hasNext();) {
			int x = in.nextInt();
			requests.add(x);
		}

		// System.err.println("done.");
		return;
	}

}

// 53
// 8
// 98 183 37 122 14 124 65 67
