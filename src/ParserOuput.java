import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;


public class ParserOuput {
	private static Parmaters parmaters ;
	private static ArrayList<String> arrayList2 = new ArrayList<String>();
	private static String temp ; 
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		long startTime = System.currentTimeMillis();
		CheckOutClassNo();
		
		long endTime = System.currentTimeMillis();
		System.out.println("SpendTime: "+((endTime-startTime)/1000));
	}

	private static void CheckOutClassNo() throws Exception {
		// TODO Auto-generated method stub
		parmaters = new Parmaters();
		FileReader reader = new FileReader(parmaters.Output_Txt);
		BufferedReader br = new BufferedReader(reader); 
		while(br.ready())
		{
			String[] tempSplit = br.readLine().toString().split("\t");
			temp = tempSplit[1]+"\t"+tempSplit[2];
			arrayList2.add(temp);
		}
		checkIsExist();
	}

	private static void checkIsExist() throws Exception {
		// TODO Auto-generated method stub
		FileWriter fw = new FileWriter(parmaters.DRGs_Txt);
		HashSet<String> set = new HashSet<String>(arrayList2);
		arrayList2.clear();
		arrayList2.addAll(set);
		for(String arrayList:arrayList2)
		{
			fw.write(arrayList+"\n");;
		}
		
		
	}

}
