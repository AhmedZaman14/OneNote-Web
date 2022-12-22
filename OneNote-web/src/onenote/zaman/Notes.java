package onenote.zaman;
import java.sql.*;
import java.util.Date;

public class Notes {
	private int getNoteBookid(int user_id) {
		try {
		Class.forName("com.mysql.jdbc.Driver");
		Connection con =DriverManager.getConnection("jdbc:mysql://localhost:3307/OneNote","root","");
		Statement stmt=con.createStatement();
		ResultSet rs=stmt.executeQuery("select NotebookID from notebook where user_id='"+user_id+"'");
		rs.next();
		return rs.getInt(1);
		}
		catch(Exception e) {
			System.out.println(e);
		}
		System.out.println("NoteBook does not exists for this user:");
		return -1;
	}
	
	private int getSectionid(int user_id ,String sectionName) {
		int noteBookid  =getNoteBookid(user_id);
		if(noteBookid!=-1) {
			try {
				Class.forName("com.mysql.jdbc.Driver");
				Connection con =DriverManager.getConnection("jdbc:mysql://localhost:3307/OneNote","root","");
				Statement stmt=con.createStatement();
				ResultSet rs=stmt.executeQuery("select SectionID from Section where notebookid='"+noteBookid+"' and "
						+ "SectionName ='"+sectionName+"'");
				rs.next();
				return rs.getInt(1);
				}
				catch(Exception e) {
					System.out.println(e);
				}
		}
		System.out.println("error in getsection");
		return -1;
	}
	
	private boolean checkPageExistance(String pageTitle, int user_id) {
		try {
		Class.forName("com.mysql.jdbc.Driver");
		Connection con =DriverManager.getConnection("jdbc:mysql://localhost:3307/OneNote","root","");
		Statement stmt=con.createStatement();
		ResultSet rs=stmt.executeQuery("select PageTitle from Page\r\n" + 
				"JOIN section ON section.SectionID = page.SectionID\r\n" + 
				"JOIN notebook ON notebook.NoteBookID = section.NoteBookID\r\n" + 
				"JOIN user ON user.user_id = notebook.user_id\r\n" + 
				"JOIN note ON note.page_id = page.page_id\r\n" + 
				"where user.user_id = '"+user_id+"' AND page.PageTitle = '"+pageTitle+"'\r\n" + 
				"");
		if(rs.next())
		return true;
		
		} catch(Exception e)
		{
		System.out.println(e);
		}

		
		return false;
	}

	
	private int createPage(String pageTitle, int user_id, String sectionName) {
		int sectionId = getSectionid(user_id,sectionName);
		if(sectionId !=-1 && !checkPageExistance(pageTitle, user_id)) {
			try {
				Class.forName("com.mysql.jdbc.Driver");
				Connection con =DriverManager.getConnection("jdbc:mysql://localhost:3307/OneNote","root","");
				
				
				Statement stmt=con.createStatement();
				stmt.executeUpdate("insert into Page(pageTitle,SectionId) values('"+pageTitle+"', '"+sectionId+"')", Statement.RETURN_GENERATED_KEYS);
				 ResultSet generatedKeys = stmt.getGeneratedKeys();
				    if (generatedKeys.next()) {
				      return generatedKeys.getInt(1);
				    }
				
				}
				catch(Exception e) {
					System.out.println(e);
				}
		}
		//System.out.println("error in createPage");
		return -1;
		
	}
	

	
	
	
	
	private void insertDefaultLayout(int noteID){
		if(noteID !=-1) {
			  try {
			    Class.forName("com.mysql.jdbc.Driver");
			    Connection con =DriverManager.getConnection("jdbc:mysql://localhost:3307/OneNote","root","");
			    Statement stmt = con.createStatement();
			    stmt.executeUpdate("INSERT INTO NoteLayout(note_id,textColor,fontName) VALUES( '"+noteID+"', 'Black', 'Georgia')");
			    
			   
			  } catch (Exception e) {
			    System.out.println(e);
			  }
			}
	}
	
	private void insertIntoNoteCategory(String txt,int noteID) {
		String category = getNoteCategory(txt);
		if(noteID !=-1) {
			  try {
			    Class.forName("com.mysql.jdbc.Driver");
			    Connection con =DriverManager.getConnection("jdbc:mysql://localhost:3307/OneNote","root","");
			    Statement stmt = con.createStatement();
			    stmt.executeUpdate("INSERT INTO category(CategoryName,note_id) VALUES('"+category+"', '"+noteID+"')");
			    
			   
			  } catch (Exception e) {
			    System.out.println(e);
			  }
			}
			
	}
	
	
	private String getNoteCategory( String txt) {
		  
		  String[] schoolKeywords = {"assignment", "quiz", "home work", "class"};
		  String[] workKeywords = {"presentation", "job", "boss", "deadline", "task"};
		  String[] domiciliaryKeywords = {"car", "home", "groceries", "bill", "family"};

		  for (String keyword : schoolKeywords) {
		    if (txt.matches(".*\\b" + keyword + "\\b.*")) {
		      return "School";
		    }
		  }
		  for (String keyword : workKeywords) {
		    if (txt.matches(".*\\b" + keyword + "\\b.*")) {
		      return "Work";
		    }
		  }
		  for (String keyword : domiciliaryKeywords) {
		    if (txt.matches(".*\\b" + keyword + "\\b.*")) {
		      return "Domiciliary";
		    }
		  }

		  return "Other";
		}
		





	
	
	
	private int insertIntoNote(String txt,int pageID) {
		  try {
			  Date currentDate = new Date();
			  Timestamp currentTimestamp = new Timestamp(currentDate.getTime());
		    Class.forName("com.mysql.jdbc.Driver");
		    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3307/OneNote", "root", "");
		    Statement stmt = con.createStatement();
		    stmt.executeUpdate("INSERT INTO Note(Text,CreationDate, page_id) VALUES('" + txt + "', '" + currentTimestamp + "', '" + pageID + "')", Statement.RETURN_GENERATED_KEYS);

		    // Get the generated ID
		    ResultSet generatedKeys = stmt.getGeneratedKeys();
		    if (generatedKeys.next()) {
		      return generatedKeys.getInt(1);
		    }
		  } catch (Exception e) {
		    System.out.println(e);
		  }
		  return -1;
		}
	
	
	
	public String createANote(String pageTitle, String txt,String sectionName, int user_id) {
		  
		int noteID= -1;
		
		if(!pageTitle.equals("") && !txt.equals("")) {
		if(checkPageExistance(pageTitle, user_id)) {
			return "Plz Change PageTitle because Page with this name already exists";
		}else {
		
		
			try {
				int pageID = createPage(pageTitle,user_id,sectionName);
				noteID = insertIntoNote(txt,pageID);
				insertIntoNoteCategory(txt,noteID);
				insertDefaultLayout(noteID);
				
				return "Note Created";
				}
				catch(Exception e) {
					System.out.println(e);
				}
			return "Plz Provide All  Correctly";
		
		}
		}
		return "Plz Provide All Information Correctly";
		
		
	    
	}
	
	

	public String searchNote(int user_id, String noteName) {
		try{
			
			
			Class.forName("com.mysql.jdbc.Driver");
			Connection con =DriverManager.getConnection("jdbc:mysql://localhost:3307/OneNote","root","");
			Statement stmt=con.createStatement();
			ResultSet rs=stmt.executeQuery("select PageTitle, Text, CreationDate from Page\r\n" + 
					"JOIN section ON section.SectionID = page.SectionID\r\n" + 
					"JOIN notebook ON notebook.NoteBookID = section.NoteBookID\r\n" + 
					"JOIN user ON user.user_id = notebook.user_id\r\n" + 
					"JOIN note ON note.page_id = page.page_id\r\n" + 
					"where user.user_id = '"+user_id+"' AND page.PageTitle = '"+noteName+"'\r\n" + 
					"");
			rs.next();
			String displayNote = "Note Title: " + rs.getString(1) + "\nNote: " + rs.getString(2) +"\nCreationData: "+ rs.getString(3);
			con.close();
			return displayNote;
			
			} catch(Exception e)
			{
			System.out.println(e);
			}
		return "No Such Note Found!";
		
		}
	
	
	
}
