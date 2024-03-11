import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UtenteEsempio {
    private final List<Utente> utenti = new ArrayList<>();

    public void caricaUtenti(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {

            br.readLine();

            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(";");
                int idUtente = Integer.parseInt(data[0]);
                String nome = data[1];
                String cognome = data[2];


                Utente utente = new Utente(idUtente, nome, cognome);
                utenti.add(utente);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void aggiungiUtente(Utente nuovoUtente) {
    }
}
