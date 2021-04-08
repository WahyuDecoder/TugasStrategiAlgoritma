package tugas.strategiAlgoritma.towerofhanoi;

/**
 * Class Pergerakan
 * @author WahyuDecoder dan ZulAzizKhan
 */
public class Pergerakan {

    private int jejak;
    private int menaraAsal;
    private int menaraTujuan;

    public Pergerakan(int jejak, int menaraAsal, int menaraTujuan) {
        this.jejak = jejak;
        this.menaraAsal = menaraAsal;
        this.menaraTujuan = menaraTujuan;
    }

    public int getJejak() {
        return jejak;
    }

    public void setJejak(int jejak) {
        this.jejak = jejak;
    }

    public int getMenaraTujuan() {
        return menaraTujuan;
    }

    public void setMenaraTujuan(int menaraTujuan) {
        this.menaraTujuan = menaraTujuan;
    }

    public int getMenaraAsal() {
        return menaraAsal;
    }

    public void setMenaraAsal(int menaraAsal) {
        this.menaraAsal = menaraAsal;
    }
}
