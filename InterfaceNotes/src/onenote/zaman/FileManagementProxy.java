package onenote.zaman;

public class FileManagementProxy implements onenote.zaman.FileManagement {
  private String _endpoint = null;
  private onenote.zaman.FileManagement fileManagement = null;
  
  public FileManagementProxy() {
    _initFileManagementProxy();
  }
  
  public FileManagementProxy(String endpoint) {
    _endpoint = endpoint;
    _initFileManagementProxy();
  }
  
  private void _initFileManagementProxy() {
    try {
      fileManagement = (new onenote.zaman.FileManagementServiceLocator()).getFileManagement();
      if (fileManagement != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)fileManagement)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)fileManagement)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (fileManagement != null)
      ((javax.xml.rpc.Stub)fileManagement)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public onenote.zaman.FileManagement getFileManagement() {
    if (fileManagement == null)
      _initFileManagementProxy();
    return fileManagement;
  }
  
  public java.lang.String addFile(int user_id, java.lang.String noteName, java.lang.String filepath) throws java.rmi.RemoteException{
    if (fileManagement == null)
      _initFileManagementProxy();
    return fileManagement.addFile(user_id, noteName, filepath);
  }
  
  public java.lang.String deleteNote(int user_id, java.lang.String noteName) throws java.rmi.RemoteException{
    if (fileManagement == null)
      _initFileManagementProxy();
    return fileManagement.deleteNote(user_id, noteName);
  }
  
  
}