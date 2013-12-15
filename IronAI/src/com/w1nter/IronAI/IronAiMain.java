/*
 * Copyright (c) 2012, Oracle and/or its affiliates. All rights reserved.
 */

package com.w1nter.IronAI;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import com.w1nter.IronAI.gmailChecker;
import com.w1nter.IronAI.weatherChecker;


public class IronAiMain extends Application {
	
	//Program Properties
	public static String author = "Theo Winter";
	public static String controlServer = "lol-cloud.com";
	public static int currentVersion = 1;
	
	//Variable Sharing
	public static boolean updateRequired = checkForUpdate();
	public static int unreadEmails = gmailChecker.unreadMailCount();
	public static double temperature = weatherChecker.getWeather();
	public static int waketime = 800;
	public static boolean switchpic = false;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Application.launch(IronAiMain.class, (java.lang.String[])null);
        speak("Iron AI shutting down.");
    }

    @Override
    public void start(Stage primaryStage) {
        try {
            StackPane page = (StackPane) FXMLLoader.load(IronAiMain.class.getResource("mainGUI.fxml"));
            Scene scene = new Scene(page);
            primaryStage.setScene(scene);
            primaryStage.setFullScreen(true);
           // primaryStage.initStyle(StageStyle.UNDECORATED);
            primaryStage.setTitle("Iron AI - Blood Crystal Control Panel");
            primaryStage.show();
            updateGUI();
            IronConsole.initializeConsole();

            //Waker
            new Timer().schedule(
            	    new TimerTask() {

            	        @Override
            	        public void run() {
            	            System.out.println("Checking time...");
            	            MainGuiController.moveoutTest();
            	              try {
          						wakeUp();
          					} catch (ParseException e) {
          						// TODO Auto-generated catch block
          						e.printStackTrace();
          					}
            	        }
            	    }, 0, 5000);
            
            //Background Worker (for performance intensive tasks)
            new Timer().schedule(
            	    new TimerTask() {

            	        @Override
            	        public void run() {
            	            System.out.println("Ping, Background Worker ready");
            	        }
            	    }, 0, 5000);
            
            //GUI-Updater (Only fast tasks)
            Timeline fiveSecondsWonder = new Timeline(new KeyFrame(Duration.seconds(10), new EventHandler<ActionEvent>() {

                public void handle(ActionEvent event) {
                    updateGUI();
                }
            }));
            fiveSecondsWonder.setCycleCount(Timeline.INDEFINITE);
            fiveSecondsWonder.play();

            
        } catch (Exception ex) {
            Logger.getLogger(IronAiMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static void updateGUI() {
        String hh = new SimpleDateFormat("HH").format(Calendar.getInstance().getTime());
        String mm = new SimpleDateFormat("mm").format(Calendar.getInstance().getTime());
      //  MainGuiController.changeTime(hh+":"+mm);
       // MainGuiController.changeEmail(IronAiMain.unreadEmails+" E-Mails");
       // MainGuiController.changeTemperature(IronAiMain.temperature+" °C");
       // MainGuiController.refreshImageView();
        print("switched");
    }
    
    public static void speak(String speech){
    	//System.out.println("Voice:"+speech);
        String [] speak = new String[2];
        speak[0] = "say";
        speak[1] = speech;
        Runtime rtSpeak = Runtime.getRuntime();
        try {
    		@SuppressWarnings("unused")
			Process proc = rtSpeak.exec(speak);
    	} catch (IOException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	}
    }
    
    static void playAlarm(){
    	//System.out.println("Playing alarm sountrack.mp3");
        String [] play = new String[2];
        play[0] = "afplay";
        play[1] = "/Users/theowinter/Music/ironmanalarm.mp3";
        Runtime rtAlarm = Runtime.getRuntime();
        try {
    		@SuppressWarnings("unused")
			Process proc = rtAlarm.exec(play);
    	} catch (IOException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	}
    }

    static void pause(int i){
    	try {
    		Thread.sleep(i*1000);
    	} catch (InterruptedException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	}
    }

    static void print(String s) {
    	System.out.println(s);
    }
    
    static String getDay() {
        String dd = new SimpleDateFormat("dd").format(Calendar.getInstance().getTime());
		int ddTranslate = Integer.parseInt(dd);
        switch(ddTranslate) {
        case 1:
        	dd = "first";
        	break;
        case 2:
        	dd = "second";
        	break;
        case 3:
        	dd = "third";
        	break;
        case 4:
        	dd = "forth";
        	break;
        case 5:
        	dd = "fivth";
        	break;
        case 6:
        	dd = "sixt";
        	break;
        case 7:
        	dd = "seventh";
        	break;
        case 8:
        	dd = "eight";
        	break;
        case 9:
        	dd = "nineth";
        	break;
        case 10:
        	dd = "tenth";
        	break;
        case 11:
        	dd = "eleventh";
        	break;
        case 12:
        	dd = "twelveth";
        	break;
        case 13:
        	dd = "thirteenth";
        	break;
        case 14:
        	dd = "fourteenth";
        	break;
        case 15:
        	dd = "fiveteenth";
        	break;
        case 16:
        	dd = "sixteenth";
        	break;
        case 17:
        	dd = "seventeenth";
        	break;
        case 18:
        	dd = "eighteenth";
        	break;
        case 19:
        	dd = "nineteenth";
        	break;
        case 20:
        	dd = "twentieth";
        	break;
        case 21:
        	dd = "twenty first";
        	break;
        case 22:
        	dd = "twenty second";
        	break;
        case 23:
        	dd = "twenty third";
        	break;
        case 24:
        	dd = "twenty fourth";
        	break;
        case 25:
        	dd = "twenty fivth";
        	break;
        case 26:
        	dd = "twenty sixt";
        	break;
        case 27:
        	dd = "twenty seventh";
        	break;
        case 28:
        	dd = "twenty eight";
        	break;
        case 29:
        	dd = "twenty nineth";
        	break;
        case 30:
        	dd = "thirtieth";
        	break;
        case 31:
        	dd = "thirty first";
        	break;
        case 32:
        	dd = "thrity second";
        	break;
        case 33:
        	dd = "thirtieth thrid";
        	break;
        }
		return dd;
    }
    
	public static boolean checkForUpdate() {
		URL url;
		String versionString = "0";
		boolean updateRequired = false;
		try {
			System.out.println("Checking for updates on: "+controlServer);
			url = new URL("http://" + controlServer + "/apps/ironAI/mostRecentVersion.txt");
			URLConnection con = url.openConnection();
			Pattern p = Pattern.compile("text/html;\\s+charset=([^\\s]+)\\s*");
			Matcher m = p.matcher(con.getContentType());
			/*
			 * If Content-Type doesn't match this pre-conception, choose default
			 * and hope for the best.
			 */
			String charset = m.matches() ? m.group(1) : "ISO-8859-1";
			Reader r = new InputStreamReader(con.getInputStream(), charset);
			StringBuilder buf = new StringBuilder();
			while (true) {
				int ch = r.read();
				if (ch < 0)
					break;
				buf.append((char) ch);
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// e.printStackTrace();
			waitForInternet();
		} catch (NullPointerException e) {
			// e.printStackTrace();
			waitForInternet();
		}
		int mostRecentVersion = Integer.parseInt(versionString);
		if (currentVersion < mostRecentVersion) {
			System.out.println("IronAI needs to be updated");
			updateRequired = true;
		} else {
			System.out.println("IronAI is already up to date.");
		}
		return updateRequired;
	}

	public static void waitForInternet() {
		int sleepTimerSeconds = 5;
		System.out.println("Internet failed. Going to sleep for "+sleepTimerSeconds+" seconds.");
		try {
			Thread.sleep(sleepTimerSeconds*1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
			System.out.println("Fatal Error. Can't sleep daddy.");
		}
		System.out.println("Trying to reconnect...");
	}
	
	public static String getCleanDate() {
        String dd = new SimpleDateFormat("dd").format(Calendar.getInstance().getTime());
        String MM = new SimpleDateFormat("MM").format(Calendar.getInstance().getTime());
        String YYYY = new SimpleDateFormat("YYYY").format(Calendar.getInstance().getTime());
		String date = (YYYY+MM+dd);
		return date;
	}
	
	public static void submitToWeb(String url, Map<String, String> data) throws Exception {
		URL siteUrl = new URL(url);
		HttpURLConnection conn = (HttpURLConnection) siteUrl.openConnection();
		conn.setRequestMethod("POST");
		conn.setDoOutput(true);
		conn.setDoInput(true);

		DataOutputStream out = new DataOutputStream(conn.getOutputStream());

		Set<String> keys = data.keySet();
		Iterator<String> keyIter = keys.iterator();
		String content = "";
		for (int i = 0; keyIter.hasNext(); i++) {
			Object key = keyIter.next();
			if (i != 0) {
				content += "&";
			}
			content += key + "=" + URLEncoder.encode(data.get(key), "UTF-8");
		}
		System.out.println(content);
		out.writeBytes(content);
		out.flush();
		out.close();
		BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		String line = "";
		while ((line = in.readLine()) != null) {
			System.out.println(line);
		}
		in.close();
	}
	
	public static void updatePhysicOperations(int i) {

		// SEND TEXT
		Map<String, String> data = new HashMap<String, String>();
		data.put("date", getCleanDate());
		data.put("primitivePhysicalOperations", ""+i);
		try {
			submitToWeb("http://" + controlServer + "/BloodLeash/ironAiDay.php", data);
			System.out.println("Status update success");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	static void ironAlarm() {
        //--------- Get e-mail info
        String emailmessage = "Wardy ate your emails";
        if(IronAiMain.unreadEmails != 999) {
        	if(IronAiMain.unreadEmails == 1) {
            	emailmessage = ("During the night you've received " + IronAiMain.unreadEmails + " new email.");  		
        	}
        	else if (IronAiMain.unreadEmails != 1) {
            	emailmessage = ("During the night you've received " + IronAiMain.unreadEmails + " new emails.");   		
        	}
        }

        String hh = new SimpleDateFormat("HH").format(Calendar.getInstance().getTime());
        String mm = new SimpleDateFormat("mm").format(Calendar.getInstance().getTime());

        String dd = getDay();
      
        //Daily countdown to certain event
       // Date today = new Date();
        DateTime today= new DateTime();
        DateTimeFormatter format = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
        DateTime test = format.parseDateTime("2013-08-13 08:00:00");
        int days = Days.daysBetween(today, test).getDays();
          
        playAlarm();
        String MMMM = new SimpleDateFormat("MMMM").format(Calendar.getInstance().getTime());
        speak("Good morning. Today's the " +dd+ " of " +MMMM+ " And its "+ hh+ " "+mm+" At the moment it is "+IronAiMain.temperature+ " degrees outside. " +emailmessage + 
        		" You've got "+days+" days left until the final exams. And we're now switching over to BBC for the latest world news!");
       
	}
	
	static void wakeUp() throws ParseException{
		SimpleDateFormat parser = new SimpleDateFormat("HH:mm");
        String hh = new SimpleDateFormat("HH").format(Calendar.getInstance().getTime());
        String mm = new SimpleDateFormat("mm").format(Calendar.getInstance().getTime());
        String currentTimeS = hh+mm;
        int currentTimeI = Integer.parseInt(currentTimeS);
        int wakeTimeI = waketime;
        int offset = wakeTimeI-currentTimeI;
        print(currentTimeI +" waky waky "+ wakeTimeI);
        print("Offset "+offset);
        if (offset == 0) {
        	print("Alarm triggered");
        	ironAlarm();
        	try {
				Thread.sleep(300000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
	}
    
}