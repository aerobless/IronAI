package com.w1nter.IronAI;

import java.util.Properties;
import javax.mail.Folder;
import javax.mail.MessagingException;
import javax.mail.Session;


public class gmailChecker {

	public static int unreadMailCount() {
		// TODO Auto-generated method stub
		Properties props = System.getProperties();
		props.setProperty("mail.store.protocol", "imaps");
		int count = 999;
		try {
		    Session session = Session.getDefaultInstance(props, null);
		    javax.mail.Store store = session.getStore("imaps");
		   System.out.println("Getting mails from Google.");
		    store.connect("imap.gmail.com", accountInformation.gmailLogin(), accountInformation.gmailAuthenticationKey());
		    javax.mail.Folder[] folders = store.getDefaultFolder().list("*");
		    Folder fldr = store.getFolder("Inbox");
	        fldr.open(Folder.HOLDS_MESSAGES);
	        count = fldr.getUnreadMessageCount();
		} catch (MessagingException e) {
		    e.printStackTrace();
		}
		return count;
	}
}
