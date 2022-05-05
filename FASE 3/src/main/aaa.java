package main;

import java.util.Calendar;
import java.util.Collections;
import java.util.LinkedList;

import objects.Mensajero;
import objects.transaccion;
import storage.Merkle_tree;
import storage.Merkle_tree.nodo_merkle;
import storage.TablaHash;
import java.lang.Thread;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;

public class aaa {

	public static void main(String[] args) {
		// String timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime());
		 String timeStamp = new SimpleDateFormat("dd-MM-yyyy::HH:mm:ss").format(Calendar.getInstance().getTime());

		 System.out.println(timeStamp);
		/*
		transaccion trans1 = new transaccion("Guatemala", "Villa Nueva", "5/05/2022 22:43", "Sergie Arizandieta", "Richars");
		LinkedList<transaccion> Lista_transiciones = new  LinkedList<transaccion>();

		for (int i = 0; i < 9; i++) {
			Lista_transiciones.add(trans1);
			System.out.println(i+1);
		}
		
		Merkle_tree arbole_merkle = new Merkle_tree();
		
		arbole_merkle.principal(Lista_transiciones);
		*/
		
		/*
		
		try {
				
			
			String text= "hola";
			
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			digest.reset();
			digest.update(text.getBytes("utf8"));
			String hash = String.format("%064x", new BigInteger(1, digest.digest()));
			
			System.out.println(hash);
			
		} catch (Exception e) {
			// TODO: handle exception
		}*/
				/*
		saber sabo = new saber();
		
		Thread hilo1 = new Thread(sabo);
		hilo1.start();
		
		sabo.setVisible(true);
		*/
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