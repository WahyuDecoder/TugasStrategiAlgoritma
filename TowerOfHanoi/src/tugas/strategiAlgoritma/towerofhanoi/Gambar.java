package tugas.strategiAlgoritma.towerofhanoi;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 * Class Gambar
 * @author WahyuDecoder dan ZulAzizKhan
 */
public class Gambar extends JPanel implements ActionListener {

    private int noJejaks;
    private int[] berhentis;
    private int pergerakanActual;
    private Image[] jejaks;
    private int x, y;
    private int jejak;
    private int nm;
    private Pergerakan[] pergerakans;
    private Posisi[] posisis;
    private Timer timer;
    private boolean pergerakanSelesai;
    private int langkah;
    private static final int VELOCIDAD = 1;
    private static final int LIMITE_JEJAKS = 8;
    private static final int LIMITE_MENARA = 3;
    private MainFrame inti;

    public Gambar(int noJejaks, MainFrame inti) {
        this.noJejaks = noJejaks;
        this.inti = inti;
        configurarPanel();
        inicializarComponentes();
        inicializarComponentesDeAnimacion();
        timer = new Timer(VELOCIDAD, this);
    }

    private void configurarPanel() {
        setDoubleBuffered(true);
        setBackground(Color.black);
    }

    private void inicializarComponentes() {
        jejaks = new Image[LIMITE_JEJAKS + 1];
        for (int i = 1; i <= LIMITE_JEJAKS; i++) {
            ImageIcon ii = new ImageIcon(this.getClass().getResource("/tugas/strategiAlgoritma/towerofhanoi/images/" + i + ".png"));
            jejaks[i] = ii.getImage();
        }
    }

    private void inicializarComponentesDeAnimacion() {
        nm = 0;
        berhentis = new int[LIMITE_MENARA + 1];
        berhentis[1] = noJejaks;
        berhentis[2] = 0;
        berhentis[3] = 0;
        jejak = 1;
        pergerakans = new Pergerakan[(int) Math.pow(2, noJejaks)];
        algoritmaHanoi(noJejaks, 1, 2, 3);
        posisis = new Posisi[9];
        for (int i = 1; i <= noJejaks; i++) {
            int w = noJejaks - i + 1;
            posisis[i] = new Posisi(posisionXJejak(i, 1), posisionYJejak(w));
        }
        x = posisis[1].getX();
        y = posisis[1].getY();
        pergerakanActual = 1;
        pergerakanSelesai = false;
        langkah = 1;
    }

    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;
        for (int i = noJejaks; i >= 1; i--) {
            g2.drawImage(jejaks[i], posisis[i].getX(), posisis[i].getY(), this);
        }
        g2.drawString("MENARA 1", 115, 320);
        g2.drawString("MENARA 2", 315, 320);
        g2.drawString("MENARA 3", 515, 320);
        Toolkit.getDefaultToolkit().sync();
        g.dispose();
    }

    public void algoritmaHanoi(int n, int asal, int sementara, int tujuan) {
        if (n == 0) {
            return;
        }
        algoritmaHanoi(n - 1, asal, tujuan, sementara);
        nm++;
        pergerakans[nm] = new Pergerakan(n, asal, tujuan);
        algoritmaHanoi(n - 1, sementara, asal, tujuan);
    }

    public void actionPerformed(ActionEvent e) {
        switch (langkah) {
            case 1:
                if (y > 30) { // 30 adalah maksimum untuk menaikkan chip
                    y--;
                    posisis[jejak].setY(y);
                } else {
                    if (pergerakans[pergerakanActual].getMenaraAsal() < pergerakans[pergerakanActual].getMenaraTujuan()) {
                        langkah = 2;
                    } else {
                        langkah = 3;
                    }
                }
                break;
            case 2:
                if (x < posisionXJejak(jejak, pergerakans[pergerakanActual].getMenaraTujuan())) {
                    x++;
                    posisis[jejak].setX(x);
                } else {
                    langkah = 4;
                }
                break;
            case 3:
                if (x > posisionXJejak(jejak, pergerakans[pergerakanActual].getMenaraTujuan())) {
                    x--;
                    posisis[jejak].setX(x);
                } else {
                    langkah = 4;
                }
                break;
            case 4:
                int nivel = berhentis[pergerakans[pergerakanActual].getMenaraTujuan()] + 1;
                if (y < posisionYJejak(nivel)) {
                    y++;
                    posisis[jejak].setY(y);
                } else {
                    pergerakanSelesai = true;
                }
                break;
        }
        if (pergerakanSelesai) {
            langkah = 1;
            berhentis[pergerakans[pergerakanActual].getMenaraTujuan()]++;
            berhentis[pergerakans[pergerakanActual].getMenaraAsal()]--;
            pergerakanActual++;
            if (pergerakanActual == (int) Math.pow(2, noJejaks)) {
                timer.stop();
                inti.resolucionCompletada();
            } else {
                pergerakanSelesai = false;
                jejak = pergerakans[pergerakanActual].getJejak();
                x = posisis[jejak].getX();
                y = posisis[jejak].getY();
            }
        }
        repaint();
    }

    public static int posisionXJejak(int jejak, int menara) {
        int k = (menara - 1) * 200;
        switch (jejak) {
            case 1:
                return 110 + k;
            case 2:
                return 100 + k;
            case 3:
                return 90 + k;
            case 4:
                return 80 + k;
            case 5:
                return 70 + k;
            case 6:
                return 60 + k;
            case 7:
                return 50 + k;
            case 8:
                return 40 + k;
        }
        return 0;
    }

    public static int posisionYJejak(int nivel) {
        switch (nivel) {
            case 1:
                return 260;
            case 2:
                return 233;
            case 3:
                return 206;
            case 4:
                return 179;
            case 5:
                return 152;
            case 6:
                return 125;
            case 7:
                return 98;
            case 8:
                return 71;
        }
        return 0;
    }

    public void iniciarAnimacion() {
        timer.restart();
    }

    public void pauseAnimacion() {
        timer.stop();
    }
}
