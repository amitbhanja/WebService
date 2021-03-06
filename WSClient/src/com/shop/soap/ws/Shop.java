
package com.shop.soap.ws;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.Action;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.4-b01
 * Generated source version: 2.2
 * 
 */
@WebService(name = "Shop", targetNamespace = "http://ws.soap.shop.com/")
@SOAPBinding(style = SOAPBinding.Style.RPC)
@XmlSeeAlso({
    ObjectFactory.class
})
public interface Shop {


    /**
     * 
     * @return
     *     returns com.shop.soap.ws.ProductArray
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://ws.soap.shop.com/Shop/showProductsRequest", output = "http://ws.soap.shop.com/Shop/showProductsResponse")
    public ProductArray showProducts();

    /**
     * 
     * @param arg0
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://ws.soap.shop.com/Shop/placeOrderRequest", output = "http://ws.soap.shop.com/Shop/placeOrderResponse")
    public String placeOrder(
        @WebParam(name = "arg0", partName = "arg0")
        ProductArray arg0);

}
