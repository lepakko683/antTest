package le683.customTypes;

import java.util.ArrayList;

import le683.helper.ArrayHelper;

public class Count {
	private String type;
	private int count = 0;
	
	public Count(String type){
		this.type = type;
	}
	
	public int getCount(){
		return this.count;
	}
	
	public void add(int a){
		this.count += a;
	}
	
	public void red(int r){
		this.count -= r;
	}
	
	public String getType(){
		return this.type;
	}
	
	@Override
	public String toString(){
		return count + " votes for " + type;
	}
	
	public static String[] getCountsAsString(ArrayList<Count> counts){
		String[] ret = new String[0];
		for(Count c : counts){
			ret = ArrayHelper.addToList(c.toString(), ret);
		}
		return ret;
	}
}
