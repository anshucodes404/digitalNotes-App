package services;

import java.io.*;
import java.util.List;
import models.Note;

public class FileHandler {

    public void exportNotes(List<Note> notes) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("notes_backup.txt"))) {
            for (Note note : notes) {
                bw.write(note.getTitle() + "|" + note.getContent() + "|" + note.getCreatedDate() + "|"
                        + note.getTag().toString());
                bw.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to export notes to file.", e);
        }
    }
}