import java.rmi.Remote;
import java.rmi.RemoteException;

public interface BanqueInterface extends Remote {

    // lister tous les  applications disponible dans banque
    void ouvrirCompte(String nom, String password) throws RemoteException;
    int fermerCompte(String nom, String password) throws RemoteException;
    //ici ne pas utiliser l'objet Compte côté client, le guichet doit passer par l'objet Banque
    int deposer(int somme, String nom, String password) throws RemoteException;
    int retirer(int somme, String nom, String password) throws RemoteException;
    int solde(String nom, String password)throws RemoteException;
}

