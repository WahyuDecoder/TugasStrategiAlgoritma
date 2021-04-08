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

    private int nroFichas;
    private int[] topes;
    private int pergerakanActual;
    private Image[] fichas;
    private int x, y;
    private int ficha;
    private int nm;
    private Pergerakan[] pergerakans;
    private Posisi[] posisis;
    private Timer timer;
    private boolean pergerakanCompletado;
    private int paso;
    private static final int VELOCIDAD = 1;
    private static final int LIMITE_FICHAS = 8;
    private static final int LIMITE_TORRES = 3;
    private MainFrame nucleo;

    public Gambar(int nroFichas, MainFrame nucleo) {
        this.nroFichas = nroFichas;
        this.nucleo = nucleo;
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
        fichas = new Image[LIMITE_FICHAS + 1];
        for (int i = 1; i <= LIMITE_FICHAS; i++) {
            ImageIcon ii = new ImageIcon(this.getClass().getResource("/tugas/strategiAlgoritma/towerofhanoi/images/" + i + ".png"));
            fichas[i] = ii.getImage();
        }
    }

    private void inicializarComponentesDeAnimacion() {
        nm = 0;
        topes = new int[LIMITE_TORRES + 1];
        topes[1] = nroFichas;
        topes[2] = 0;
        topes[3] = 0;
        ficha = 1;
        pergerakans = new Pergerakan[(int) Math.pow(2, nroFichas)];
        algoritmoHanoi(nroFichas, 1, 2, 3);
        posisis = new Posisi[9];
        for (int i = 1; i <= nroFichas; i++) {
            int w = nroFichas - i + 1;
            posisis[i] = new Posisi(posisionXFicha(i, 1), posisionYFicha(w));
        }
        x = posisis[1].getX();
        y = posisis[1].getY();
        pergerakanActual = 1;
        pergerakanCompletado = false;
        paso = 1;
    }

    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;
        for (int i = nroFichas; i >= 1; i--) {
            g2.drawImage(fichas[i], posisis[i].getX(), posisis[i].getY(), this);
        }
        g2.drawString("MENARA 1", 115, 320);
        g2.drawString("MENARA 2", 315, 320);
        g2.drawString("MENARA 3", 515, 320);
        Toolkit.getDefaultToolkit().sync();
        g.dispose();
    }

    public void algoritmoHanoi(int n, int origen, int temporal, int destino) {
        if (n == 0) {
            return;
        }
        algoritmoHanoi(n - 1, origen, destino, temporal);
        nm++;
        pergerakans[nm] = new Pergerakan(n, origen, destino);
        algoritmoHanoi(n - 1, temporal, origen, destino);
    }

    public void actionPerformed(ActionEvent e) {
        switch (paso) {
            case 1:
                if (y > 30) { // 30 adalah maksimum untuk menaikkan chip
                    y--;
                    posisis[ficha].setY(y);
                } else {
                    if (pergerakans[pergerakanActual].getTorreOrigen() < pergerakans[pergerakanActual].getTorreDestino()) {
                        paso = 2;
                    } else {
                        paso = 3;
                    }
                }
                break;
            case 2:
                if (x < posisionXFicha(ficha, pergerakans[pergerakanActual].getTorreDestino())) {
                    x++;
                    posisis[ficha].setX(x);
                } else {
                    paso = 4;
                }
                break;
            case 3:
                if (x > posisionXFicha(ficha, pergerakans[pergerakanActual].getTorreDestino())) {
                    x--;
                    posisis[ficha].setX(x);
                } else {
                    paso = 4;
                }
                break;
            case 4:
                int nivel = topes[pergerakans[pergerakanActual].getTorreDestino()] + 1;
                if (y < posisionYFicha(nivel)) {
                    y++;
                    posisis[ficha].setY(y);
                } else {
                    pergerakanCompletado = true;
                }
                break;
        }
        if (pergerakanCompletado) {
            paso = 1;
            topes[pergerakans[pergerakanActual].getTorreDestino()]++;
            topes[pergerakans[pergerakanActual].getTorreOrigen()]--;
            pergerakanActual++;
            if (pergerakanActual == (int) Math.pow(2, nroFichas)) {
                timer.stop();
                nucleo.resolucionCompletada();
            } else {
                pergerakanCompletado = false;
                ficha = pergerakans[pergerakanActual].getFicha();
                x = posisis[ficha].getX();
                y = posisis[ficha].getY();
            }
        }
        repaint();
    }

    public static int posisionXFicha(int ficha, int torre) {
        int k = (torre - 1) * 200;
        switch (ficha) {
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

    public static int posisionYFicha(int nivel) {
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

    public void pausarAnimacion() {
        timer.stop();
    }
}
