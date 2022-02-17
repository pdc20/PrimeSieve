import javax.swing.*;
import java.io.FileWriter;

public class Terminal extends JFrame {
    private JTextArea terminalText;
    private JProgressBar terminalProgressBar;
    private JPanel terminalPanel;

    public static void main(String[] args) {
        Terminal t = new Terminal();
        t.setContentPane(t.terminalPanel);
        t.setDefaultCloseOperation(EXIT_ON_CLOSE);
        t.setTitle("Prime Sieve Tracker");
        t.setSize(800,600);
        t.setVisible(true);

        StringBuilder sb = new StringBuilder();
        int cnt=0;
        for (int i = 50000000; i <= 21*50000000; i += 50000000) {
            Sieve s = new Sieve(i);
            long startTime = System.currentTimeMillis();
            s.construct();
            long endTime = System.currentTimeMillis();
            sb.append("").append(i).append(",").append(endTime - startTime).append("\n");
            t.terminalText.append("i = " + i + " done in " + (endTime - startTime) + "ms.\n");
            ++cnt;
            t.terminalProgressBar.setValue((int)Math.round(100.0/21.0 * (double)cnt));
            t.terminalProgressBar.setString(String.format("%.2f %%", 100.0/21.0 * (double)cnt));
        }
        t.terminalText.append("All done! You may now close this window.\nMake sure to view output.csv in the same folder as this program.");

        try (FileWriter fileWriter = new FileWriter("output.csv")) {
            fileWriter.write("n,ms\n");
            fileWriter.write(sb.toString());
            fileWriter.close();
        } catch (Exception e) {
            t.terminalText.append(e.toString());
        }
    }
}
