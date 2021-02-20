package com.dbpack.test;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCExample {
	public static Statement stmt = null;
	
	//Insert records into EMP table
	public static void InsertData(int eid, String ename, int esal) {
		try {
			
			
			String insertQuery = "insert into emp values("+eid+",'"+ename+"',"+esal+")";
			System.out.println("Insert Query: " + insertQuery);
		
			if(stmt.executeUpdate(insertQuery) == 1)
				System.out.println("Emplpyee added successful");
			else
				System.out.println(" Emplpyee Insertion Failed");
		}catch(Exception ex) {
			System.out.println("Invalid data");
		}	
	}
	
	// Retrieve EMP records from DB 
	public static void RetriveData(int eid) {
		try {
			
			String retriveQuery = "Select * from emp where eid = " + eid ;
			System.out.println("Retrive Query: " + retriveQuery);
		
			ResultSet set = stmt.executeQuery(retriveQuery);
			
			if ( set.next() == false )
			{
				System.out.println(" Given Employee is not present in the records");
				}
			else 
			{				
					do
					{
					System.out.println(set.getInt(1)+"---"+set.getString(2)+"---"+set.getInt(3)); 
					}while(set.next());
			}
				
				
		}catch(Exception ex) {
			System.out.println("Exception Occured while Retriving the data into table");
		}
	}

    public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {

    	Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "skc", "skc");	
		stmt = conn.createStatement();
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            //Select an option to perform operation
            System.out.println("Enter the Operation to Perform: 1. Insert  2. Retrive \n");
            int option = Integer.parseInt(br.readLine());
            switch(option){
            
            	case 1:
            		System.out.println("Enter Employee Number: \n");
            		int enum1 = Integer.parseInt(br.readLine());
            		
            		System.out.println("Enter Employee Name: \n");
            		String ename = br.readLine();
            		
            		System.out.println("Enter Employee Salary: \n");
            		int esal = Integer.parseInt(br.readLine());
            		
            		InsertData(enum1,ename,esal);
            		break;
            	case 2:
            		
            		System.out.println("Enter Employee Number: \n");
            		
            		int eid = Integer.parseInt(br.readLine());
            		
            		RetriveData(eid);
            		break;
            }
            stmt.close();
            conn.close();
    }
}

