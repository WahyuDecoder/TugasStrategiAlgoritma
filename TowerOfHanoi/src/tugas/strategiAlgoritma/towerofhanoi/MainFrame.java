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

    private JLabel labelNroDiscos;
    private JLabel labelInformacion;
    private JSpinner spinnerNroDiscos;
    private JButton botonIniciar;
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

        labelNroDiscos = new JLabel("JUMLAH DARI PIRINGAN");
        panelInferior.add(labelNroDiscos);

        spinnerNroDiscos = new JSpinner(new SpinnerNumberModel(8, 1, 8, 1));
        spinnerNroDiscos.addChangeListener(this);
        panelInferior.add(spinnerNroDiscos);

        botonIniciar = new JButton("Start");
        botonIniciar.addActionListener(this);
        panelInferior.add(botonIniciar);

        labelInformacion = new JLabel("PROGRAM SELESAI!");
        labelInformacion.setForeground(Color.red);
        labelInformacion.setVisible(false);
        panelInferior.add(labelInformacion);

        add(panelInferior, BorderLayout.SOUTH);
        gambar = new Gambar(8, this);
        add(gambar, BorderLayout.CENTER);


    }

    public void actionPerformed(ActionEvent e) {
        if (botonIniciar.getText().equals("Pause")) {
            gambar.pausarAnimacion();
            botonIniciar.setText("Continue");
        } else {
            if (botonIniciar.getText().equals("Start again")) {
                gambar = new Gambar(Integer.parseInt(spinnerNroDiscos.getValue().toString()), this);
                add(gambar, BorderLayout.CENTER);
                botonIniciar.setText("Start");
                labelInformacion.setVisible(false);
                this.setVisible(true);
            } else {
                gambar.iniciarAnimacion();
                botonIniciar.setText("Pause");
            }
        }
    }

    public void stateChanged(ChangeEvent e) {
        gambar.pausarAnimacion();
        botonIniciar.setText("Start");
        labelInformacion.setVisible(false);
        gambar = new Gambar(Integer.parseInt(spinnerNroDiscos.getValue().toString()), this);
        add(gambar, BorderLayout.CENTER);
        this.setVisible(true);
    }

    public void resolucionCompletada() {
        botonIniciar.setText("Start again");
        labelInformacion.setVisible(true);
    }
}
