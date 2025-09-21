package ui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import models.Note;
import services.NoteService;

import java.util.List;

public class NotesTableFrame extends JFrame {
    public NotesTableFrame() {
        setTitle("All Notes");
        setSize(600, 400);
        ImageIcon icon = new ImageIcon("logo.png");
        setIconImage(icon.getImage());
        setLocationRelativeTo(null);

        String[] columns = {"ID", "Title", "Content", "Date", "Tag"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);

        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane);

        List<Note> notes = new NoteService().getAllNotes();
        for (Note note : notes) {
            model.addRow(new Object[]{
                    note.getId(),
                    note.getTitle(),
                    note.getContent(),
                    note.getCreatedDate(),
                    note.getTag().getName(),
            });
        }

        setVisible(true);
    }
}
