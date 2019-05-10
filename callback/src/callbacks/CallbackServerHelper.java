package callbacks;


/**
* callbacks/CallbackServerHelper.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from callbacks.idl
* vendredi 3 mai 2019 11 h 30 CEST
*/

abstract public class CallbackServerHelper
{
  private static String  _id = "IDL:callbacks/CallbackServer:1.0";

  public static void insert (org.omg.CORBA.Any a, callbacks.CallbackServer that)
  {
    org.omg.CORBA.portable.OutputStream out = a.create_output_stream ();
    a.type (type ());
    write (out, that);
    a.read_value (out.create_input_stream (), type ());
  }

  public static callbacks.CallbackServer extract (org.omg.CORBA.Any a)
  {
    return read (a.create_input_stream ());
  }

  private static org.omg.CORBA.TypeCode __typeCode = null;
  synchronized public static org.omg.CORBA.TypeCode type ()
  {
    if (__typeCode == null)
    {
      __typeCode = org.omg.CORBA.ORB.init ().create_interface_tc (callbacks.CallbackServerHelper.id (), "CallbackServer");
    }
    return __typeCode;
  }

  public static String id ()
  {
    return _id;
  }

  public static callbacks.CallbackServer read (org.omg.CORBA.portable.InputStream istream)
  {
    return narrow (istream.read_Object (_CallbackServerStub.class));
  }

  public static void write (org.omg.CORBA.portable.OutputStream ostream, callbacks.CallbackServer value)
  {
    ostream.write_Object ((org.omg.CORBA.Object) value);
  }

  public static callbacks.CallbackServer narrow (org.omg.CORBA.Object obj)
  {
    if (obj == null)
      return null;
    else if (obj instanceof callbacks.CallbackServer)
      return (callbacks.CallbackServer)obj;
    else if (!obj._is_a (id ()))
      throw new org.omg.CORBA.BAD_PARAM ();
    else
    {
      org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate ();
      callbacks._CallbackServerStub stub = new callbacks._CallbackServerStub ();
      stub._set_delegate(delegate);
      return stub;
    }
  }

  public static callbacks.CallbackServer unchecked_narrow (org.omg.CORBA.Object obj)
  {
    if (obj == null)
      return null;
    else if (obj instanceof callbacks.CallbackServer)
      return (callbacks.CallbackServer)obj;
    else
    {
      org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate ();
      callbacks._CallbackServerStub stub = new callbacks._CallbackServerStub ();
      stub._set_delegate(delegate);
      return stub;
    }
  }

}
