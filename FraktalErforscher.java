import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FraktalErforscher extends JFrame {
  private int breite = 800;
  private int höhe = 800;
  private double zoom = 200;
  private double verschiebungX = 0;
  private double verschiebungY = 0;
  private int maximaleIterationen = 500;
  private boolean istMandelbrot = true;

  private double juliaRe = -0.7;
  private double juliaIm = 0.27015;

  private JTextField zoomFeld, verschiebungXFeld, verschiebungYFeld, iterationenFeld, juliaReFeld, juliaImFeld;
  private FraktalPanel fraktalPanel;

  public FraktalErforscher() {
    super("Fraktal Erforscher");

    // Layout für die Parameter-Eingabe
    JPanel steuerungsPanel = new JPanel();
    steuerungsPanel.setLayout(new GridLayout(7, 2));

    // Textfelder für die Mandelbrot/Julia-Parameter
    zoomFeld = new JTextField(String.valueOf(zoom));
    verschiebungXFeld = new JTextField(String.valueOf(verschiebungX));
    verschiebungYFeld = new JTextField(String.valueOf(verschiebungY));
    iterationenFeld = new JTextField(String.valueOf(maximaleIterationen));
    juliaReFeld = new JTextField(String.valueOf(juliaRe));
    juliaImFeld = new JTextField(String.valueOf(juliaIm));

    // Hinzufügen von Labels und Textfeldern
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

    // Button zum Wechseln zwischen Mandelbrot und Julia
    JButton umschaltKnopf = new JButton("Zu Julia wechseln");
    umschaltKnopf.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        istMandelbrot = !istMandelbrot;
        umschaltKnopf.setText(istMandelbrot ? "Zu Julia wechseln" : "Zu Mandelbrot wechseln");
        fraktalAktualisieren();
      }
    });

    // Button zum Neuzeichnen
    JButton neuZeichnenKnopf = new JButton("Neu zeichnen");
    neuZeichnenKnopf.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        fraktalAktualisieren();
      }
    });

    // Layout für das Fraktal-Anzeigefeld
    fraktalPanel = new FraktalPanel();

    // Layout für das gesamte Fenster
    this.setLayout(new BorderLayout());
    this.add(steuerungsPanel, BorderLayout.NORTH);
    this.add(fraktalPanel, BorderLayout.CENTER);

    JPanel knopfPanel = new JPanel();
    knopfPanel.add(umschaltKnopf);
    knopfPanel.add(neuZeichnenKnopf);
    this.add(knopfPanel, BorderLayout.SOUTH);

    this.setSize(breite, höhe);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setVisible(true);
  }

  private void fraktalAktualisieren() {
    try {
      // Parameter aus Textfeldern holen
      zoom = Double.parseDouble(zoomFeld.getText());
      verschiebungX = Double.parseDouble(verschiebungXFeld.getText());
      verschiebungY = Double.parseDouble(verschiebungYFeld.getText());
      maximaleIterationen = Integer.parseInt(iterationenFeld.getText());
      juliaRe = Double.parseDouble(juliaReFeld.getText());
      juliaIm = Double.parseDouble(juliaImFeld.getText());

      // Fraktal neu zeichnen
      fraktalPanel.repaint();
    } catch (NumberFormatException e) {
      JOptionPane.showMessageDialog(this, "Bitte gültige Zahlen eingeben", "Fehler", JOptionPane.ERROR_MESSAGE);
    }
  }

  public static void main(String[] args) {
    new FraktalErforscher();
  }

  // Panel zum Zeichnen der Fraktale
  class FraktalPanel extends JPanel {
    @Override
    protected void paintComponent(Graphics g) {
      super.paintComponent(g);
      Graphics2D g2d = (Graphics2D) g;

      for (int x = 0; x < getWidth(); x++) {
        for (int y = 0; y < getHeight(); y++) {
          // Berechne die komplexe Zahl
          double zx = (x - breite / 2) / zoom + verschiebungX;
          double zy = (y - höhe / 2) / zoom + verschiebungY;

          int iteration = 0;
          if (istMandelbrot) {
            double cX = zx;
            double cY = zy;
            while (zx * zx + zy * zy < 4 && iteration < maximaleIterationen) {
              double tmp = zx * zx - zy * zy + cX;
              zy = 2.0 * zx * zy + cY;
              zx = tmp;
              iteration++;
            }
          } else {
            double cX = juliaRe;
            double cY = juliaIm;
            while (zx * zx + zy * zy < 4 && iteration < maximaleIterationen) {
              double tmp = zx * zx - zy * zy + cX;
              zy = 2.0 * zx * zy + cY;
              zx = tmp;
              iteration++;
            }
          }

          // Farbe je nach Iterationsanzahl setzen
          if (iteration < maximaleIterationen) {
            g2d.setColor(new Color(iteration % 256, iteration % 128, iteration % 64));
          } else {
            g2d.setColor(Color.BLACK);
          }
          g2d.drawLine(x, y, x, y);
        }
      }
    }
  }
}
