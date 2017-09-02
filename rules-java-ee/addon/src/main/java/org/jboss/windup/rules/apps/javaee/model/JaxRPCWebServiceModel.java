package org.jboss.windup.rules.apps.javaee.model;

import org.jboss.windup.rules.apps.java.model.JavaClassModel;
import org.jboss.windup.rules.apps.xml.model.XmlFileModel;

import com.tinkerpop.blueprints.Direction;
import com.tinkerpop.frames.Adjacency;
import com.tinkerpop.frames.modules.typedgraph.TypeValue;

/**
 * JAX-RPC Web Service.
 * 
 * @author <a href="mailto:bradsdavis@gmail.com">Brad Davis</a>
 */
@TypeValue(JaxRPCWebServiceModel.TYPE)
public interface JaxRPCWebServiceModel extends WebServiceModel
{
    String TYPE = "JaxRPCService";
    String JAXRPC_IMPLEMENTATION_CLASS = "jaxrpcImplementationClass";
    String JAXRPC_INTERFACE = "jaxrpcInterface";
    String JAXRPC_XML_DESCRIPTOR = "jaxrpcXmlDescriptor";
    
    /**
     * Contains the JAX-RPC implementation class
     */
    @Adjacency(label = JAXRPC_IMPLEMENTATION_CLASS, direction = Direction.OUT)
    void setImplementationClass(JavaClassModel implRef);

    /**
     * Contains the JAX-RPC implementation class
     */
    @Adjacency(label = JAXRPC_IMPLEMENTATION_CLASS, direction = Direction.OUT)
    JavaClassModel getImplementationClass();

    /**
     * Contains the JAX-RPC implementation class
     */
    @Adjacency(label = JAXRPC_INTERFACE, direction = Direction.OUT)
    void setInterface(JavaClassModel interfaceRef);

    /**
     * Contains the JAX-RPC implementation class
     */
    @Adjacency(label = JAXRPC_INTERFACE, direction = Direction.OUT)
    JavaClassModel getInterface();
    
    
    /**
     * Contains the JAX-WS implementation class
     */
    @Adjacency(label = JAXRPC_XML_DESCRIPTOR, direction = Direction.OUT)
    void setXmlDescriptor(XmlFileModel xmlFile);

    /**
     * Contains the JAX-WS implementation class
     */
    @Adjacency(label = JAXRPC_XML_DESCRIPTOR, direction = Direction.OUT)
    XmlFileModel getXmlDescriptor();
}
