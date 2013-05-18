package le683.customTypes;

public class TwoStr {
	private String first;
	
	private String second;

	public TwoStr(String first, String second){
		this.first = first;
		this.second = second;
	}
	
	public void setFirst(String str){
		this.first = str;
	}
	
	public void setSecond(String str){
		this.second = str;
	}
	
	public String getFirst(){
		return this.first;
	}
	
	public String getSecond(){
		return this.second;
	}
	
	@Override
	public String toString(){
		return this.first + " - " + this.second;
	}

}
