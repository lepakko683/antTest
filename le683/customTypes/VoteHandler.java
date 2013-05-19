package le683.customTypes;

import java.util.ArrayList;

import le683.helper.ArrayHelper;

public class VoteHandler {
	private ArrayList<Vote> votes;
	
	private String[] presets;
	
	public VoteHandler(){
		votes = new ArrayList<Vote>();
	}
	
	public void setPresets(String[] sets){
		this.presets=sets;
	}
	
	public String[] getPresets(){
		return this.presets;
	}
	
	public boolean isPresetted(){
		if(presets != null){
			return true;
		}
		return false;
	}
	
	public void addVote(Vote v){
		
		boolean tryAdd=false;
		
		if(isPresetted()){
			
			if(ArrayHelper.isOnList(v.getVote(), presets)){
				tryAdd=true;
			}
		}else{
			tryAdd=true;
		}
		if(tryAdd){
			boolean voted = false;
			for(Vote q : votes){
				if(q.getUser().equals(v.getUser())){
					voted=true;
					votes.set(votes.indexOf(q), v);
				}
			}
			if(!voted){
				votes.add(v);
			}
		}
		
	}
	
	/**Remember to stop vote before executing this!*/
	public ArrayList<Count> getCount(){
		ArrayList<Count> ret = new ArrayList<Count>();
		for(int i=0;i<votes.size();i++){
			if(ret.size()==0){
				Count co = new Count(votes.get(i).getVote());
				co.add(1);
				ret.add(co);
			}else{
				boolean cnts=false;
				for(Count cout : ret){
					if(votes.get(i).getVote().equals(cout.getType())){
						Count co = new Count(cout.getType());
						co.add(cout.getCount()+1);
						ret.set(ret.indexOf(cout), co);
						cnts=true;
						break;
					}
				}
				if(!cnts){
					Count co = new Count(votes.get(i).getVote());
					co.add(1);
					ret.add(co);
				}
				
			}
		}
		return ret;
	}
}
