package com.saber.spring_camel_json_to_map;

import org.apache.camel.Exchange;
import org.apache.camel.ProducerTemplate;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringCamelJsonToMapApplicationTests {

    @Autowired
    private ProducerTemplate producerTemplate;

    @Test
    void contextLoads() {
        String json="{\n" +
                "  \"fullMessage\": \"{\\\"ip\\\":\\\"10.39.241.198\\\",\\\"userAgent\\\":\\\"Apache-HttpClient/4.5.3 (Java/1.8.0_102)\\\",\\\"count_ip_service_method\\\":\\\"10.39.241.198-nahab-get-person-inquiry-nid-731108.0\\\",\\\"requestURI\\\":\\\"/services/nahab/get-person-inquiry-nid\\\",\\\"count_total_service_method\\\":\\\"nahab-get-person-inquiry-nid-1884838.0\\\",\\\"body\\\":\\\"{\\\\\\\"personTypeCode\\\\\\\":\\\\\\\"1\\\\\\\",\\\\\\\"nationalTypeCode\\\\\\\":\\\\\\\"1\\\\\\\",\\\\\\\"online\\\\\\\":false,\\\\\\\"serviceCode\\\\\\\":1004,\\\\\\\"realPersonIranianNationalCode\\\\\\\":{\\\\\\\"nationalCode\\\\\\\":\\\\\\\"2122537272\\\\\\\"},\\\\\\\"legalPersonIranianNationalId\\\\\\\":{\\\\\\\"nationalId\\\\\\\":\\\\\\\"00000000000\\\\\\\"},\\\\\\\"realPersonNonIranianNationalCode\\\\\\\":{\\\\\\\"faragirCode\\\\\\\":\\\\\\\"0\\\\\\\"},\\\\\\\"legalPersonNonIranianFaragirCode\\\\\\\":{\\\\\\\"faragirCode\\\\\\\":\\\\\\\"0\\\\\\\"}}\\\",\\\"queryString\\\":\\\"\\\",\\\"serviceName\\\":\\\"nahab\\\",\\\"httpMethod\\\":\\\"POST\\\",\\\"type\\\":\\\"request\\\",\\\"receiveRequestDate\\\":\\\"2022-01-04 07:27:27 AM\\\",\\\"uri\\\":\\\"/services/nahab/get-person-inquiry-nid\\\",\\\"url\\\":\\\"http://10.39.15.26:8760/services/nahab/get-person-inquiry-nid\\\",\\\"calledMethodName\\\":\\\"called nahab get-person-inquiry-nid\\\",\\\"accept\\\":\\\"application/json, application/*+json\\\",\\\"count_total_service\\\":\\\"nahab-1902238.0\\\",\\\"correlation\\\":\\\"5393c3a2-5951-4c86-b93f-719afe86d505\\\",\\\"startTime\\\":\\\"2022-01-04T07:27:27.494\\\",\\\"count_ip_service\\\":\\\"10.39.241.198-nahab-731108.0\\\",\\\"username\\\":\\\"tempmobile\\\"}\",\n" +
                "  \"userAgent\": \"Apache-HttpClient/4.5.3 (Java/1.8.0_102)\",\n" +
                "  \"ip\": \"10.39.241.198\",\n" +
                "  \"serviceName\": \"nahab\",\n" +
                "  \"count_ip_service_method\": \"10.39.241.198-nahab-get-person-inquiry-nid-731108.0\",\n" +
                "  \"count_total_service\": \"nahab-1902238.0\",\n" +
                "  \"count_total_service_method\": \"nahab-get-person-inquiry-nid-1884838.0\",\n" +
                "  \"requestURI\": \"/services/nahab/get-person-inquiry-nid\",\n" +
                "  \"calledMethodName\": \"called nahab get-person-inquiry-nid\",\n" +
                "  \"uri\": \"/services/nahab/get-person-inquiry-nid\",\n" +
                "  \"body\": \"{\\\"personTypeCode\\\":\\\"1\\\",\\\"nationalTypeCode\\\":\\\"1\\\",\\\"online\\\":false,\\\"serviceCode\\\":1004,\\\"realPersonIranianNationalCode\\\":{\\\"nationalCode\\\":\\\"2122537272\\\"},\\\"legalPersonIranianNationalId\\\":{\\\"nationalId\\\":\\\"00000000000\\\"},\\\"realPersonNonIranianNationalCode\\\":{\\\"faragirCode\\\":\\\"0\\\"},\\\"legalPersonNonIranianFaragirCode\\\":{\\\"faragirCode\\\":\\\"0\\\"}}\",\n" +
                "  \"queryString\": \"\",\n" +
                "  \"httpMethod\": \"POST\",\n" +
                "  \"type\": \"request\",\n" +
                "  \"receiveRequestDate\": \"2022-01-04 07:27:27 AM\",\n" +
                "  \"url\": \"http://10.39.15.26:8760/services/nahab/get-person-inquiry-nid\",\n" +
                "  \"accept\": \"application/json, application/*+json\",\n" +
                "  \"correlation\": \"5393c3a2-5951-4c86-b93f-719afe86d505\",\n" +
                "  \"startTime\": \"2022-01-04T07:27:27.494\",\n" +
                "  \"count_ip_service\": \"10.39.241.198-nahab-731108.0\",\n" +
                "  \"username\": \"tempmobile\"\n" +
                "}";
        Exchange responseExchange = producerTemplate.send("direct:json-to-map-route", exchange -> {
            exchange.getIn().setBody(json);
        });

        int statusCode = responseExchange.getIn().getHeader(Exchange.HTTP_RESPONSE_CODE,Integer.class);
        String responseBody =responseExchange.getIn().getBody(String.class);

        System.out.println("statusCode === "+statusCode);
        System.out.println("responseBody === "+responseBody);

    }

}
