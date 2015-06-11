import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;


public class ParserData_step3 {
	private static Parmaters parmaters ;
	private static ArrayList<String> uinqID = new ArrayList<>();
	private static ArrayList<String> fullDataArlist = new ArrayList<>();
	private static HashMap<String,String> fullData = new HashMap<>();
	private static int i = 1 ;
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		//parmaters = new Parmaters();
		long starttime = System.currentTimeMillis();
		GetuniqID();
		CheckData();
		IsSpecialID();
		long endTime = System.currentTimeMillis();
		System.out.println("SpendTime" + (endTime-starttime)/1000);
		System.out.println(i);
	}
    private static void GetuniqID() throws Exception
    {
    	FileReader uniqIDReader= new FileReader(Parmaters.ClassCList);
    	BufferedReader uniqIDBR = new BufferedReader(uniqIDReader);
    	while(uniqIDBR.ready())
    	{
    		
    		uinqID.add(uniqIDBR.readLine());
    		
    	}
    	
    	System.out.println(uinqID.size());
    	uniqIDBR.close();
    };
    private static void CheckData() throws Exception
    {
    	String temp ;
    	FileReader FullDataReader= new FileReader(Parmaters.FullDRGs_CLass10_txt);
    	BufferedReader FullDataBR = new BufferedReader(FullDataReader);
    	while(FullDataBR.ready())
    	{
    		fullDataArlist.add(FullDataBR.readLine());
    		
    	}
    	FullDataBR.close();
    }
	private static void IsSpecialID() throws Exception {
		// TODO Auto-generated method stub
		FileWriter fw = new FileWriter(Parmaters.ClassC_Class_Detail);
		for(String arlist:uinqID)
		{
			 i+=1; 
			for(String fulldata : fullDataArlist)
			{
				String[] temp = fulldata.split("\t");
				if(arlist.equals(temp[0]))
				{	
					System.out.println(fulldata);
					fw.write(fulldata+"\n");
				}
			}
				
		}
		fw.flush();
		fw.close();
		//System.out.println(i);
	};
}
