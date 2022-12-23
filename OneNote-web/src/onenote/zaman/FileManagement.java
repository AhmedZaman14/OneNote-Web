package onenote.zaman;
import java.io.BufferedWriter;
import java.io.File;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;




public class FileManagement {



    private int getNotebookID(int user_id) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3307/onenote", "root", "");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select NotebookID from NoteBook where user_id = '" + user_id + "'");
            rs.next();
            return rs.getInt(1);

        } catch (Exception e) {
            e.getMessage();
        }
        return -1;
    }


    private int getSectionID(int notebookID) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3307/onenote", "root", "");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select SectionID from Section where NoteBookId = '" + notebookID + "' and SectionName = 'Education/Work'");
            rs.next();
            return rs.getInt(1);


        } catch (Exception e) {
            e.getMessage();
        }
        return -1;
    }

    private int getPageId(int sectionID, String pageTitle) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3307/onenote", "root", "");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select page_id from Page where  pageTitle= '" + pageTitle + "'  AND (SectionId = '" + sectionID + "' or SectionId = '" + (sectionID + 1) + "' or SectionId = '" + (sectionID + 2) + "')");
            if (rs.next()) {
                return rs.getInt(1);

            } else {

                return -1;
            }
        } catch (Exception e) {
            e.getMessage();
        }
        return -1;
    }

    private int getNoteID(int pageID) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3307/onenote", "root", "");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select NoteID from Note where page_id = '" + pageID + "' ");
            if (rs.next()) {
                return rs.getInt(1);

            } else {

                return -1;
            }
        } catch (Exception e) {
            e.getMessage();
        }
        return -1;
    }

    private void deltePage(int pageID, String PageTitle) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3307/onenote", "root", "");
            Statement stmt = con.createStatement();

            stmt.executeUpdate("delete from Page where page_id = '" + pageID + "' and PageTitle= '" + PageTitle + "' ");
        } catch (Exception e) {
            e.getMessage();
        }
    }

    private void deleteNoteCategory(int noteID) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3307/onenote", "root", "");
            Statement stmt = con.createStatement();

            stmt.executeUpdate("delete from Category where note_id = '" + noteID + "'");
        } catch (Exception e) {
            e.getMessage();
        }
    }

    private void deleteNoteLayout(int noteID) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3307/onenote", "root", "");
            Statement stmt = con.createStatement();

            stmt.executeUpdate("delete from NoteLayout where note_id = '" + noteID + "'");
        } catch (Exception e) {
            e.getMessage();
        }
    }


    public String deleteNote(int user_id, String noteName) {

        int notebookID = getNotebookID(user_id);
        int sectionID = getSectionID(notebookID);
        int pageID = getPageId(sectionID, noteName);
        int noteID = getNoteID(pageID);
        if (notebookID != -1 && sectionID != -1 && pageID != -1 && noteID != -1) {


            try {
                Class.forName("com.mysql.jdbc.Driver");
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3307/onenote", "root", "");
                Statement stmt = con.createStatement();
                deleteNoteCategory(noteID);
                deleteNoteLayout(noteID);
                stmt.executeUpdate("delete from Note where page_id = '" + pageID + "'");
                deltePage(pageID, noteName);


                return "Note Deleted";

            } catch (Exception e) {
                e.getMessage();

            }

            return "Error Note couldn't deleted";

        } else {
            return "Note Does not exists";
        }
    }


//========================================Add File To Your PC====================================

    //to save the note to ur PC


    public String addFile(int user_id, String noteName, String filepath) {
  	  try {
  	    Class.forName("com.mysql.jdbc.Driver");
  	    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3307/OneNote", "root", "");
  	    Statement stmt = con.createStatement();
  	    ResultSet rs = stmt.executeQuery("select PageTitle, Text from page\r\n" +
  	      "JOIN section ON section.SectionID = page.SectionID\r\n" +
  	      "JOIN notebook ON notebook.NoteBookID = section.NoteBookID\r\n" +
  	      "JOIN user ON user.user_id = notebook.user_id\r\n" +
  	      "JOIN note ON note.page_id = page.page_id\r\n" +
  	      "where user.user_id = '" + user_id + "' AND page.PageTitle = '" + noteName + "'\r\n" +
  	      "");
  	    rs.next();
  	    String noteTitle = rs.getString(1);
  	    String txt = rs.getString(2);

  	    // Check if the filepath is valid
  	    File file = new File(filepath);
  	    if (!file.exists() || !file.isDirectory()) {
  	      throw new IOException("The filepath is invalid or does not exist. Please enter a valid filepath.");
  	    }

  	    // Call the saveFile method inside the try-catch block
  	    saveFile(filepath, noteTitle, txt);
  	    con.close();
  	    return "File created successfully at: " + filepath + noteTitle + ".txt";
  	  } catch (IOException e) {
  	    System.out.println(e.getMessage());
  	    return "Error: Plz confirm that the FilePath is correct ";
  	  } catch (Exception e) {
  	    System.out.println(e);
  	    return "File Does not exists";
  	  }
  	}


    private void saveFile(String filepath, String noteTitle, String txt) throws IOException {
    	  try {
    	    // Add a trailing slash to the filepath if it doesn't have one
    	    if (!filepath.endsWith("/")) {
    	      filepath += "/";
    	    }
    	    FileWriter fileWriter = new FileWriter(filepath + noteTitle + ".txt");
    	    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

    	    bufferedWriter.write(txt);
    	    bufferedWriter.close();
    	  } catch (Exception e) {
    	    e.getMessage();
    	  }
    	}


}