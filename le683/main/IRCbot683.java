package le683.main;

import org.jibble.pircbot.PircBot;

public class IRCbot683 extends PircBot {
	public IRCbot683(){
		this.setName("LeBot683");
	}
	
	@Override
	public void onMessage(String channel, String sender, String login, String hostname, String msg){
		System.out.println("[MSG] " + channel + " - " + sender + ": " + msg + " | login: " + login + ", hostname: " + hostname);
		
	}
}
