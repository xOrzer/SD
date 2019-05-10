package universiteApplication;

import java.util.HashMap;
import java.util.Vector;

import org.omg.PortableServer.POA;

import universite.Etudiant;
import universite.EtudiantHelper;
import universite.FacPOA;

public class FacImpl extends FacPOA {

	private int nbEtudiants;
	private HashMap<Integer, Etudiant> etudiants;
	
	private POA rootPOA;
	
	public FacImpl(POA rootPOA) 
	{
		etudiants = new HashMap<>();
		nbEtudiants = 0;
		this.rootPOA = rootPOA;
	}
	
	@Override
	public Etudiant ajouterEtudiant(String nom) {
		Etudiant etuRef = null;
		
		EtudientImpl etu = new EtudientImpl();
		etu.nom(nom);
		int num = ++nbEtudiants;
		etu.numCarte(num);
		org.omg.CORBA.Object ref;
		try 
		{
			ref = rootPOA.servant_to_reference(etu);
			etuRef = EtudiantHelper.narrow(ref);
			etudiants.put(num, etuRef);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
		return etuRef;
	}

	@Override
	public Etudiant recupererEtudiant(int numCarte) {
		return etudiants.get(numCarte);
	}

	@Override
	public void retirerEtudiant(int numCarte) 
	{
		etudiants.remove(numCarte);
	}

	@Override
	public Etudiant[] listerEtudiants() {
		Vector<Etudiant> etus = new Vector<>() ;
		for (Integer num : etudiants.keySet()){
			etus.add(etudiants.get(num)) ;
		}
		return etus.toArray(new Etudiant[etus.size()]);
	}

}
