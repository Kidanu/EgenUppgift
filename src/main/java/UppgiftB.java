import com.sun.xml.internal.ws.resources.SoapMessages;

import javax.xml.soap.*;
import java.io.IOException;

public class UppgiftB {
    public static void main(String[] args) {
        String url = "http://webservices.oorsprong.org/websamples.countryinfo/CountryInfoService.wso?WSDL";
        String country = "CountryName";
        String capital = "CapitalName";
        String currency = "CountryCurrency";

        callSoapWebService(url,country,"SE");//Anväder SE för att få country name
    }

    private static void callSoapWebService(String url, String function, String countryISOCode){
        try{
            SOAPConnectionFactory SoapConFactory = SOAPConnectionFactory.newInstance();//Skapar nytt objekt
            SOAPConnection sopCon = SoapConFactory.createConnection();//Skapar med ett tomt objekt connection, inte skickat någon url.Väntar på en call
            SOAPMessage soapMessage = createSoapRequest(function,countryISOCode);//Skickar in parametrarna
            SOAPMessage response = sopCon.call(soapMessage,url);//Skickar xml soap meddelandet till webApiet. (Till XMlet man måste skicka till)

           // System.out.println(response.getSOAPBody());
            response.writeTo(System.out);

        }catch (SOAPException e){
            e.printStackTrace();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
    private static SOAPMessage createSoapRequest(String function, String countryISOCode) throws SOAPException {


            MessageFactory mf = MessageFactory.newInstance();//Nytt objekt
            SOAPMessage soapMessages = mf.createMessage();//Använder Createmeesage metod från klassen
            createSoapMessage(soapMessages,countryISOCode);//Country ISO code är vad vi slänger in i xml:en.

            if(function.endsWith("CountryName")){
                System.out.println("Kommer jag till land");
            }
            else if(function.endsWith("CapitalName")) {
            System.out.println("Kmr jag till capital");
            }
            else if(function.endsWith("CurrencyName")) {
                System.out.println("kmr jag till pengar");
            }
                return soapMessages;

    }

    private static void createSoapMessage(SOAPMessage sm, String countryISOCode){
        SOAPPart soapPart = sm.getSOAPPart();
       try {
           SOAPEnvelope soapEnvelope = soapPart.getEnvelope();//
           soapEnvelope.addNamespaceDeclaration("ns1","http://www.oorsprong.org/websamples.countryinfo");//Skapar XMl anropet här

           SOAPBody sb = soapEnvelope.getBody();
           SOAPElement soapElement = sb.addChildElement("CountryCurrency","ns1");//Detta element som är annorulunda
           SOAPElement soapElement2 = soapElement.addChildElement("sCountryISOCode","ns1");
           soapElement2.addTextNode(countryISOCode);

       }catch (SOAPException e){
           e.printStackTrace();
       }

    }


}

/*<?xml version="1.0" encoding="UTF-8"?>
<SOAP-ENV:Envelope xmlns:SOAP-ENV="http://schemas.xmlsoap.org/soap/envelope/" xmlns:ns1="http://www.oorsprong.org/websamples.countryinfo">
  <SOAP-ENV:Body>
    <ns1:CountryName>
      <ns1:sCountryISOCode>?</ns1:sCountryISOCode>
    </ns1:CountryName>
  </SOAP-ENV:Body>
</SOAP-ENV:Envelope>*/

/*<?xml version="1.0" encoding="UTF-8"?>
<SOAP-ENV:Envelope xmlns:SOAP-ENV="http://schemas.xmlsoap.org/soap/envelope/" xmlns:ns1="http://www.oorsprong.org/websamples.countryinfo">
  <SOAP-ENV:Body>
    <ns1:CapitalCity>
      <ns1:sCountryISOCode>?</ns1:sCountryISOCode>
    </ns1:CapitalCity>
  </SOAP-ENV:Body>
</SOAP-ENV:Envelope>
*/
/*<?xml version="1.0" encoding="UTF-8"?>
<SOAP-ENV:Envelope xmlns:SOAP-ENV="http://schemas.xmlsoap.org/soap/envelope/" xmlns:ns1="http://www.oorsprong.org/websamples.countryinfo">
  <SOAP-ENV:Body>
    <ns1:CountryCurrency>
      <ns1:sCountryISOCode>?</ns1:sCountryISOCode>
    </ns1:CountryCurrency>
  </SOAP-ENV:Body>
</SOAP-ENV:Envelope>*/
