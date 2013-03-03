/*
 * Copyright(C) OASIS(R) 2009,2010. All Rights Reserved.
 * OASIS trademark, IPR and other policies apply.    
 */
package client_bws;

import javax.xml.namespace.QName;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPHeader;
import javax.xml.soap.SOAPMessage;
import javax.xml.ws.Dispatch;
import javax.xml.ws.Endpoint;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.Handler;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;
import java.lang.reflect.Constructor;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * An extended JAXWSTestRunner client for the Web services Binding testcases, which creates a JAXWS Web service endpoint that serves as an external Web service
 * that can be invoked from the SCA application that is under test
 */
public abstract class BWS_JAXWSTestRunner extends BaseJAXWSTestRunner {

    static Endpoint endpoint;

    /**
     * An extended version of the setUp() method in the BaseJAXWSTestRunner class - this version instantiates a JAXWS Web service on the client, which can be
     * invoked via Web services from an SCA reference - further, a JAXWS Handler is installed in that client service which examines the Headers of the request
     * for any SCA callback addressing parameters, which should be present if the service on the client has a bidirectional (callback) interface.  The handler
     * extracts the callback address data from the header and places it into the message context object which is available to the service methods
     */
    public void setUp() throws Throwable {

        resetStatics();

        // Next do the setup of the JAXWS service on the client
        BWSTestConfiguration bwsConfig = getBWSTestConfiguration();
        String endpointAddress = bwsConfig.getEndpointAddress();
        System.out.println(">>> Starting external JAXWS service at: " + endpointAddress);

        String className = bwsConfig.getEndpointClass();

        // Create the JAXWS endpoint... 
        try {
            Class<?> serviceImpl = Class.forName(className);
            Constructor<?> serviceConstructor = serviceImpl.getConstructor();
            Object serviceImplObject = serviceConstructor.newInstance();
            endpoint = Endpoint.create(null, serviceImplObject);

            // Insert the callback address handler into the handler chain for this service
            List<Handler> handlerChain = endpoint.getBinding().getHandlerChain();
            handlerChain.add(new SCACallbackHandler());
            endpoint.getBinding().setHandlerChain(handlerChain);

            // Publish the service
            endpoint.publish(endpointAddress);
        } catch (Exception e) {
            System.out.println("Exception thrown while publishing client endpoint: " + e.getMessage());
            e.printStackTrace();
        }

    } // end method setUp()

    protected abstract BWSTestConfiguration getBWSTestConfiguration();

    public void tearDown() throws Exception {

        // Stop the JAXWS endpoint
        if (endpoint != null) {
            endpoint.stop();
        }
    }

    /**
     * There are a set of static fields in this class which are used by some testcases to hold testcase-specific metadata - these fields are static since they
     * are accessed by static methods called from other classes running in separate threads.
     * <p/>
     * This method resets the fields to a known state at the beginning of each test run so that the values established in one testcase do not interfere with the
     * subsequent testcases.
     */
    protected void resetStatics() {
        relatesRequired = false;
        scaPropsRequired = false;
        relatedMsg = "";
        scaProps = "";
    } // end method resetStatics

    public static final String ADDRESS_KEY = "returnAddress";
    public static final String TO_ADDRESS_KEY = "toAddress";
    public static final String RELATES_KEY = "relateID";
    public static final String RELATES_TYPE_KEY = "relateTypeID";
    public static final String ACTION_KEY = "actionValue";
    public static final String SCAPROPS_KEY = "scaProps";

    private static final String XMLNS_TEST = "http://test.sca.oasisopen.org/";
    private static final String XMLNS_WSA = "http://www.w3.org/2005/08/addressing";
    private static final String WSA_ANONYMOUS = "http://www.w3.org/2005/08/addressing/anonymous";
    private static final String SCA_PROPS = "SCAProps";

    /**
     * Prepares the SOAP service invocation including the SOAP Body and SOAP Header elements
     *
     * @param dispatch        - this Dispatch object that will be used to make the service invocation
     * @param callAddress     - the address of the service to call
     * @param operationName   - the name of the target operation to invoke - if null, "operation1" is used
     * @param callbackAddress - the callback address for a bidirectional service, null if the service is not bidirectional
     * @param refParms        - WS Addressing reference parameters for the callbackAddress - can be null
     * @param messageID       - message ID for the outgoing request message, where correlation of the callback request is desired - can be null
     * @param requestMsg      - the content of the request message
     * @return a SOAPMessage element containing the prepared SOAP message
     * @throws SOAPException
     */
    protected SOAPMessage prepareRequest(Dispatch<SOAPMessage> dispatch,
                                         String callAddress,
                                         String operationName,
                                         String callbackAddress,
                                         String refParms,
                                         String messageID,
                                         String requestMsg) throws SOAPException {
        final String OP_NAME = "operation1";

        MessageFactory factory = MessageFactory.newInstance();
        SOAPMessage request = factory.createMessage();
        SOAPEnvelope envelope = request.getSOAPPart().getEnvelope();

        if (operationName == null) {
            operationName = OP_NAME;
        }

        // Create the request
        request.getSOAPBody().
                addBodyElement(envelope.createName(operationName, "ns2", XMLNS_TEST)).
                addChildElement(envelope.createName("arg0")).
                addTextNode(requestMsg);

        // Add WS-Addressing header for the callback
        SOAPHeader theHeader = request.getSOAPHeader();

        if (callbackAddress != null) {
            SOAPElement wsaFrom = theHeader.addHeaderElement(envelope.createName("From", "wsa", XMLNS_WSA));
            wsaFrom.addChildElement(envelope.createName("Address", "wsa", XMLNS_WSA)).addTextNode(callbackAddress);
            // If there are reference parameters for the callback address, they are placed into a WSA ReferenceProperties element
            // within an SCAProps element declared in the SCA Test namespace
            if (refParms != null) {
                wsaFrom.addChildElement(envelope.createName("ReferenceParameters", "wsa", XMLNS_WSA)).
                        addChildElement(envelope.createName(SCA_PROPS, "test", XMLNS_TEST)).
                        addTextNode(refParms);
            }
        } // end if 
        theHeader.addHeaderElement(envelope.createName("To", "wsa", XMLNS_WSA)).addTextNode(callAddress);
//       theHeader.addHeaderElement(envelope.createName("Action", "wsa", XMLNS_WSA)).addTextNode(XMLNS_TEST + operationName);
        if (messageID != null) {
            theHeader.addHeaderElement(envelope.createName("MessageID", "wsa", XMLNS_WSA)).
                    addTextNode(messageID);
        }

        return request;
    } // end method prepareRequest

    protected static boolean relatesRequired = false;
    protected static boolean scaPropsRequired = false;
    protected static String relatedMsg = "";
    protected static String scaProps = "";
    protected static String SCA_CALLBACK_REL = "http://docs.oasis-open.org/opencsa/sca-bindings/ws/callback";

    /**
     * Validates the callback headers received when a callback operation is invoked
     *
     * @param serviceCtx - the WebServiceContext for the callback operation invocation
     * @return - true if the headers match, false if they do not match
     */
    public static String validateCallbackHeaders(WebServiceContext serviceCtx) {
        MessageContext theContext = serviceCtx.getMessageContext();

        final String problem = "invalid headers: ";

        // Basic required information is:
        // a) wsa:To
        // b) wsa:Action
        String toAddress = (String) theContext.get(TO_ADDRESS_KEY);
        String action = (String) theContext.get(ACTION_KEY);
        if (toAddress == null) {
            return problem + "wsa:To missing";
        }
        if (action == null) {
            return problem + "wsa:Action missing";
        }

        // Additional optional information includes:
        // a) RelatesTo
        // b) Reference properties
        if (relatesRequired) {
            String relatesTo = (String) theContext.get(RELATES_KEY);
            String relatesType = (String) theContext.get(RELATES_TYPE_KEY);
            if (relatesTo == null) {
                return problem + "wsa:RelatesTo missing";
            }
            if (!relatesTo.equals(relatedMsg)) {
                return problem + "related message ID incorrect";
            }
            if (relatesType == null) {
                return problem + "@RelationshipType attribute missing";
            }
            if (!relatesType.equals(SCA_CALLBACK_REL)) {
                return problem + "@RelationshipType attribute invalid";
            }
        } // end if

        if (scaPropsRequired) {
            String returnedScaProps = (String) theContext.get(SCAPROPS_KEY);
            if (returnedScaProps == null) {
                return problem + "EPR Reference Properties missing";
            }
            if (!returnedScaProps.equals(scaProps)) {
                return problem + "EPR Reference Properties incorrect value";
            }
        } // end if

        return "headers OK";
    } // end method validateCallbackHeaders

    /**
     * SOAPHandler class which is placed into the handler chain to deal with callback addresses for SCA bidirectional services, which are passed in headers -
     * either wsa:From or wsa:ReplyTo
     */
    public class SCACallbackHandler implements SOAPHandler<SOAPMessageContext> {

        static final String XMLNS_WSA = "http://www.w3.org/2005/08/addressing";

        /**
         * Declare the QNames of the headers handled by this handler
         */
        public Set<QName> getHeaders() {
            Set<QName> theHeaders = new HashSet<QName>();
            theHeaders.add(new QName(XMLNS_WSA, "ReplyTo"));
            theHeaders.add(new QName(XMLNS_WSA, "From"));
            return theHeaders;
        }

        public void close(MessageContext arg0) {
            // Intentionally empty
        }

        public boolean handleFault(SOAPMessageContext arg0) {
            // Intentionally empty
            return false;
        }

        /**
         * handleMessage method handles the headers associated with a message
         * <p/>
         * it looks for the wsa:From and wsa:ReplyTo headers (in that order) and if present extracts the wsa:Address value,
         * which is then stored into the message
         * context for use as the callback address for the service operation.
         */
        @SuppressWarnings("unchecked")
        public boolean handleMessage(SOAPMessageContext context) {

            try {
                SOAPMessage soapMessage = context.getMessage();
                if (soapMessage == null) {
                    return true;
                }
                SOAPHeader soapHeader = soapMessage.getSOAPHeader();
                if (soapHeader == null) {
                    return true;
                }

                handleWsaToHeader(soapHeader, context);
                handleWsaRelatesToHeader(soapHeader, context);
                handleWsaActionHeader(soapHeader, context);
                handleScaPropsHeader(soapHeader, context);

                Iterator<SOAPElement> fromHeaders = (Iterator<SOAPElement>) soapHeader.getChildElements(new QName(XMLNS_WSA, "From"));
                if (fromHeaders.hasNext()) {
                    SOAPElement fromElement = fromHeaders.next();
                    extractReturnAddress(fromElement, context);
                    return true;
                } // end if

                Iterator<SOAPElement> replyToHeaders = (Iterator<SOAPElement>) soapHeader.getChildElements(new QName(XMLNS_WSA, "ReplyTo"));
                if (replyToHeaders.hasNext()) {
                    SOAPElement replyToElement = replyToHeaders.next();
                    extractReturnAddress(replyToElement, context);
                    return true;
                } // end if

            } catch (SOAPException e) {
                System.out.println("SCACallbackHandler got SOAPException trying to read the SOAP header");
                e.printStackTrace();
            }
            // If neither header is present, then no address is obtained and the forwarded message context contains
            // no "returnAddress" property

            return true;
        } // end method handleMessage

        private boolean handleWsaToHeader(SOAPHeader soapHeader, SOAPMessageContext context) {
            Iterator<SOAPElement> toHeaders = (Iterator<SOAPElement>) soapHeader.getChildElements(new QName(XMLNS_WSA, "To"));
            if (toHeaders.hasNext()) {
                SOAPElement toElement = toHeaders.next();
                String address = toElement.getValue();
                if (address != null) {
                    System.out.println("wsa:To address found: " + address);
                    context.put(TO_ADDRESS_KEY, address);
                    context.setScope(TO_ADDRESS_KEY, MessageContext.Scope.APPLICATION);
                } // end if
                return true;
            } // end if
            return false;
        }

        private boolean handleWsaRelatesToHeader(SOAPHeader soapHeader, SOAPMessageContext context) {
            Iterator<SOAPElement> relatesHeaders = (Iterator<SOAPElement>) soapHeader.getChildElements(new QName(XMLNS_WSA, "RelatesTo"));
            if (relatesHeaders.hasNext()) {
                SOAPElement relatesElement = relatesHeaders.next();
                String relationshipType = relatesElement.getAttribute("RelationshipType");
                String relateID = relatesElement.getValue();
                if (relateID != null) {
                    System.out.println("wsa:RelatesTo ID found: " + relateID);
                    context.put(RELATES_KEY, relateID);
                    context.setScope(RELATES_KEY, MessageContext.Scope.APPLICATION);
                    context.put(RELATES_TYPE_KEY, relationshipType);
                    context.setScope(RELATES_TYPE_KEY, MessageContext.Scope.APPLICATION);
                } // end if
                return true;
            } // end if
            return false;
        }

        private boolean handleWsaActionHeader(SOAPHeader soapHeader, SOAPMessageContext context) {
            Iterator<SOAPElement> actionHeaders = (Iterator<SOAPElement>) soapHeader.getChildElements(new QName(XMLNS_WSA, "Action"));
            if (actionHeaders.hasNext()) {
                SOAPElement actionElement = actionHeaders.next();
                String address = actionElement.getValue();
                if (address != null) {
                    System.out.println("wsa:Action value found: " + address);
                    context.put(ACTION_KEY, address);
                    context.setScope(ACTION_KEY, MessageContext.Scope.APPLICATION);
                } // end if
                return true;
            } // end if
            return false;

        }

        private boolean handleScaPropsHeader(SOAPHeader soapHeader, SOAPMessageContext context) {
            Iterator<SOAPElement> scaPropsHeaders = (Iterator<SOAPElement>) soapHeader.getChildElements(new QName(XMLNS_TEST, SCA_PROPS));
            if (scaPropsHeaders.hasNext()) {
                SOAPElement scaPropsElement = scaPropsHeaders.next();
                String address = scaPropsElement.getValue();
                if (address != null) {
                    System.out.println("test:SCAProps value found: " + address);
                    context.put(SCAPROPS_KEY, address);
                    context.setScope(SCAPROPS_KEY, MessageContext.Scope.APPLICATION);
                } // end if
                return true;
            } // end if
            return false;

        }

        /**
         * Extract the return address data contained within a SOAP From: or ReplyTo: element and store it in the SOAP message context so that it can be read
         * and used
         * by the application code, under the label "returnAddress"
         *
         * @param element the SOAPElement containing the From: or ReplyTo:
         * @param context the SOAP message context for this message
         *                <p/>
         *                The expectation is that the From: or ReplyTo: element will contain a <wsa:Address/> subelement which contains the actual return address as
         *                its text content
         */
        @SuppressWarnings("unchecked")
        private void extractReturnAddress(SOAPElement element, SOAPMessageContext context) {

            Iterator<SOAPElement> addresses = (Iterator<SOAPElement>) element.getChildElements(new QName(XMLNS_WSA, "Address"));
            if (addresses.hasNext()) {
                String address = addresses.next().getValue();

                if (address != null) {
                    System.out.println("Address found: " + address);
                    context.put(ADDRESS_KEY, address);
                    context.setScope(ADDRESS_KEY, MessageContext.Scope.APPLICATION);
                } // end if
            } // end if

        } // end method extractReturnAddress

    } // end class SCACallbackHandler

} // end class BWS_JAXWSTestRunner
