package corba;


/**
* corba/ChatServerOperations.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from corba.idl
* vendredi 3 mai 2019 14 h 30 CEST
*/

public interface ChatServerOperations 
{
  void register (corba.ChatClient c);
  void unRegister (corba.ChatClient c);
  void sendToAll (String msg, corba.ChatClient from);
} // interface ChatServerOperations
