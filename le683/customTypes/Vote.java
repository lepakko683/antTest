package le683.customTypes;

public class Vote {
	private String user;
	private String vote;
	
	public Vote(String user, String vote){
		this.user = user;
		this.vote = vote;
	}
	
	public String getUser(){
		return this.user;
	}
	
	public String getVote(){
		return this.vote;
	}

}
