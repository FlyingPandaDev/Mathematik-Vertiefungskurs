import java.awt.*;
import java.awt.event.*;

public class mandel extends Frame {

  public static void main(String arguments[]) {
    mandel proggi = new mandel();

    WindowListener wl = new WindowAdapter() {
      public void windowClosing(WindowEvent e) {
        System.exit(0);
      }
    };
    proggi.addWindowListener(wl);
    proggi.setLocation(100, 100);
    proggi.setSize(300, 300);
    proggi.show();
  }

  mandel() {
    super("Mandelbrotmenge");
  }

  public void paint(Graphics bs) {
    double amin;
    double bmin;
    double kante;
    double ds;
    double a;
    double b;
    double x;
    double y;
    double xx;
    double yy;
    int s;
    int r;
    int zaehler;

    amin = -1.5;
    bmin = -1;
    kante = 2;

    ds = kante / 200;

    a = amin;

    for (s = 0; s <= 200; s++) {
      b = bmin;
      for (r = 0; r <= 200; r++) {
        x = 0;
        y = 0;
        zaehler = 0;
        while ((zaehler < 100) && (Math.sqrt(x * x + y * y) < 2)) {
          zaehler++;
          xx = x * x - y * y + a;
          y = 2 * x * y + b;
          x = xx;
        }
        if (zaehler == 100) {
          bs.setColor(Color.black);
          bs.drawLine(s + 50, r + 50, s + 50, r + 50);
        }

        if (zaehler < 10) {
          bs.setColor(Color.red);
          bs.drawLine(s + 50, r + 50, s + 50, r + 50);
        } else if (zaehler < 20) {
          bs.setColor(Color.green);
          bs.drawLine(s + 50, r + 50, s + 50, r + 50);
        } else if (zaehler < 30) {
          bs.setColor(Color.lightGray);
          bs.drawLine(s + 50, r + 50, s + 50, r + 50);
        } else if (zaehler < 40) {
          bs.setColor(Color.blue);
          bs.drawLine(s + 50, r + 50, s + 50, r + 50);
        } else if (zaehler < 50) {
          bs.setColor(Color.yellow);
          bs.drawLine(s + 50, r + 50, s + 50, r + 50);
        } else if (zaehler < 60) {
          bs.setColor(Color.magenta);
          bs.drawLine(s + 50, r + 50, s + 50, r + 50);
        } else if (zaehler < 70) {
          bs.setColor(Color.gray);
          bs.drawLine(s + 50, r + 50, s + 50, r + 50);
        } else if (zaehler < 80) {
          bs.setColor(Color.orange);
          bs.drawLine(s + 50, r + 50, s + 50, r + 50);
        } else if (zaehler < 90) {
          bs.setColor(Color.white);
          bs.drawLine(s + 50, r + 50, s + 50, r + 50);
        } else if (zaehler < 100) {
          bs.setColor(Color.cyan);
          bs.drawLine(s + 50, r + 50, s + 50, r + 50);
        }
        b = b + ds;
      }
      a = a + ds;
    }
  }
}
