public class Vendita {
    private int id;
    private int idUtente;
    private int idCapo;
    private double prezzo;
    private boolean venduto;

    public Vendita(int idUtente, int idCapo, double prezzo) {
    }

    public void restituisciCapo() {
        this.venduto = false;

        Capo capo = trovaCapoPerId(this.idCapo);
        if (capo != null) {
            capo.setDisponibile(true);
        }
    }

    private Capo trovaCapoPerId(int idCapo) {
        CapoEsempio capoEsempio = new CapoEsempio();
        for (Capo capo : capoEsempio.getCapi()) {
            if (capo.getId() == idCapo) {
                return capo;
            }
        }
        return null;
    }


    public int getId() {
        int Id = 0;
        return Id;
    }
}
