public class Capo {
    private int id;
    private String nome;
    private String dataInserimento;
    private String tipologia;
    private String marca;
    private String taglia;
    private double prezzo;
    private boolean disponibile;

    public Capo(int id, String nome, String dataInserimento, String tipologia, String marca, String taglia, double prezzo, boolean disponibile) {
        this.id = id;
        this.nome = nome;
        this.dataInserimento = dataInserimento;
        this.tipologia = tipologia;
        this.marca = marca;
        this.taglia = taglia;
        this.prezzo = prezzo;
        this.disponibile = disponibile;
    }

    public Capo(int id, String nome) {
    }

    public Capo(int id, String dataInserimento, String tipologia, String marca, String taglia, double prezzo) {
    }

    public String getNome() {
        return nome;
    }

    public int getId() {
        return id;
    }

    public String getDataInserimento() {
        return dataInserimento;
    }

    public String getTipologia() {
        return tipologia;
    }

    public String getMarca() {
        return marca;
    }

    public String getTaglia() {
        return taglia;
    }

    public double getPrezzo() {
        return prezzo;
    }

    public boolean isDisponibile() {
        return disponibile;
    }

    public void prenota(int idUtentePrenotante) {
        if (disponibile) {
            disponibile = false;
            System.out.println("Prenotazione effettuata con successo per il capo con ID: " + id);
        } else {
            System.out.println("Il capo con ID: " + id + " non è disponibile per la prenotazione.");
        }
    }


    public void setDisponibile(boolean disponibile) {
        this.disponibile = disponibile;
    }


}
