package corba;

/**
* corba/ChatServerHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from corba.idl
* vendredi 3 mai 2019 14 h 30 CEST
*/

public final class ChatServerHolder implements org.omg.CORBA.portable.Streamable
{
  public corba.ChatServer value = null;

  public ChatServerHolder ()
  {
  }

  public ChatServerHolder (corba.ChatServer initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = corba.ChatServerHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    corba.ChatServerHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return corba.ChatServerHelper.type ();
  }

}