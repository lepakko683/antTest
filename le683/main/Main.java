package le683.main;

import java.io.IOException;
import java.util.Scanner;

import org.jibble.pircbot.IrcException;

public class Main {
	private static IRCbot683 bot;
	private static Scanner keybIn;
	
	public static final String chnl="#Adventures-game";
	
	public static void main(String[] args) {
		bot = new IRCbot683();
		bot.setVerbose(true);
		
		keybIn = new Scanner(System.in);
		
		try {
			bot.connect("irc.esper.net");
			bot.joinChannel(Main.chnl);
			String lnIn="";
			while(bot.isConnected()){
				lnIn = keybIn.nextLine();
				//System.out.print(lnIn + System.lineSeparator());
				if(lnIn != null && lnIn.length()>0){
					if(lnIn.equalsIgnoreCase("!exit")){
						bot.quitServer("Bot Stopped!");
						bot.dispose();
						break;
					}else{
						bot.sendMessage(Main.chnl, lnIn);
					}
				}
				lnIn=null;
			}
			System.out.println("Stopping!");
		} catch (IOException | IrcException e) {
			e.printStackTrace();
		}
		
		
	}

}
