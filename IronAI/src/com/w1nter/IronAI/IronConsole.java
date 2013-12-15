package com.w1nter.IronAI;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IronConsole {
	//Propertise
	static boolean voiceOutput = true;
	public static ArrayList<String> lastCommands = new ArrayList<String>();
	public static int commandPointer = 21;
	
	//Startup Config
	static String line1 = "IronConsole ready for input";
	static String line2 = ">";
	static String line3 = ">";
	static String line4 = ">";
	static String line5 = ">";
	static String line6 = ">";
	static String line7 = ">";
	static String line8 = ">";
	static String line9 = ">";
	static String line10 = ">";
	static String line11 = ">";
	static String line12 = ">";
	static String line13 = ">";
	static String line14 = ">";
	static String line15 = ">";


	public IronConsole() {
		// TODO Auto-generated constructor stub
	}
	
	public static void initializeConsole() {
		String output = (line1+"\n"+line2+"\n"+line3+"\n"+line4+"\n"+line5+"\n"+line6+"\n"+line7+"\n"+line8+"\n"+line9+"\n"+line10+"\n"+line11+"\n"+line12+"\n"+line13+"\n"+line14+"\n"+line15);
		// MainGuiController.commandlineOut(output);
		 for (int i= 0; i<21; i++) {
			 lastCommands.add("");			 
		 }
	}
	
	public static void print(String s) {
		line1 = line2;
		line2 = line3;
		line3 = line4;
		line4 = line5;
		line5 = line6;
		line6 = line7;
		line7 = line8;
		line8 = line9;
		line9 = line10;
		line10 = line11;
		line11 = line12;
		line12 = line13;
		line13 = line14;
		line14 = line15;
		line15 = s;
		initializeConsole();
	}

	public static void consoleReadCommands(String s) {
		Pattern helloRegex = Pattern.compile("!identify", Pattern.CASE_INSENSITIVE);
		Matcher hello = helloRegex.matcher(s);
		if (hello.find()) {
			print("Hi there! I'm running IronAI Version "+IronAiMain.currentVersion+" and all systems are ok.");
			voiceCMD("Hi there! I'm running IronAI Version "+IronAiMain.currentVersion+" and all systems are ok.");
		}
		Pattern helpRegex = Pattern.compile("!help", Pattern.CASE_INSENSITIVE);
		Matcher help = helpRegex.matcher(s);
		if (help.find()) {
			print("Sorry. One does not simply enter !help.");
			voiceCMD("Sorry. One does not simply enter !help.");
		}
		Pattern voicOffRegex = Pattern.compile("!voice", Pattern.CASE_INSENSITIVE);
		Matcher voiceOff = voicOffRegex.matcher(s);
		if (voiceOff.find()) {
			if (IronConsole.voiceOutput == true) {
				IronConsole.voiceOutput = false;
				print("Yes sir! I'm silent now!");
			}
			else if (IronConsole.voiceOutput == false) {
				IronConsole.voiceOutput = true;
				print("I've regained my voice sir!");
				voiceCMD("I've regained my voice sir!");
			}
		}
		Pattern sayRegex = Pattern.compile("!say", Pattern.CASE_INSENSITIVE);
		Matcher say = sayRegex.matcher(s);
		if (say.find()) {
			s = s.substring(5);
			voiceCMD(s);
		}
		Pattern addPhysRegex = Pattern.compile("!addPhys", Pattern.CASE_INSENSITIVE);
		Matcher addPhys = addPhysRegex.matcher(s);
		if (addPhys.find()) {
			s = s.substring(9);
			s = s.trim();
			int i =  Integer.parseInt(s);
			IronAiMain.updatePhysicOperations(i);
			print("Good job Sir!");
			voiceCMD("Good job Sir!");
		}
		Pattern alarmRegex = Pattern.compile("!alarm", Pattern.CASE_INSENSITIVE);
		Matcher alarm = alarmRegex.matcher(s);
		if (alarm.find()) {
			print("Of course Sir!");
			voiceCMD("Of course Sir!");
			IronAiMain.ironAlarm();
		}
		
		Pattern setAlarmRegex = Pattern.compile("!setalarm", Pattern.CASE_INSENSITIVE);
		Matcher setAlarm = setAlarmRegex.matcher(s);
		if (setAlarm.find()) {
			s = s.substring(10);
			IronAiMain.waketime = Integer.parseInt(s);
			print("System-Alarm set for "+IronAiMain.waketime);
			voiceCMD("Setting the alarm for "+IronAiMain.waketime+" Sir!");
		}
	}
	
	static void voiceCMD(String s) {
		if (voiceOutput == true) {
			IronAiMain.speak(s);
		}
	}
	
	public static void addCommand(String s) {
		lastCommands.set(0, lastCommands.get(1));
		lastCommands.set(1, lastCommands.get(2));
		lastCommands.set(2, lastCommands.get(3));
		lastCommands.set(3, lastCommands.get(4));
		lastCommands.set(4, lastCommands.get(5));
		lastCommands.set(5, lastCommands.get(6));
		lastCommands.set(6, lastCommands.get(7));
		lastCommands.set(7, lastCommands.get(8));
		lastCommands.set(8, lastCommands.get(9));
		lastCommands.set(9, lastCommands.get(10));
		lastCommands.set(10, lastCommands.get(11));
		lastCommands.set(11, lastCommands.get(12));
		lastCommands.set(12, lastCommands.get(13));
		lastCommands.set(13, lastCommands.get(14));
		lastCommands.set(14, lastCommands.get(15));
		lastCommands.set(15, lastCommands.get(16));
		lastCommands.set(16, lastCommands.get(17));
		lastCommands.set(17, lastCommands.get(18));
		lastCommands.set(18, lastCommands.get(19));
		lastCommands.set(19, lastCommands.get(20));
		lastCommands.add(20, s);
	}
}
