package TCP_Chat_01 ;

import javax.swing.JFrame;

public class ChatClientFrame extends JFrame{
    
    String hostName ;
    String userName ;
    
    
    JFrame myFrame = this ;
    
    ChatClientFrame(String aHost, String aUserName){
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