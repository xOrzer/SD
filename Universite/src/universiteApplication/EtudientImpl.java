package universiteApplication;

import java.util.HashMap;
import java.util.Vector;

import universite.EtudiantPOA;
import universite.EtudiantPackage.Resultat;

public class EtudientImpl extends EtudiantPOA {

	private String nom = null ;
	private int numCarte;

	private HashMap<String, Float> resultats = new HashMap<>() ;

	@Override
	public void ajouterNoteDans(float note, String matiere) 
	{
		resultats.put(matiere, note);
	}

	@Override
	public String[] listeMatieres() 
	{
		return resultats.keySet().toArray(new String[resultats.size()]);
	}

	@Override
	public float[] listeNotes() {
		Float[] notes = resultats.values().toArray(new Float[resultats.size()]) ;
		float[] fNotes = new float[notes.length] ;
		for (int i=0;i<notes.length;i++){
			fNotes[i] = notes[i].floatValue() ;
		}
		return fNotes ;
	}

	@Override
	public float moyenne() {

		float moyenne = 0;
		for(Float note : resultats.values())
		{
			moyenne += note.floatValue();
		}
		moyenne /= resultats.size();
		
		return moyenne;
	}

	@Override
	public Resultat[] listeResultats() {
		Vector<Resultat> results = new Vector<>() ;
		for (String matiere : resultats.keySet()){
			results.add(new Resultat(matiere, resultats.get(matiere))) ;
		}
		return results.toArray(new Resultat[resultats.size()]);
	}

	@Override
	public String nom() {
		return nom;
	}

	@Override
	public void nom(String newNom) 
	{
		nom = newNom;
	}

	@Override
	public int numCarte() {
		return numCarte;
	}
	
	public void numCarte(int num)
	{
		numCarte = num;
	}

}
