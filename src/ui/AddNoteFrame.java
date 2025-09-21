package ui;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import models.Note;
import models.Tag;
import services.NoteService;

public class AddNoteFrame extends JFrame {
    private final JTextField titleField;
    private final JTextArea contentArea;
    private final JComboBox<Tag> tagBox;

    public AddNoteFrame() {
        setTitle("Add Note");
        setSize(500, 600);
        setLocationRelativeTo(null);
        ImageIcon icon = new ImageIcon("logo.png");
        setIconImage(icon.getImage());

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        titleField = new JTextField(20);
        contentArea = new JTextArea(10, 20);
        contentArea.setLineWrap(true);
        contentArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(contentArea);

        NoteService noteService = new NoteService();
        tagBox = new JComboBox<>();
        List<Tag> tags = noteService.getAllTags();
        tagBox.setModel(new DefaultComboBoxModel<>(tags.toArray(new Tag[0])));

        JButton saveBtn = new JButton("Save");
        saveBtn.setFont(new Font("Arial", Font.BOLD, 14));

        gbc.gridx = 0;
        gbc.gridy = 0;
        add(new JLabel("Title:"), gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        add(titleField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        add(new JLabel("Content:"), gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        add(scrollPane, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        add(new JLabel("Tag:"), gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        add(tagBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.insets = new Insets(20, 5, 5, 5);
        add(saveBtn, gbc);

        saveBtn.addActionListener(e -> {
            if (titleField.getText().trim().isEmpty() || contentArea.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Title and Content cannot be empty!", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            String title = titleField.getText();
            String content = contentArea.getText();
            Tag tag = (Tag) tagBox.getSelectedItem();
            Note note = new Note(title, content, tag);

            try {
                new NoteService().addNote(note);
                JOptionPane.showMessageDialog(this, "Note Saved Successfully!", "Success",
                        JOptionPane.INFORMATION_MESSAGE);
                dispose();
                new NotesTableFrame(); // Refresh the notes view
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error saving note: " + ex.getMessage(), "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        });

        setVisible(true);
    }
}
