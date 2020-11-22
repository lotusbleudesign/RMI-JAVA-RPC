# RMI-JAVA-RPC
Petit TP RMI JAVa effectué en Licence (Université Paris Descartes / Paris Sorbonne Nord).


RMI (Remote Method Invocation) est une technologie fournie à partir du JDK 1.1 pour permettre de mettre en œuvre facilement des objets distribués.

Implémentation d’un Remote Procédure Call en Java.
L’application RMI permet la communication entre machines virtuelles Java (JVM) qui peuvent se trouver physiquement sur la même machine ou sur deux machines distinctes.
Technologie basé sur les systèmes distribués Client/serveur, en mode synchrone ou asynchrone.

Se distingue en 3 parties :

- Serveur RMI
- Client RMI
- Registry RMI (annuaire)

L’application dispose d’une interface commune permettant la communication et les paramètres à effectuer entre le client et le serveur.


Lancer l’annuaire RMI :
rmiregistry -J-Djava.security.policy=client1.policy

Attention, lancer l’annuaire depuis le répertoire où se trouve vos fichiers !
