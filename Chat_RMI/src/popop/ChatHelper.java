package popop;

import javax.swing.*;
import java.awt.*;

public class ChatHelper{

    static String getNonEmptyInfo(Component parent, String mainMsg, String nullMsg){		
		String name = null ;
		try{
			name = (JOptionPane.showInputDialog(parent, mainMsg)).trim() ;
			while(name.compareTo("")==0) {
				JOptionPane.showMessageDialog(parent, nullMsg) ;
				name = (JOptionPane.showInputDialog(parent, mainMsg)).trim() ;
			}//while			
		}catch(NullPointerException ex){}//catch		
		return name ;
	}//getUserName

    static String getUserName(Component parent){		
		return getNonEmptyInfo(parent, "Hey people, what's your name ?", "Don't you have a name ?!?") ;
	}//getUserName
	
	static String getHostName(Component parent){		
		return  getNonEmptyInfo(parent, "What is the name of the remote server ?", "Cannot run without a server name or IP adress !!!") ; 
	}//getHostName
	
	static String getSessionName(Component parent){		
		return  getNonEmptyInfo(parent, "What is the name of the session ?", "Session name should not be empty !!!") ; 
	}//getHostName
	
}