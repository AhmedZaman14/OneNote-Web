/**
 * Notes.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package onenote.zaman;

public interface Notes extends java.rmi.Remote {
    public java.lang.String createANote(java.lang.String pageTitle, java.lang.String txt, java.lang.String sectionName, int user_id) throws java.rmi.RemoteException;
    public java.lang.String searchNote(int user_id, java.lang.String noteName) throws java.rmi.RemoteException;
}
