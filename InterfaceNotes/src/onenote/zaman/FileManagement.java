/**
 * FileManagement.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package onenote.zaman;

public interface FileManagement extends java.rmi.Remote {
    public java.lang.String addFile(int user_id, java.lang.String noteName, java.lang.String filepath) throws java.rmi.RemoteException;
    public java.lang.String deleteNote(int user_id, java.lang.String noteName) throws java.rmi.RemoteException;
}
