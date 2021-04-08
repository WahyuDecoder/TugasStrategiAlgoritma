package tugas.strategiAlgoritma.towerofhanoi;

/**
 * Class Pergerakan
 * @author WahyuDecoder dan ZulAzizKhan
 */
public class Pergerakan {

    private int ficha;
    private int torreOrigen;
    private int torreDestino;

    public Pergerakan(int ficha, int torreOrigen, int torreDestino) {
        this.ficha = ficha;
        this.torreOrigen = torreOrigen;
        this.torreDestino = torreDestino;
    }

    public int getFicha() {
        return ficha;
    }

    public void setFicha(int ficha) {
        this.ficha = ficha;
    }

    public int getTorreDestino() {
        return torreDestino;
    }

    public void setTorreDestino(int torreDestino) {
        this.torreDestino = torreDestino;
    }

    public int getTorreOrigen() {
        return torreOrigen;
    }

    public void setTorreOrigen(int torreOrigen) {
        this.torreOrigen = torreOrigen;
    }
}
