package main;

import java.util.Collections;
import java.util.LinkedList;

import objects.Mensajero;
import storage.TablaHash;
import java.lang.Thread;

public class aaa {

	public static void main(String[] args) {
		
		
		saber sabo = new saber();
		
		Thread hilo1 = new Thread(sabo);
		hilo1.start();
		
		sabo.setVisible(true);
		
		/*
		// TODO Auto-generated method stub
		TablaHash tabla = new TablaHash();
		tabla.mostar();
		
		long dpi = Long.valueOf("3000523830101");
		Long dpi2 = Long.valueOf("3000523830101");
		
		if(dpi == dpi2.longValue()) {
			
			//System.out.println("TODO BIEN");
		}
		// Create ine LinkedList for Student object
        LinkedList<Student> List= new LinkedList<Student>();
        List.add(new Student("Meet", 32, 2));
        List.add(new Student("Jhon", 11, 5));
        List.add(new Student("Sham", 92, 1));
        List.add(new Student("William", 86, 3));
        List.add(new Student("Harry", 35, 4));
  
        // Print the Unsorted LinkedList
        System.out.println("UnSorted List");
        for (Student s : List) {
            System.out.println("rank: "+ s.Rank + " name: " + s.Name + " id: "+ s.Id);

        }
        System.out.println();
  
        // sort in ascending order
        Collections.sort(List);
  
        // Print the sorted LinkedList
        System.out.println("Sorted List");
        for (Student s : List) {
            // Print the sorted LinkedList
            System.out.println("rank: "+ s.Rank + " name: " + s.Name + " id: "+ s.Id);
        }
		*/
	}

}

class Student implements Comparable<Student> {
    String Name;
    int Id;
    int Rank;
  
    Student(String name, int id, int rank)
    {
        this.Name = name;
        this.Id = id;
        this.Rank = rank;
    }
  
    // Override the compareTo() method
    @Override public int compareTo(Student s)
    {
        if (Rank > s.Rank) {
            return 1;
        }
        else if (Rank == s.Rank) {
            return 0;
        }
        else {
            return -1;
        }
    }
}