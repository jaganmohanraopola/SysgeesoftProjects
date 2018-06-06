package com.data;
import java.io.*;
import java.sql.ResultSet;
import java.util.Scanner;

public class sample {

	public static void main(String[] args)throws IOException {
		

		  Scanner sc=new Scanner(System.in);
	      System.out.print("1.Upload Flat File\n2.Generate Flat File\n Enter your choice:");
	      int choice=sc.nextInt();
	      switch(choice)
	      {
	      case 1:
	    	  System.out.println("Enter FilePath:");
	    	  String sPath=sc.next();
	    	  ReadFlatFile(sPath);
	    	  break;
	      case 2:GenerateFlatFile();break;
	      default:System.out.println("Wron");
	      }
		}
		public static void ReadFlatFile(String fPath) throws IOException
		{
			
	        String line = "";
	        BufferedReader br=null;
	        try {

	        	br = new BufferedReader(new FileReader(fPath));
	            while ((line = br.readLine()) != null) 
	            {
	                String[] data = line.split("\\|");                
	                DBOperation.InsertData(data[0], data[1], data[2], data[3]);
	            } 
	        } catch (Exception e) 
	        {
	            e.printStackTrace();
	        } 
	        finally 
	        {
	            if (br != null) 
	            {
	                try 
	                {
	                    br.close();
	                } 
	                catch (IOException e) 
	                {
	                    e.printStackTrace();
	                }
	            }
	        }
		}
		public static void GenerateFlatFile()
		{
			try
			{
				String OutPath="E://Uploads//output.txt";
				int rows=0;
				ResultSet rs=DBOperation.GetData();
				File f1=new File(OutPath);
				if(!f1.exists())
				{
					f1.createNewFile();
				}
				FileOutputStream fos = new FileOutputStream(OutPath);			 
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
				bw.write("SNO|Name|FatherName|Mobile");
				bw.newLine();
				while(rs.next())
				{
					bw.write(rs.getString(1)+"|"+rs.getString(2)+"|"
				+rs.getString(3)+"|"+rs.getString(4));
					bw.newLine();	
					rows++;
				}
				bw.close();
				System.out.println(rows+" Rows Exported Successfully....!");
				System.out.println("Please find the file under path:"+OutPath);
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
				System.out.println("Fail to Export");
			}
		}

	}


