import java.rmi.RemoteException;
import java.util.concurrent.ExecutionException;

public class Compte {

    /*Ecrire un fichier Compte.java qui contient l’implémentation d’une classe Compte avec les
        informations suivantes:
        - le nom du propriétaire du compte
        - un mot de passe
        - le solde du compte
     */
    private String nom;
    private String password;
    private int solde;

    public Compte(String nom, String password){
        this.nom = nom;
        this.password = password;
        this.solde = 0;
    }

    public String getNom() {
        return nom;
    }

    public String getPassword() {
        return password;
    }

    public int getSolde() {
        synchronized (this) {
            return solde;
        }
    }

    public int addSolde(int somme) throws RemoteException {
        if(somme < 0) {
            throw new RemoteException("Opération impossile : somme négatif");
        }
            // mutex sur l'objet lui même pour éviter thread sur méthode
            synchronized (this){
                solde += somme;
                return solde;
            }
    }

    public int subSolde(int somme) throws RemoteException {
        if(somme < 0) {
            throw new RemoteException("Opération impossile : somme négatif");
        }
        synchronized (this){
            solde -= somme;
            return solde;
        }
    }

    public String toString(){

        String  s = "Compte : " + getNom() + "\n" ;
                s += "Mot de password : " + getPassword()+ "\n" ;
                s += "Solde tout compte : " + getSolde() ;
        return s;
    }
}
