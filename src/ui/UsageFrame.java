package ui;

import javax.swing.*;
import java.awt.*;

public class UsageFrame extends JFrame {

    public UsageFrame() {
        setTitle("How to Use");
        setSize(500, 400);
        setLocationRelativeTo(null);
        ImageIcon icon = new ImageIcon("logo.png");
        setIconImage(icon.getImage());

        JTextArea usageText = new JTextArea();
        usageText.setEditable(false);

        usageText.setLineWrap(true);
        usageText.setWrapStyleWord(true);
        usageText.setMargin(new Insets(10, 10, 10, 10));

        StringBuilder sb = new StringBuilder();
        sb.append("How to Use Digital Notes Vault\n\n");
        sb.append("1. Add a Note:\n");
        sb.append("   - Click the 'Add Note' button on the main screen.\n");
        sb.append("   - A new window will open.\n");
        sb.append("   - Enter a title for your note.\n");
        sb.append("   - Write the content of your note in the text area.\n");
        sb.append("   - Select a tag from the dropdown menu.\n");
        sb.append("   - Click the 'Save' button to store your note.\n\n");
        sb.append("2. View Notes:\n");
        sb.append("   - Click the 'View Notes' button on the main screen.\n");
        sb.append("   - A table will appear showing all your saved notes.\n\n");
        sb.append("3. Export Notes:\n");
        sb.append("   - Click the 'Export Notes' button on the main screen.\n");
        sb.append("   - Your notes will be saved to 'notes_backup.txt'.\n");

        usageText.setText(sb.toString());

        JScrollPane scrollPane = new JScrollPane(usageText);
        add(scrollPane);

        setVisible(true);
    }
}
