package sio.la2028.model;

public class Epreuve_Athlete {

    private int id;
    private Athlete athlete;
    private Epreuve epreuve;
    private int position;

    public Epreuve_Athlete(int id, Athlete athlete, Epreuve epreuve) {
        this.id = id;
        this.athlete = athlete;
        this.epreuve = epreuve;
        this.position = position;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Athlete getAthlete() {
        return athlete;
    }

    public void setAthlete(Athlete athlete) {
        this.athlete = athlete;
    }

    public Epreuve getEpreuve() {
        return epreuve;
    }

    public void setEpreuve(Epreuve epreuve) {
        this.epreuve = epreuve;
    }

    public int getPosition() { return position; }
    public void setPosition(int position) { this.position = position; }

}
