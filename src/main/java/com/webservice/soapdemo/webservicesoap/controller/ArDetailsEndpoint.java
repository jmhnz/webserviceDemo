package com.webservice.soapdemo.webservicesoap.controller;

import com.oracle.xmlns.brm.schemas.businessopcodes.VfEaiOpEnquiryArDetailsRequest;
import com.oracle.xmlns.brm.schemas.businessopcodes.VfEaiOpEnquiryArDetailsResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.util.List;

@Slf4j
@Endpoint
public class ArDetailsEndpoint {
    private static final String NAMESPACE_URI="http://xmlns.oracle.com/BRM/schemas/BusinessOpcodes";
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "vfEaiOpEnquiryArDetailsRequest")
    @ResponsePayload
    public VfEaiOpEnquiryArDetailsResponse handleRequest(@RequestPayload VfEaiOpEnquiryArDetailsRequest request) {
        log.info("handleRequest");
//        log.info(request.getVFEAIOPENQUIRYARDETAILSRequest().getVFEAIOPENQUIRYARDETAILSInputFlist().getACCOUNTNO());
        VfEaiOpEnquiryArDetailsResponse response = new VfEaiOpEnquiryArDetailsResponse();

        response.setPOID(request.getVFEAIOPENQUIRYARDETAILSRequest().getVFEAIOPENQUIRYARDETAILSInputFlist().getPOID());
        response.setDUE("81.25");
        List<VfEaiOpEnquiryArDetailsResponse.RESULTS> results = response.getRESULTS();

        VfEaiOpEnquiryArDetailsResponse.RESULTS result0 = new VfEaiOpEnquiryArDetailsResponse.RESULTS();
        VfEaiOpEnquiryArDetailsResponse.RESULTS result1 = new VfEaiOpEnquiryArDetailsResponse.RESULTS();


        result0.setElem("0");
        result0.setAMOUNT("-21");
        result0.setCHANNEL("NZ POST");
        result0.setTYPESTR("Payment");
        result0.setVFFLDPAYMENTMETHOD("(RT)");

        result1.setElem("1");
        result1.setAMOUNT("-11");
        result1.setCHANNEL("Access Charges");
        result1.setTYPESTR("Payment");
        result1.setVFFLDPAYMENTMETHOD("Adjustment");

        results.add(result0);
        results.add(result1);

        log.info("request: {}, response: {}", request.getVFEAIOPENQUIRYARDETAILSRequest(), response);
        return response;
    }

}
