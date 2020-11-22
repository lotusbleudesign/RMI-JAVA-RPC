import java.rmi.*;

public class banqueServeur {

    public static void main(String[] args) {
        try {
            System.out.println( "Serveur : Construction de l'implementation ");
            Banque banque= new Banque();
            System.out.println("Objet banque se conncecte dans le RMIregistry");
            /* Test en réseau local */
            Naming.rebind("rmi://172.16.0.150:1099/MyBanque", banque);
            System.out.println("Attente des invocations des clients :");
        } catch (Exception e) {
            System.out.println("Erreur de liaison de l'objet Banque");
            System.out.println(e.toString());
        }
    }
}
