import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {

        JFrame jFrame = new JFrame("ShutDown Scheduler (Seconds)");
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jFrame.setSize(400, 200);
        jFrame.setVisible(true);
        jFrame.setLayout(null);
        jFrame.getContentPane().setBackground(new Color(23, 23, 23));
        JTextField jTextField = new JTextField("0");
        jFrame.add(jTextField);
        jTextField.setBounds(150, 50, 100, 30);
        jTextField.setBackground(new Color(23, 23, 23));
        jTextField.setForeground(Color.WHITE);
        jTextField.setFont(new Font("Arial", Font.BOLD, 15));
        JButton enterButton = new JButton("Start");
        jFrame.add(enterButton);
        JButton cancelShutdown = new JButton("Cancel");
        jFrame.add(cancelShutdown);
        cancelShutdown.setBounds(100, 100, 100, 50);
        enterButton.setBounds(0, 100, 100, 50);
        String os = System.getProperty("os.name").toLowerCase();
        cancelShutdown.addActionListener(e ->  {
            try {
                if(os.contains("win")) {
                    Runtime.getRuntime().exec("shutdown -a");
                    JOptionPane.showMessageDialog(jFrame, "Cancelled the shutdown for Windows.");
                } else if (os.contains("nix") || os.contains("nux")) {
                    Runtime.getRuntime().exec("shutdown -c");
                    JOptionPane.showMessageDialog(jFrame, "Cancelled the shutdown for Linux");

                }
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        });
        enterButton.addActionListener(e -> {
            try {
                int seconds = Integer.parseInt(jTextField.getText());
                if (seconds > 0) {
                    if(os.contains("win")) {
                        Runtime.getRuntime().exec("shutdown -s -t " + seconds);
                        JOptionPane.showMessageDialog(jFrame, "Shutting down your PC in " + seconds +" seconds." + " (Windows)");
                    } else if (os.contains("nux") || os.contains("nix")) {
                        Runtime.getRuntime().exec("shutdown +" + seconds);
                        JOptionPane.showMessageDialog(jFrame, "Shutting down your PC in " + seconds +" seconds" + " (Linux Based Distro)");
                    }
                    JOptionPane.showMessageDialog(jFrame, "Shutdown scheduled in " + seconds + " seconds.");
                } else {
                    JOptionPane.showMessageDialog(jFrame, "Please enter a valid number greater than 0.");
                }
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        });
    }
}
