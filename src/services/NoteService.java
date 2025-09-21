package services;

import db.DBConnection;
import models.Note;
import models.Tag;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NoteService {

    public void addNote(Note note) {
        String sql = "INSERT INTO notes(title, content, created_date, tag_id) VALUES (?,?,?,?)";
        try (Connection conn = DBConnection.getDBConnection()) {
            assert conn != null;
            try (PreparedStatement statement = conn.prepareStatement(sql)) {
                statement.setString(1, note.getTitle());
                statement.setString(2, note.getContent());
                statement.setTimestamp(3, Timestamp.valueOf(note.getCreatedDate()));
                statement.setInt(4, note.getTag().getId());

                statement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error saving note: " + e.getMessage(), e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Database driver not found: " + e.getMessage(), e);
        }
    }

    public List<Note> getAllNotes() {
        List<Note> notes = new ArrayList<>();
        String sql = "SELECT n.id, n.title, n.content, n.created_date, t.tag_id, t.tag_name " +
                "FROM notes n JOIN tags t ON n.tag_id = t.tag_id";

        try (Connection conn = DBConnection.getDBConnection()) {
            assert conn != null;
            try (Statement statement = conn.createStatement();
                    ResultSet result = statement.executeQuery(sql)) {

                while (result.next()) {
                    Tag tag = new Tag(result.getInt("tag_id"), result.getString("tag_name"));
                    Note note = new Note(
                            result.getInt("id"),
                            result.getString("title"),
                            result.getString("content"),
                            result.getTimestamp("created_date").toLocalDateTime(),
                            tag);
                    notes.add(note);
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException("Error fetching notes: " + e.getMessage(), e);
        }
        return notes;
    }

    public List<Tag> getAllTags() {
        List<Tag> tags = new ArrayList<>();
        String sql = "SELECT tag_id, tag_name FROM tags";

        try (Connection conn = DBConnection.getDBConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                tags.add(new Tag(
                        rs.getInt("tag_id"),
                        rs.getString("tag_name")));
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException("Error fetching tags: " + e.getMessage());
        }
        return tags;
    }
}