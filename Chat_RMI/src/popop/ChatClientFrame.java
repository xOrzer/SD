package popop;

import javax.swing.*;

public class ChatClientFrame extends JFrame{
    
    String hostName ;
    protected String userName ;
    
    
    JFrame myFrame = this ;
    
    protected ChatClientFrame(String aHost, String aUserName){
        if ((hostName = aHost) == null){
            hostName = ChatHelper.getHostName(this) ;
        }
        if (hostName == null){ System.exit(0) ; } 
        
        if ((userName = aUserName) == null ){
            userName = ChatHelper.getUserName(this) ;
        }
        if (userName == null){ System.exit(0) ; }
   
    } 
    
    ChatClientFrame(String aHost){
        hostName = aHost ;
    }
    
	public void closeWindow(){
	   System.exit(0) ;
	}
    
}