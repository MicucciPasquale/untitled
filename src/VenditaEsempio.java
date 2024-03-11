import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class VenditaEsempio {
    private final List<Vendita> vendite = new ArrayList<>();

    public void caricaVendite(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean primaRiga = true;
            while ((line = br.readLine()) != null) {
                if (primaRiga) {
                    primaRiga = false;
                    continue;
                }
                line = line.trim();
                System.out.println("Riga letta: " + line);
                String[] data = line.split(";");
                if (data.length >= 3) {
                    try {
                        int idUtente = Integer.parseInt(data[0].trim());
                        int idCapo = Integer.parseInt(data[1].trim());
                        double prezzo = Double.parseDouble(data[2].trim());
                        Vendita vendita = new Vendita(idUtente, idCapo, prezzo);
                        vendite.add(vendita);
                    } catch (NumberFormatException e) {
                        System.err.println("Errore nella conversione di un numero in formato stringa.");
                        e.printStackTrace();
                    }
                } else {
                    System.err.println("La riga non ha abbastanza campi per creare una vendita.");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Vendita> getVendite() {
        return vendite;
    }
}
