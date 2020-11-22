import java.rmi.server.UnicastRemoteObject;
import java.util.Hashtable;
import java.rmi.*;

public class Banque extends UnicastRemoteObject implements BanqueInterface  {

    private Hashtable<String, Compte> comptes;

    public Banque() throws RemoteException {
        // utiliser constructeur UnicastRemoteObject
        super();
        comptes = new Hashtable<String, Compte>();
    }

    public void ouvrirCompte(String nom, String password) throws RemoteException {
        // mutex verrou
        synchronized (this){

            Compte compte = comptes.get(nom);

            if (compte != null) {
            throw new RemoteException("Compte deja existant : " + nom + " !");
            }

            comptes.put(nom, new Compte(nom, password));
            }

    }

    private Compte verifie(String nom, String password) throws RemoteException {

        Compte compte = comptes.get(nom);
        if (compte != null && password.equals(compte.getPassword())) {
            return compte;
        } else {
            throw new RemoteException(" Compte dejà existant ou mot de passe inexistant !");
        }

    }

    public int fermerCompte(String nom, String password) {
        /**
         * Methode Synchronized
         * Ce qui signifie qu'un seul thread par instance de la classe peut exécuter cette méthode.
         * */
        synchronized (this) {
            Compte compte = comptes.get(nom);
            int solde = 0;
            if (compte != null) {
                solde = compte.getSolde();
                comptes.remove(nom);
            }
            return solde;
        }
    }

    public int deposer(int somme,String nom, String password) throws RemoteException {
        Compte c = verifie(nom,password);
        return c.addSolde(somme);
    }

    public int retirer(int somme, String nom, String password) throws RemoteException {
        Compte c = verifie(nom,password);
        return c.subSolde(somme);
    }

    public int solde(String nom, String password) throws RemoteException {
        Compte c = verifie(nom,password);
        return c.getSolde();
    }

    public String toString(){

        String s = "";
        for (String key : comptes.keySet())
        {
            s += "key : " + key + " ";
            s += "compte : " + comptes.get(key) + "\n";
        }
        return s;
    }
}