package universiteApplication;

import org.omg.CORBA.ORB;

import java.io.BufferedReader;
import java.io.FileReader;
import org.omg.CORBA.ORB;

import java.io.BufferedReader;
import java.io.FileReader;

import universite.EtudiantHelper;
import universite.Etudiant;
import universite.EtudiantPackage.Resultat;
import universite.Fac;
import universite.FacHelper;


public class ClientUniversite {
    private static void drawline(){
        System. out .println("---------------------------------------");
    }
    public static void main(String[] args) throws Exception{
        ORB orb = ORB. init (args, null) ;
        BufferedReader br = new BufferedReader(new FileReader("ior.txt")) ;
        String ior = br.readLine() ;
        br.close() ;

        org.omg.CORBA.Object o = orb.string_to_object(ior) ;

        Fac fac = FacHelper. narrow (o) ;
        Etudiant etu = fac.ajouterEtudiant("Steeve") ;
        int numero = etu.numCarte() ;
        Etudiant etudiant = fac.recupererEtudiant(numero) ;
        etudiant.ajouterNoteDans(18.5f, "Informatique");
        etudiant.ajouterNoteDans(12f, "Mathematiques");
        etudiant.ajouterNoteDans(17f, "Anglais");

        System. out .println("Information sur " + etudiant.nom() + " : ");

        System. out .print("Liste des matières passées : ");

        String[] matieres = etudiant.listeMatieres() ;
        for (String m : matieres){
            System. out .print(m + " ");
        }

        System. out .println("");
        System. out .print("Liste des notes : ");
        float[] notes = etudiant.listeNotes() ;

        for (float n : notes){
            System. out .print(n + " ");
        }

        System. out .println("");
        System. out .println("Liste des résultats : ");
        Resultat[] resultats = etudiant.listeResultats() ;

        for (Resultat r : resultats){
            System. out .println(r.matiere + " : " + r.note);
        }

        System. out .println("Moyenne de " + etudiant.nom() + " : " + etudiant.moyenne());
        fac.ajouterEtudiant("Bill") ;
        fac.ajouterEtudiant("Marc") ;
        fac.ajouterEtudiant("Toto") ;
        Etudiant[] etudiants = fac.listerEtudiants() ;
        drawline ();
        System. out .println("Liste des inscrits :");

        for(Etudiant e : etudiants){
            System. out .println(e.numCarte() + "\t\t: " + e.nom());
        }

        fac.retirerEtudiant(3);
        etudiants = fac.listerEtudiants() ;
        drawline ();
        System. out .println("Liste des inscrits :");

        for(Etudiant e : etudiants){
            System. out .println(e.numCarte() + "\t\t: " + e.nom() + ", " + e.moyenne());
        }
    }
}
