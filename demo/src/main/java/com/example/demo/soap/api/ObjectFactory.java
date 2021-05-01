//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantación de la referencia de enlace (JAXB) XML v2.2.11 
// Visite <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: 2021.04.30 a las 08:27:56 PM COT 
//


package com.example.demo.soap.api;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.example.demo.soap.api package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _Backend_QNAME = new QName("https://tech-test.getsandbox.com:443/back/auth", "backend");
    private final static QName _BackendResponse_QNAME = new QName("https://tech-test.getsandbox.com:443/back/auth", "backendResponse");
    private final static QName _UserDefinedFault_QNAME = new QName("https://tech-test.getsandbox.com:443/back/auth", "UserDefinedFault");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.example.demo.soap.api
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Backend }
     * 
     */
    public Backend createBackend() {
        return new Backend();
    }

    /**
     * Create an instance of {@link BackendResponse }
     * 
     */
    public BackendResponse createBackendResponse() {
        return new BackendResponse();
    }

    /**
     * Create an instance of {@link UserDefinedFault }
     * 
     */
    public UserDefinedFault createUserDefinedFault() {
        return new UserDefinedFault();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Backend }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "https://tech-test.getsandbox.com:443/back/auth", name = "backend")
    public JAXBElement<Backend> createBackend(Backend value) {
        return new JAXBElement<Backend>(_Backend_QNAME, Backend.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BackendResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "https://tech-test.getsandbox.com:443/back/auth", name = "backendResponse")
    public JAXBElement<BackendResponse> createBackendResponse(BackendResponse value) {
        return new JAXBElement<BackendResponse>(_BackendResponse_QNAME, BackendResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UserDefinedFault }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "https://tech-test.getsandbox.com:443/back/auth", name = "UserDefinedFault")
    public JAXBElement<UserDefinedFault> createUserDefinedFault(UserDefinedFault value) {
        return new JAXBElement<UserDefinedFault>(_UserDefinedFault_QNAME, UserDefinedFault.class, null, value);
    }

}
