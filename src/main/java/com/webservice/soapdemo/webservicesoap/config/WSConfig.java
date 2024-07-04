package com.webservice.soapdemo.webservicesoap.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

@Configuration
@EnableWs
@Slf4j
public class WSConfig extends WsConfigurerAdapter {

    @Bean
    public ServletRegistrationBean<MessageDispatcherServlet> messageDispatcherServlet(ApplicationContext applicationContext){
        log.info("messageDispatcherServlet -- 1");
        MessageDispatcherServlet servlet = new MessageDispatcherServlet();
        servlet.setApplicationContext(applicationContext);
        servlet.setTransformWsdlLocations(true);
        return new ServletRegistrationBean<>(servlet, "/BrmWebServices/*");
    }

    @Bean
    public XsdSchema vfEaiOpEnquiryArDetailsSchema() {
        log.info("vfEaiOpEnquiryArDetailsSchema -- 1");
        return new SimpleXsdSchema(new ClassPathResource("VF_EAI_OP_ENQUIRY_AR_DETAILS.xsd"));
    }

    // BrmWebServices/BRMCustomPaymentServices_v2?WSDL
    @Bean(name = "BRMCustomPaymentServices_v2")
    public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema vfEaiOpEnquiryArDetailsSchema) {
        log.info("DefaultWsdl11Definition -- 1");
        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
        wsdl11Definition.setPortTypeName("VF_EAI_OP_ENQUIRY_AR_DETAILS_RequestType");
        wsdl11Definition.setLocationUri("/BrmWebServices/BRMCustomPaymentServices_v2");
        wsdl11Definition.setTargetNamespace("http://xmlns.oracle.com/BRM/schemas/BusinessOpcodes");
        wsdl11Definition.setSchema(vfEaiOpEnquiryArDetailsSchema);

        return wsdl11Definition;
    }

}
