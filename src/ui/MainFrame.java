package ui;

import javax.swing.*;
import java.awt.*;
import services.NoteService;
import services.FileHandler;

public class MainFrame extends JFrame {
    NoteService noteService = new NoteService();
    FileHandler fileHandler = new FileHandler();

    public MainFrame() {
        setTitle("Digital Notes Vault");
        setSize(500, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        ImageIcon icon = new ImageIcon("logo.png");
        setIconImage(icon.getImage());

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JPanel buttonPanel = new JPanel(new GridLayout(2, 2, 20, 20));

        JButton addBtn = new JButton("Add Note");
        JButton viewBtn = new JButton("View Notes");
        JButton exportBtn = new JButton("Export Notes");
        JButton howToUse = new JButton("How to Use");

        Font buttonFont = new Font("Arial", Font.PLAIN, 14);
        addBtn.setFont(buttonFont);
        viewBtn.setFont(buttonFont);
        exportBtn.setFont(buttonFont);

        buttonPanel.add(addBtn);
        buttonPanel.add(viewBtn);
        buttonPanel.add(exportBtn);
        buttonPanel.add(howToUse);

        add(buttonPanel, gbc);

        addBtn.addActionListener(e -> new AddNoteFrame());
        viewBtn.addActionListener(e -> new NotesTableFrame());
        exportBtn.addActionListener(e -> {
            try {
                fileHandler.exportNotes(noteService.getAllNotes());
                JOptionPane.showMessageDialog(this, "Notes exported successfully to notes_backup.txt.", "Success",
                        JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error exporting notes: " + ex.getMessage(), "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        });
        howToUse.addActionListener(e -> new UsageFrame());

        setVisible(true);
    }
}
