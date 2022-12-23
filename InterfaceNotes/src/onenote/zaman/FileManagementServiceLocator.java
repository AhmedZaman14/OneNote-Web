/**
 * FileManagementServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package onenote.zaman;

public class FileManagementServiceLocator extends org.apache.axis.client.Service implements onenote.zaman.FileManagementService {

    public FileManagementServiceLocator() {
    }


    public FileManagementServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public FileManagementServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for FileManagement
    private java.lang.String FileManagement_address = "http://localhost:8080/OneNote-web/services/FileManagement";

    public java.lang.String getFileManagementAddress() {
        return FileManagement_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String FileManagementWSDDServiceName = "FileManagement";

    public java.lang.String getFileManagementWSDDServiceName() {
        return FileManagementWSDDServiceName;
    }

    public void setFileManagementWSDDServiceName(java.lang.String name) {
        FileManagementWSDDServiceName = name;
    }

    public onenote.zaman.FileManagement getFileManagement() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(FileManagement_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getFileManagement(endpoint);
    }

    public onenote.zaman.FileManagement getFileManagement(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            onenote.zaman.FileManagementSoapBindingStub _stub = new onenote.zaman.FileManagementSoapBindingStub(portAddress, this);
            _stub.setPortName(getFileManagementWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setFileManagementEndpointAddress(java.lang.String address) {
        FileManagement_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (onenote.zaman.FileManagement.class.isAssignableFrom(serviceEndpointInterface)) {
                onenote.zaman.FileManagementSoapBindingStub _stub = new onenote.zaman.FileManagementSoapBindingStub(new java.net.URL(FileManagement_address), this);
                _stub.setPortName(getFileManagementWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("FileManagement".equals(inputPortName)) {
            return getFileManagement();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://zaman.onenote", "FileManagementService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://zaman.onenote", "FileManagement"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("FileManagement".equals(portName)) {
            setFileManagementEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
