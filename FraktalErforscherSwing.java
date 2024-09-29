import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FraktalErforscherSwing extends JFrame {

    private JTextField zoomFeld, verschiebungXFeld, verschiebungYFeld, iterationenFeld, juliaReFeld, juliaImFeld;
    private JPanel fraktalPanel;
    private boolean istJulia = false;
    private double zoom = 200;
    private double verschiebungX = 0;
    private double verschiebungY = 0;
    private int maximaleIterationen = 500;
    private double juliaRe = -0.4;
    private double juliaIm = 0.6;

    public FraktalErforscherSwing() {
        setTitle("Fraktal Erforscher");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Benutzeroberfläche erstellen
        JPanel steuerungsPanel = new JPanel();
        steuerungsPanel.setLayout(new GridLayout(6, 2, 10, 10));

        // Parameterfelder
        zoomFeld = new JTextField(String.valueOf(zoom));
        verschiebungXFeld = new JTextField(String.valueOf(verschiebungX));
        verschiebungYFeld = new JTextField(String.valueOf(verschiebungY));
        iterationenFeld = new JTextField(String.valueOf(maximaleIterationen));
        juliaReFeld = new JTextField(String.valueOf(juliaRe));
        juliaImFeld = new JTextField(String.valueOf(juliaIm));

        steuerungsPanel.add(new JLabel("Zoom:"));
        steuerungsPanel.add(zoomFeld);
        steuerungsPanel.add(new JLabel("Verschiebung X:"));
        steuerungsPanel.add(verschiebungXFeld);
        steuerungsPanel.add(new JLabel("Verschiebung Y:"));
        steuerungsPanel.add(verschiebungYFeld);
        steuerungsPanel.add(new JLabel("Iterationen:"));
        steuerungsPanel.add(iterationenFeld);
        steuerungsPanel.add(new JLabel("Julia Real:"));
        steuerungsPanel.add(juliaReFeld);
        steuerungsPanel.add(new JLabel("Julia Imaginär:"));
        steuerungsPanel.add(juliaImFeld);

        JButton neuZeichnenKnopf = new JButton("Neu zeichnen");
        neuZeichnenKnopf.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                aktualisiereParameter();
                fraktalPanel.repaint();
            }
        });

        JButton umschaltKnopf = new JButton("Zu Julia wechseln");
        umschaltKnopf.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                istJulia = !istJulia;
                umschaltKnopf.setText(istJulia ? "Zu Mandelbrot wechseln" : "Zu Julia wechseln");
                fraktalPanel.repaint();
            }
        });

        // Fraktalpanel erstellen
        fraktalPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                zeichneFraktal(g);
            }
        };

        // Layout einrichten
        setLayout(new BorderLayout());
        add(steuerungsPanel, BorderLayout.NORTH);
        add(fraktalPanel, BorderLayout.CENTER);
        add(neuZeichnenKnopf, BorderLayout.SOUTH);
        add(umschaltKnopf, BorderLayout.WEST);
    }

    private void zeichneFraktal(Graphics g) {
        int breite = fraktalPanel.getWidth();
        int hoehe = fraktalPanel.getHeight();

        for (int x = 0; x < breite; x++) {
            for (int y = 0; y < hoehe; y++) {
                double zx, zy, cX, cY;
                zx = zy = 0;

                // Koordinaten in die komplexe Ebene umwandeln
                double real = (x - breite / 2.0 + verschiebungX) / zoom;
                double imag = (y - hoehe / 2.0 + verschiebungY) / zoom;

                if (istJulia) {
                    cX = juliaRe;
                    cY = juliaIm;
                    zx = real;
                    zy = imag;
                } else {
                    cX = real;
                    cY = imag;
                }

                int iter;
                for (iter = 0; iter < maximaleIterationen; iter++) {
                    double zxQuadrat = zx * zx;
                    double zyQuadrat = zy * zy;
                    if (zxQuadrat + zyQuadrat > 4.0) {
                        break;
                    }
                    double temp = zxQuadrat - zyQuadrat + cX;
                    zy = 2.0 * zx * zy + cY;
                    zx = temp;
                }

                // Farbgebung basierend auf Iterationen
                if (iter < maximaleIterationen) {
                    g.setColor(new Color(iter % 256, iter % 128, iter % 64));
                } else {
                    g.setColor(Color.BLACK);
                }
                g.drawRect(x, y, 1, 1);
            }
        }
    }

    private void aktualisiereParameter() {
        try {
            zoom = Double.parseDouble(zoomFeld.getText());
            verschiebungX = Double.parseDouble(verschiebungXFeld.getText());
            verschiebungY = Double.parseDouble(verschiebungYFeld.getText());
            maximaleIterationen = Integer.parseInt(iterationenFeld.getText());
            juliaRe = Double.parseDouble(juliaReFeld.getText());
            juliaIm = Double.parseDouble(juliaImFeld.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Bitte gültige Werte eingeben", "Eingabefehler", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            FraktalErforscherSwing fenster = new FraktalErforscherSwing();
            fenster.setVisible(true);
        });
    }
}

