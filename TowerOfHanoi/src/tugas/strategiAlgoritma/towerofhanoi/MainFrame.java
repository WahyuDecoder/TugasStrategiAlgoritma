package tugas.strategiAlgoritma.towerofhanoi;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.WindowConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * Clase MainFrame
 * @author WahyuDecoder dan ZulAzizKhan
 */
public class MainFrame extends JFrame implements ActionListener, ChangeListener {

    private JLabel labelDisc;
    private JLabel labelInformation;
    private JSpinner spinnerDisc;
    private JButton tombolMulai;
    private Gambar gambar;

    public MainFrame() {
        super("Tower of Hanoi | Tugas Strategi Algoritma");
        configurarVentana();
        inicializarComponentes();
        this.setVisible(true);
    }

    private void configurarVentana() {
        this.setSize(680, 400);
        this.setVisible(true);
        this.setLayout(new BorderLayout());
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    private void inicializarComponentes() {

        JPanel panelInferior = new JPanel();

        labelDisc = new JLabel("JUMLAH DARI PIRINGAN");
        panelInferior.add(labelDisc);

        spinnerDisc = new JSpinner(new SpinnerNumberModel(8, 1, 8, 1));
        spinnerDisc.addChangeListener(this);
        panelInferior.add(spinnerDisc);

        tombolMulai = new JButton("Start");
        tombolMulai.addActionListener(this);
        panelInferior.add(tombolMulai);

        labelInformation = new JLabel("PROGRAM SELESAI!");
        labelInformation.setForeground(Color.red);
        labelInformation.setVisible(false);
        panelInferior.add(labelInformation);

        add(panelInferior, BorderLayout.SOUTH);
        gambar = new Gambar(8, this);
        add(gambar, BorderLayout.CENTER);


    }

    public void actionPerformed(ActionEvent e) {
        if (tombolMulai.getText().equals("Pause")) {
            gambar.pauseAnimacion();
            tombolMulai.setText("Continue");
        } else {
            if (tombolMulai.getText().equals("Start again")) {
                gambar = new Gambar(Integer.parseInt(spinnerDisc.getValue().toString()), this);
                add(gambar, BorderLayout.CENTER);
                tombolMulai.setText("Start");
                labelInformation.setVisible(false);
                this.setVisible(true);
            } else {
                gambar.iniciarAnimacion();
                tombolMulai.setText("Pause");
            }
        }
    }

    public void stateChanged(ChangeEvent e) {
        gambar.pauseAnimacion();
        tombolMulai.setText("Start");
        labelInformation.setVisible(false);
        gambar = new Gambar(Integer.parseInt(spinnerDisc.getValue().toString()), this);
        add(gambar, BorderLayout.CENTER);
        this.setVisible(true);
    }

    public void resolucionCompletada() {
        tombolMulai.setText("Start again");
        labelInformation.setVisible(true);
    }
}
