package le683.main;

import java.io.IOException;
import java.util.Scanner;

import org.jibble.pircbot.IrcException;

public class Main {
	private static IRCbot683 bot;
	private static Scanner keybIn;
	
	public static void main(String[] args) {
		bot = new IRCbot683();
		bot.setVerbose(true);
		
		keybIn = new Scanner(System.in);
		
		try {
			bot.connect("irc.esper.net");
			bot.joinChannel("#Adventures-game");
			
			String lnIn="";
			while(bot.isConnected()){
				System.out.print(lnIn + System.lineSeparator());
				if(lnIn != null){
					if(lnIn.equalsIgnoreCase("!exit")){
						bot.quitServer("Bot Stopped!");
						bot.dispose();
						break;
					}else{
						bot.sendMessage("#Adventures-game", lnIn);
					}
				}
			}
			System.out.println("Stopping!");
		} catch (IOException | IrcException e) {
			e.printStackTrace();
		}
		
		
	}

}
