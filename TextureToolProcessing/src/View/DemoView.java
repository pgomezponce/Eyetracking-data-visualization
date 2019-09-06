package View;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class DemoView extends JFrame {

    ImageIcon leftImage;

    public DemoView(String title) throws HeadlessException {
        super(title);

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        int statusFile = jfc.showDialog(this, "Choose texture");
        if (statusFile == JFileChooser.APPROVE_OPTION)
        {
            File f = jfc.getSelectedFile();

            this.leftImage = new ImageIcon(f.getAbsolutePath());

            JLabel jleft = new JLabel(leftImage);
        }

        //this.leftImage = new ImageIcon

        GridLayout gridLayout = new GridLayout();
        gridLayout.setColumns(2);
        gridLayout.setRows(1);

        JPanel left = new JPanel();

        left.add(jleft);

        this.getContentPane().setLayout(gridLayout);
    }
}
