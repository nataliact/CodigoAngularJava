package com.example.demo.service;

import javax.xml.bind.JAXBElement;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Service;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

import com.example.demo.soap.api.Backend;
import com.example.demo.soap.api.BackendResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.soap.Node;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

@Service
public class InicioSesion extends WebServiceGatewaySupport{
	
	@Autowired
	private Jaxb2Marshaller marshaller;

	private WebServiceTemplate template;

	public BackendResponse getinfo(Backend request) {
		template = new WebServiceTemplate(marshaller);
		BackendResponse backendResponse = (BackendResponse) template.marshalSendAndReceive("https://tech-test.getsandbox.com:443/back/auth",
				request,
				new SoapActionCallback("https://tech-test.getsandbox.com:443/back/auth"));
		return backendResponse;
		
	}
	
	public BackendResponse getBank(Object request){
		JAXBElement res = (JAXBElement) getWebServiceTemplate().marshalSendAndReceive("https://tech-test.getsandbox.com:443/back/auth", request);
		return (BackendResponse) res.getValue();
	}
	
	public BackendResponse getInfo(Backend request) {
		BackendResponse bkr = new BackendResponse();
		bkr.setResultCode(99999);
        String wsURL = "https://tech-test.getsandbox.com:443/back/auth";
        URL url = null;
        URLConnection connection = null;
        HttpURLConnection httpConn = null;
        String responseString = null;
        String outputString="";
        OutputStream out = null;
        InputStreamReader isr = null;
        BufferedReader in = null;
         
        String xmlInput =
                "<soap:Envelope xmlns:soap=\"http://www.w3.org/2003/05/soap-envelope\" xmlns:sam=\"http://example.com/sample\">\r\n"
                + "   <soap:Header/>\r\n"
                + "   <soap:Body>\r\n"
                + "      <sam:backend>\r\n"
                + "         <user>"+ request.getUser() +"</user>\r\n"
                + "         <password>"+ request.getPassword() +"</password>\r\n"
                + "      </sam:backend>\r\n"
                + "   </soap:Body>\r\n"
                + "</soap:Envelope>";
         
        try
        {
            url = new URL(wsURL);
            connection = url.openConnection();
            httpConn = (HttpURLConnection) connection;
 
            byte[] buffer = new byte[xmlInput.length()];
            buffer = xmlInput.getBytes();
 
            String SOAPAction = "";
            
             httpConn.setRequestProperty("Content-Length", String
                     .valueOf(buffer.length));
            httpConn.setRequestProperty("Content-Type",
                    "text/xml; charset=utf-8");
             
             
            httpConn.setRequestProperty("SOAPAction", SOAPAction);
            httpConn.setRequestMethod("POST");
            httpConn.setDoOutput(true);
            httpConn.setDoInput(true);
            out = httpConn.getOutputStream();
            out.write(buffer);
            out.close();
             
            isr = new InputStreamReader(httpConn.getInputStream());
            in = new BufferedReader(isr);
             
            while ((responseString = in.readLine()) != null) 
            {
                outputString = outputString + responseString;
            }
                         
            
            Document document = parseXmlFile(outputString);
            NodeList nl = document.getElementsByTagName("sam:backendResponse");
            
            int length = nl.getLength();
            for (int i = 0; i < length; i++) {
                if (nl.item(i).getNodeType() == Node.ELEMENT_NODE) {
                    Element el = (Element) nl.item(i);
                    if (el.getNodeName().contains("backendResponse")) {
                        bkr.setResultCode(Integer.parseInt(el.getElementsByTagName("resultCode").item(0).getTextContent()));
                        bkr.setFirstName(el.getElementsByTagName("firstName").item(0).getTextContent());
                        bkr.setLastName(el.getElementsByTagName("lastName").item(0).getTextContent());
                        bkr.setAge(Integer.parseInt(el.getElementsByTagName("age").item(0).getTextContent()));
                        bkr.setProfilePhoto(el.getElementsByTagName("profilePhoto").item(0).getTextContent());
                        bkr.setVideo(el.getElementsByTagName("video").item(0).getTextContent());
                        
                    }
                    
                }
            }
            
              
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
        
        return bkr;
    }
     
    private Document parseXmlFile(String in) {
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
             InputSource is = new InputSource(new StringReader(in));
            return db.parse(is);
        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        } catch (SAXException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
