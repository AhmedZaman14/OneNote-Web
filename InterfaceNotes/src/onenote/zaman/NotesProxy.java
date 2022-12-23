package onenote.zaman;

public class NotesProxy implements onenote.zaman.Notes {
  private String _endpoint = null;
  private onenote.zaman.Notes notes = null;
  
  public NotesProxy() {
    _initNotesProxy();
  }
  
  public NotesProxy(String endpoint) {
    _endpoint = endpoint;
    _initNotesProxy();
  }
  
  private void _initNotesProxy() {
    try {
      notes = (new onenote.zaman.NotesServiceLocator()).getNotes();
      if (notes != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)notes)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)notes)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (notes != null)
      ((javax.xml.rpc.Stub)notes)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public onenote.zaman.Notes getNotes() {
    if (notes == null)
      _initNotesProxy();
    return notes;
  }
  
  public java.lang.String createANote(java.lang.String pageTitle, java.lang.String txt, java.lang.String sectionName, int user_id) throws java.rmi.RemoteException{
    if (notes == null)
      _initNotesProxy();
    return notes.createANote(pageTitle, txt, sectionName, user_id);
  }
  
  public java.lang.String searchNote(int user_id, java.lang.String noteName) throws java.rmi.RemoteException{
    if (notes == null)
      _initNotesProxy();
    return notes.searchNote(user_id, noteName);
  }
  
  
}