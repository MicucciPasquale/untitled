import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        UtenteEsempio utenteEsempio = new UtenteEsempio();
        utenteEsempio.caricaUtenti("C:/Users/Utente/Desktop/Progetto Java Basics/untitled/resources/utenti.csv");
        CapoEsempio capoEsempio = new CapoEsempio();
        capoEsempio.caricaCapi("C:/Users/Utente/Desktop/Progetto Java Basics/untitled/resources/capi.csv");
        VenditaEsempio venditaEsempio = new VenditaEsempio();
        venditaEsempio.caricaVendite("C:/Users/Utente/Desktop/Progetto Java Basics/untitled/resources/vendite.csv");

        Scanner scanner = new Scanner(System.in);

        int scelta;
        do {
            System.out.println("Scegli un'operazione:");
            System.out.println("1. Visualizzare tutti i capi second hand");
            System.out.println("2. Comprare un capo esistente");
            System.out.println("3. Restituire un capo");
            System.out.println("4. Aggiungere nuovo utente");
            System.out.println("5. Esporta un file con i capi disponibili");
            System.out.println("0. Uscire");

            scelta = scanner.nextInt();

            switch (scelta) {
                case 1:
                    System.out.println("Elenco dei capi presenti nel sistema:");
                    try (BufferedReader reader = new BufferedReader(new FileReader("C:/Users/Utente/Desktop/Progetto Java Basics/untitled/resources/capi.csv"))) {
                        String line;
                        while ((line = reader.readLine()) != null) {
                            if (line.trim().isEmpty() || line.trim().equals(";")) {
                                continue;
                            }
                            String[] capoData = line.split(";");
                            if (capoData.length < 1 || capoData[0].trim().isEmpty()) {
                                continue;
                            }
                            System.out.println(line);
                        }
                    } catch (IOException e) {
                        System.out.println("Errore durante la lettura del file: " + e.getMessage());
                    }
                    break;

                case 2:
                    System.out.println("Inserisci l'ID del capo che desideri prenotare:");
                    int idCapoPrenotato = scanner.nextInt();
                    System.out.println("Inserisci il tuo ID utente:");
                    int idUtentePrenotante = scanner.nextInt();
                    Capo capoPrenotato = null;
                    for (Capo capo : capoEsempio.getCapi()) {
                        if (capo.getId() == idCapoPrenotato) {
                            capoPrenotato = capo;
                            break;
                        }
                    }
                    if (capoPrenotato != null) {
                        capoPrenotato.prenota(idUtentePrenotante);
                        System.out.println("Prenotazione effettuata con successo per il capo con ID: " + idCapoPrenotato);
                    } else {
                        System.out.println("Capo non trovato nel sistema.");
                    }
                    break;

                case 3:
                    System.out.println("Inserisci l'ID della vendita da restituire:");
                    int idVendita = scanner.nextInt();

                    Vendita venditaDaRestituire = null;
                    for (Vendita vendita : venditaEsempio.getVendite()) {
                        if (vendita.getId() == idVendita) {
                            venditaDaRestituire = vendita;
                            break;
                        }
                    }
                    if (venditaDaRestituire != null) {
                        venditaDaRestituire.restituisciCapo();
                        System.out.println("Capo restituito con successo per la vendita con ID: " + idVendita);
                    } else {
                        System.out.println("Vendita non trovata nel sistema.");
                    }
                    break;

                case 4:
                    System.out.println("Inserisci l'ID dell'utente:");
                    int idUtente = scanner.nextInt();
                    System.out.println("Inserisci il nome dell'utente:");
                    String nomeUtente = scanner.next();
                    System.out.println("Inserisci il cognome dell'utente:");
                    String cognomeUtente = scanner.next();
                    System.out.println("Inserisci la data di nascita dell'utente:");
                    String dataNascitaUtente = scanner.next();
                    System.out.println("Inserisci l'indirizzo dell'utente:");
                    String indirizzoUtente = scanner.next();
                    System.out.println("Inserisci il documento ID dell'utente:");
                    String documentoIdUtente = scanner.next();

                    Utente nuovoUtente = new Utente(idUtente, nomeUtente, cognomeUtente, dataNascitaUtente, indirizzoUtente, documentoIdUtente);

                    utenteEsempio.aggiungiUtente(nuovoUtente);
                    System.out.println("Nuovo utente aggiunto con successo.");
                    break;

                case 5:
                    LocalDate currentDate = LocalDate.now();
                    String fileName = "capi_" + currentDate.format(DateTimeFormatter.ofPattern("dd_MM_yyyy")) + ".csv";

                    try (FileWriter writer = new FileWriter(fileName)) {
                        writer.write("ID;Data Inserimento;Tipologia;Marca;Taglia;Prezzo\n");

                        for (Capo capo : capoEsempio.getCapi()) {
                            if (capo.isDisponibile()) {
                                writer.write(capo.getId() + ";" + capo.getDataInserimento() + ";" +
                                        capo.getTipologia() + ";" + capo.getMarca() + ";" +
                                        capo.getTaglia() + ";" + capo.getPrezzo() + "\n");
                            }
                        }

                        System.out.println("Esportazione completata. Il file è stato salvato come '" + fileName + "'.");
                    } catch (IOException e) {
                        System.out.println("Si è verificato un errore durante l'esportazione dei capi.");
                        e.printStackTrace();
                    }
                    break;

                case 0:
                    System.out.println("Uscita dall'applicazione.");
                    break;
                default:
                    System.out.println("Scelta non valida.");
            }

        } while (scelta != 0);

        scanner.close();
    }

    private static void visualizzaCapiDisponibili(String s) {
    }
}
