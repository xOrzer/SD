package universite;


/**
* universite/_FacStub.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from universite.idl
* vendredi 3 mai 2019 10 h 05 CEST
*/

public class _FacStub extends org.omg.CORBA.portable.ObjectImpl implements universite.Fac
{

  public universite.Etudiant ajouterEtudiant (String nom)
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("ajouterEtudiant", true);
                $out.write_string (nom);
                $in = _invoke ($out);
                universite.Etudiant $result = universite.EtudiantHelper.read ($in);
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return ajouterEtudiant (nom        );
            } finally {
                _releaseReply ($in);
            }
  } // ajouterEtudiant

  public universite.Etudiant recupererEtudiant (int numCarte)
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("recupererEtudiant", true);
                $out.write_long (numCarte);
                $in = _invoke ($out);
                universite.Etudiant $result = universite.EtudiantHelper.read ($in);
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return recupererEtudiant (numCarte        );
            } finally {
                _releaseReply ($in);
            }
  } // recupererEtudiant

  public void retirerEtudiant (int numCarte)
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("retirerEtudiant", true);
                $out.write_long (numCarte);
                $in = _invoke ($out);
                return;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                retirerEtudiant (numCarte        );
            } finally {
                _releaseReply ($in);
            }
  } // retirerEtudiant

  public universite.Etudiant[] listerEtudiants ()
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("listerEtudiants", true);
                $in = _invoke ($out);
                universite.Etudiant $result[] = universite.FacPackage.EtudiantsHelper.read ($in);
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return listerEtudiants (        );
            } finally {
                _releaseReply ($in);
            }
  } // listerEtudiants

  // Type-specific CORBA::Object operations
  private static String[] __ids = {
    "IDL:universite/Fac:1.0"};

  public String[] _ids ()
  {
    return (String[])__ids.clone ();
  }

  private void readObject (java.io.ObjectInputStream s) throws java.io.IOException
  {
     String str = s.readUTF ();
     String[] args = null;
     java.util.Properties props = null;
     org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init (args, props);
   try {
     org.omg.CORBA.Object obj = orb.string_to_object (str);
     org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl) obj)._get_delegate ();
     _set_delegate (delegate);
   } finally {
     orb.destroy() ;
   }
  }

  private void writeObject (java.io.ObjectOutputStream s) throws java.io.IOException
  {
     String[] args = null;
     java.util.Properties props = null;
     org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init (args, props);
   try {
     String str = orb.object_to_string (this);
     s.writeUTF (str);
   } finally {
     orb.destroy() ;
   }
  }
} // class _FacStub