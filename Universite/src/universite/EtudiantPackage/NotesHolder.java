package universite.EtudiantPackage;


/**
* universite/EtudiantPackage/NotesHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from universite.idl
* vendredi 3 mai 2019 10 h 05 CEST
*/

public final class NotesHolder implements org.omg.CORBA.portable.Streamable
{
  public float value[] = null;

  public NotesHolder ()
  {
  }

  public NotesHolder (float[] initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = universite.EtudiantPackage.NotesHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    universite.EtudiantPackage.NotesHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return universite.EtudiantPackage.NotesHelper.type ();
  }

}