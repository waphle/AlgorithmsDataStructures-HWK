import java.util.Scanner;
import java.io.*;

public class hashTablePartTwo {
	
// S = Hash Table Size, N = Number of AUID's
public static final int S = 50, N = 31;
  
	static int hash(int x) {
		return (x % N);
	}
  
	static int insert(int hashTable[], int x, int i) {
  
		int pos = (hash(x) + i) % S;
		if(hashTable[pos] == 0) {
			hashTable[pos] = x;
			return 0;
		}
		
		return 1 + insert(hashTable, x, i + 1);
	}
  
	static int search(int hashTable[], int key, int i) {
  
		int pos = (hash(key) + i) % S;
  
		// case for key not found
		if(i == S || hashTable[pos] == 0) {
			System.out.println(key + " not found");
			return 0;
		}
		
		if(hashTable[pos] == key) {
			System.out.println(key + " found at index: " + pos);
			return 0;
		}
  
		return 1 + search(hashTable, key, i+1);
	}
  
  	// driver method
	public static void main(String[] args) {
  
		// hash table size of 50
		int hashTable[] = new int[50];
		double avg = 0.0;
  
		try {
			Scanner sc = new Scanner(new File("C:\\Users\\jeffz\\Downloads\\2020 Fall\\Algorithms and Data Structures\\HWK\\HWK6-JeffZhang\\AUID.txt"));
  
			for(int i=1; i<=32; i++) {
				int x = sc.nextInt();
				avg += insert(hashTable, x, 0);
			}
  
		} 
		
		catch(FileNotFoundException e) {
			System.out.println("AUID.text file not found.");
		}
  
		// calculate average by dividing by 32
		avg = avg / 32;
		System.out.println("The average number of probing in creating hash table: " + avg);

		System.out.println("Hash table: ");
		for(int i=0; i<S; i++) {
			System.out.print(hashTable[i] + " ");
		}
		
		System.out.println();
  
		// Add keys here for the program to search
		int searchKeys[] = {97091, 12345, 64301, 68815};
		avg = 0.0;
  
		for(int i=0; i<searchKeys.length; i++) {
			avg += search(hashTable, searchKeys[i], 0);
		}
  
		avg = avg / searchKeys.length;
		System.out.println("Average number of probing in searching hash table: " + avg);
	}
	
}