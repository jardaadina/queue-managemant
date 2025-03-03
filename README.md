# queue-managemant
Obiectivul temei
  Obiectivul principal al proiectului este dezvoltarea unui simulator pentru gestionarea cozilor de clienți folosind thread-uri și mecanisme de sincronizare.
Obiective secundare:
Implementarea a două strategii de distribuție:
Selectarea cozii cu cel mai scurt timp de așteptare.
Selectarea cozii cu cei mai puțini clienți.
Crearea unei interfețe grafice pentru afișarea în timp real a simulării.
Analiza problemei, modelare, scenarii, cazuri de utilizare

Cerințe Funcționale:
Generarea unui thread distinct pentru fiecare server.
Simularea distribuției și procesării serviciilor pentru fiecare client.

Cerințe Non-Funcționale:
Implementarea unei interfețe grafice pentru afișarea în timp real a simulării.

Mod de utilizare:
După rularea simulatorului, va apărea interfața grafică.
Se introduc valorile dorite în câmpurile disponibile (în caz de valori neconforme, apare un mesaj de eroare).
Se selectează strategia de distribuire dorită din combo-box.
Se apasă Enter pentru a începe simularea.
Se deschide o a doua interfață grafică ce afișează în timp real:
Lista de așteptare
Timpul curent
Statusul fiecărei cozi

Proiectare
Diagrama UML conține:
Clasele, interfețele și enum-ul utilizat.
Relațiile dintre ele și interacțiunile principale.

Implementare
SimulationManager
Coordonează execuția simulatorului.
Inițializează interfața grafică (View).
Metoda run orchestrează afișarea și desfășurarea simulării.

View
Inițializează interfața grafică principală.
Preia datele introduse de utilizator.
Inițializează interfața grafică secundară și declanșează procesul simulatorului.

Scheduler
Gestionează cozile și distribuirea clienților.
Fiecare coadă este un obiect de tip Server.

Server
Fiecare coadă conține o listă de clienți (Task).
Gestionează timpul de așteptare și procesare a clienților.
Thread-ul serverului procesează primul client din listă, reducându-i timpul de procesare în fiecare secundă.

Task
Reprezintă un client în simulator.
Conține timpul de procesare asociat fiecărui client.

ConcreteStrategyTime & ConcreteStrategyQueue
Implementări ale interfeței Strategy.
Se ocupă de adăugarea clienților în cozi conform strategiei de distribuție alese.

Strategy (Interfață)
Definește metoda addTask() utilizată în strategiile de distribuire.

Enum SelectionPolicy
Reprezintă cele două strategii de distribuție a clienților.

Rezultate
Pentru validarea corectitudinii simulării, s-au realizat șase rulări distincte, iar rezultatele au fost înregistrate în șase fișiere .txt.

Concluzii
Prin acest proiect, am aprofundat gestionarea thread-urilor și sincronizarea acestora, utilizând BlockingDeque pentru gestionarea cozilor. O îmbunătățire posibilă ar fi utilizarea atributului waitingPeriod din Server, pentru:
Monitorizarea timpului de așteptare.
Calcularea timpului mediu de așteptare.
Optimizarea procesului de distribuție a clienților în ConcreteStrategyTime.


