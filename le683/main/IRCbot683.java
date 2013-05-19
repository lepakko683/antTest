package le683.main;

import java.util.Random;

import le683.customTypes.Count;
import le683.customTypes.TwoStr;
import le683.customTypes.Vote;
import le683.customTypes.VoteHandler;
import le683.helper.ArrayHelper;

import org.jibble.pircbot.Colors;
import org.jibble.pircbot.PircBot;
import org.jibble.pircbot.User;

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
		new TwoStr("!stopVote", "Stops the running vote and counts the votes"),
		new TwoStr("!troll", "Makes the bot troll the messager")
	};
	
	
	public IRCbot683(){
		this.setName("LeBot683");
		rand = new Random();
		voteRunning=false;
	}
	
	@Override
	public void onMessage(String channel, String sender, String login, String hostname, String msg){
		System.out.println("[MSG] " + channel + " - " + sender + ": " + msg + " | login: " + login + ", hostname: " + hostname);
		String[] nicks = new String[0];
		for(User usr : this.getUsers(Main.chnl)){
			nicks = ArrayHelper.addToList(usr.getNick(), nicks);
		}
		if(sender != this.getNick()){
			
			if(msg.startsWith("!vote ") && voteRunning && vhand != null){
				vhand.addVote(new Vote(sender, msg.substring(6)));
			}
			
			if(msg.startsWith("!troll")){
				this.sendMessage(Main.chnl, "-->" + sender + "<-- This user wants to be trolled!");
			}
			if(isOperator(sender)){ //is OP
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
					vhand = new VoteHandler();
					if(msg.substring(10).length()>0){
						String[] prefixArr = ArrayHelper.parseArray(msg.substring(10));
						if(prefixArr.length>1){//has to have 2 options to vote
							vhand.setPresets(prefixArr);
						}
					}
					voteRunning=true;
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
	
	private void startVote(String[] presets){
		if(!(presets == null)){
			
		}
	}
	
	private boolean isOperator(String name){
		String[] opNicks = new String[0];
		
		for(User usr : this.getUsers(Main.chnl)){
			if(usr.isOp()){
				opNicks = ArrayHelper.addToList(usr.getNick(), opNicks);
			}
		}
		if(ArrayHelper.isOnList(name, opNicks)){
			return true;
		}
		return false;
	}
	
}
