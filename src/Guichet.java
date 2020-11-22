
import java.io.InputStreamReader;
import java.rmi.Naming;
import java.util.Scanner;

public class Guichet {

    // Créer  constante qui vont être mes choix
    private static final int OUVRIRCOMPTE = 1;
    private static final int SOLDECOMPTE = 2;
    private static final int RETIRER = 3;
    private static final int DEPOSER = 4;
    private static final int FERMERCOMPTE = 5;

    private static String login = "";
    private static String password = "";
    public static void main (String [] args){


        BanqueInterface banque = null;
        int montant = 0;

        try {
            // Tester en premier lieu une connexion sinon on sort
            banque = (BanqueInterface)Naming.lookup("rmi://172.16.0.150:1099/MyBanque");

        } catch (Exception e) {
            System.out.println ("Impossible de se connecter à ma banque !");
            System.out.println (e.toString());
            System.exit(1);
        }

        while(true) {
            System.out.println("Que souhaitez-vous effectuer ?");
            System.out.println("1- Ouvrir un compte ");
            System.out.println("2- Consulter mon compte");
            System.out.println("3- Retirer");
            System.out.println("4- Deposer");
            System.out.println("5- Fermer mon compte");
            System.out.println(" Annuler ou Quitter : appuyer sur entrée");

            // Récuperer les entrées saisies de l'utilisateur
            Scanner scanner = new Scanner(new InputStreamReader(System.in));
            String input = scanner.nextLine();
            // Convertir le String en integer
            int operation = 0;
            try {
                operation = Integer.valueOf(input);
            }catch(Exception e){}

            try {
                // Attention swich case uniquement sur integer d'où l'utilisation d'un entier.
                switch (operation) {

                    case OUVRIRCOMPTE:
                        banque.ouvrirCompte(demandeLogin(scanner), demandePassword(scanner));
                        break;
                    case SOLDECOMPTE:
                        int solde = banque.solde(demandeLogin(scanner), demandePassword(scanner));
                        System.out.println("Votre solde actuel est de : " + solde + " €");
                        break;
                    case RETIRER:
                        System.out.println("Quel est le montant à retirer ? ");
                        montant = Integer.valueOf(scanner.nextLine());
                        solde = banque.retirer(montant, demandeLogin(scanner), demandePassword(scanner));
                        System.out.println("Votre nouveau solde est de : " + solde + " €");
                        break;
                    case DEPOSER:
                        System.out.println("Combien voulez-vous déposer ? ");
                        montant = Integer.valueOf(scanner.nextLine());
                        solde = banque.deposer(montant, demandeLogin(scanner), demandePassword(scanner));
                        System.out.println("Votre nouveau solde est de : " + solde + " €");
                        break;
                    case FERMERCOMPTE:
                        System.out.println("Attention demande de fermeture compte, action irreversible !");
                        solde = banque.fermerCompte(login, demandePassword(scanner));
                        System.out.println("Votre compte a bien été supprimé ! Il vous restez " + solde +" sur votre compte" );
                        break;
                    default:
                        // Autre choix, on stoppe le programme
                        System.out.println(" Au revoir ");
                        System.exit(0);
                        break;
                }
            } catch (Exception e) {
                System.out.println( "Opération impossible : " + e.getMessage());
            }
        }
    }

    // Si pas de saisie, retourne le login du compte en cours
    public static String demandeLogin(Scanner scanner){
        String loginPrecedant = ( login.isEmpty() ) ? "": " ( "+login+" , appuyer sur entrée )";
        System.out.println("Veuillez saisir votre login :"+ loginPrecedant);
        String newLogin = scanner.nextLine();
        if ( !newLogin.isEmpty() ) {
            login = newLogin;
        }
        return login;
    }
    public static String demandePassword(Scanner scanner){
        String passwordPrecedant = ( password.isEmpty() ) ? "": "(*******)";
        System.out.println("Quel est votre mot de passe "+ passwordPrecedant +"? ");
        String newpassword = scanner.nextLine();
        if ( !newpassword.isEmpty() ) {
            password = newpassword;
        }
        return password;
    }
}
