/**
 * NotesService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package onenote.zaman;

public interface NotesService extends javax.xml.rpc.Service {
    public java.lang.String getNotesAddress();

    public onenote.zaman.Notes getNotes() throws javax.xml.rpc.ServiceException;

    public onenote.zaman.Notes getNotes(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
