package universite.FacPackage;


/**
* universite/FacPackage/EtudiantsHelper.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from universite.idl
* vendredi 3 mai 2019 10 h 05 CEST
*/

abstract public class EtudiantsHelper
{
  private static String  _id = "IDL:universite/Fac/Etudiants:1.0";

  public static void insert (org.omg.CORBA.Any a, universite.Etudiant[] that)
  {
    org.omg.CORBA.portable.OutputStream out = a.create_output_stream ();
    a.type (type ());
    write (out, that);
    a.read_value (out.create_input_stream (), type ());
  }

  public static universite.Etudiant[] extract (org.omg.CORBA.Any a)
  {
    return read (a.create_input_stream ());
  }

  private static org.omg.CORBA.TypeCode __typeCode = null;
  synchronized public static org.omg.CORBA.TypeCode type ()
  {
    if (__typeCode == null)
    {
      __typeCode = universite.EtudiantHelper.type ();
      __typeCode = org.omg.CORBA.ORB.init ().create_sequence_tc (0, __typeCode);
      __typeCode = org.omg.CORBA.ORB.init ().create_alias_tc (universite.FacPackage.EtudiantsHelper.id (), "Etudiants", __typeCode);
    }
    return __typeCode;
  }

  public static String id ()
  {
    return _id;
  }

  public static universite.Etudiant[] read (org.omg.CORBA.portable.InputStream istream)
  {
    universite.Etudiant value[] = null;
    int _len0 = istream.read_long ();
    value = new universite.Etudiant[_len0];
    for (int _o1 = 0;_o1 < value.length; ++_o1)
      value[_o1] = universite.EtudiantHelper.read (istream);
    return value;
  }

  public static void write (org.omg.CORBA.portable.OutputStream ostream, universite.Etudiant[] value)
  {
    ostream.write_long (value.length);
    for (int _i0 = 0;_i0 < value.length; ++_i0)
      universite.EtudiantHelper.write (ostream, value[_i0]);
  }

}
