import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class ParserFinal {
	private static Parmaters parmaters;
	private static ArrayList<String> arylist = new ArrayList<String>();
	private static ArrayList<String> arylistDRGs = new ArrayList<String>();
	private static ArrayList<HashMap<String,Integer>> hmap = new ArrayList<HashMap<String,Integer>>();
	private static int Account = 0 ; 
	public static void main(String[] args) throws Exception {
		long start = System.currentTimeMillis();
		parmaters = new Parmaters();
		ReadDRGsItem();
		ReadFullDRGs();
		AddItemAndOutput();
		long end = System.currentTimeMillis(); 
		System.out.println("Spend Time :  "+((end-start)/1000));
	}

	private static void ReadFullDRGs() throws Exception {
		FileReader fr = new FileReader(parmaters.FullDRGs_Txt);
		BufferedReader br = new BufferedReader(fr);
		while (br.ready()) {
			arylistDRGs.add(br.readLine());
		}
		br.close();
	}

	private static void ReadDRGsItem() throws Exception {
		FileReader fr = new FileReader(parmaters.DRGs_Txt);
		BufferedReader br = new BufferedReader(fr);
		while (br.ready()) {
			arylist.add(br.readLine());
		}
		br.close();
	}
	private static void AddItemAndOutput() throws Exception
	{
		FileWriter fw = new FileWriter(parmaters.Final);
		for(String Item:arylist)
		{
			for(String Item2:arylistDRGs)
			{
				String[] temp = Item2.split("\t");
				String temp2 = temp[1]+"\t"+temp[2]; 
				if(Item.equals(temp2))
				{
					Account += Integer.valueOf(temp[3]);
					
				}
				
			}
			System.out.println(Item+"\t"+Account);
			fw.write(Item+"\t"+Account+"\n");
			Account = 0 ; 
		}
		fw.flush();
		fw.close();
	}
}
