package le683.main;

import java.util.Random;

import le683.customTypes.Count;
import le683.customTypes.TwoStr;
import le683.customTypes.Vote;
import le683.customTypes.VoteHandler;
import le683.helper.ArrayHelper;

import org.jibble.pircbot.Colors;
import org.jibble.pircbot.PircBot;

public class IRCbot683 extends PircBot {
//	private User[] chnlUsers;
	/**CmdName - explanation*/
	private Random rand;
	private boolean voteRunning;
	
	private String msgPrefix = Colors.CYAN;
	
	private VoteHandler vhand;
	
	private final TwoStr[] commands = new TwoStr[]{
		new TwoStr("!help", "Prints commands"),
		new TwoStr("!pickUser", "Prints name of a random user"),
		new TwoStr("!startVote", "Starts a vote, vote by !vote *your vote*"),
		new TwoStr("!vote", "Vote *your vote*"),
		new TwoStr("!stopVote", "Stops the running vote and counts the votes")
	};
	
	
	public IRCbot683(){
		this.setName("LeBot683");
		rand = new Random();
		voteRunning=false;
	}
	
	@Override
	public void onMessage(String channel, String sender, String login, String hostname, String msg){
		System.out.println("[MSG] " + channel + " - " + sender + ": " + msg + " | login: " + login + ", hostname: " + hostname);
		String[] nicks = new String[this.getUsers(Main.chnl).length];
		for(int i=0; i<nicks.length; i++){
			nicks[i] = this.getUsers(Main.chnl)[i].getNick();
		}
		if(sender != this.getNick()){
			
			if(msg.startsWith("!vote ") && voteRunning && vhand != null){
				vhand.addVote(new Vote(sender, msg.substring(6)));
			}
			if(ArrayHelper.isOnList(sender, nicks)){ //is OP
				if(msg.startsWith("!help")){
					this.sendMessage(Main.chnl, msgPrefix+"-----------Bot Help----------");
					String msgLine="";
					for(TwoStr ts : commands){
						msgLine = msgLine.concat(ts + " | ");
					}
					this.sendMessage(Main.chnl, msgPrefix+msgLine);
					this.sendMessage(Main.chnl, msgPrefix+"-----------Bot Help----------");
				
				}
				if(msg.startsWith("!startVote") && !voteRunning){
					voteRunning=true;
					vhand = new VoteHandler();
					this.sendMessage(Main.chnl, msgPrefix+"A vote has been started! Vote by typing '!vote *your vote*'");
				}
				
				if(msg.startsWith("!stopVote") && voteRunning){
					voteRunning=false;
					this.sendMessage(Main.chnl, msgPrefix+"The vote has ended!, counting votes, you are wasting your time by typing '!vote *whatever*'");
					String msgLine = "";
					for(String st : Count.getCountsAsString(vhand.getCount())){
						msgLine = msgLine.concat(st + ", ");
					}
					this.sendMessage(Main.chnl, msgPrefix+"The results are: " + msgLine);
					vhand=null;
				}
				if(msg.startsWith("!pickUser")){
					nicks = (String[]) ArrayHelper.removeFromList(this.getNick(), nicks);
				
					this.sendMessage(Main.chnl, nicks[rand.nextInt(nicks.length)]);
					
				}
				
				
			}
		}
	}
	
}
