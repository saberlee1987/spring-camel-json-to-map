package com.saber.spring_camel_json_to_map.routes;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.stereotype.Component;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

@Component
public class JsonToMapRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {

        from("direct:json-to-map-route")
                .routeId("json-to-map-route")
                .routeGroup("json-to-map-route-group")
                .unmarshal().json(JsonLibrary.Jackson)
                .process(exchange -> {
                    StringBuilder fieldNames = new StringBuilder("(");
                    StringBuilder fieldValues = new StringBuilder("(");
                    @SuppressWarnings("ALL")
                    Map<String, Object> jsonMap = (Map<String, Object>) exchange.getIn().getBody();
                    Set<String> keySet = jsonMap.keySet();
                    Iterator<String> iterator = keySet.iterator();
                    String key;

                    while (iterator.hasNext()) {
                        key = iterator.next();
                        fieldNames.append(key).append(",");
                        fieldValues.append(":#").append(key).append(",");
                    }
                    fieldNames.append(")");
                    fieldValues.append(")");
                    String fields = fieldNames.toString();
                    String values = fieldValues.toString();
                    fields = fields.replace(",)", ")");
                    values = values.replace(",)", ")");

                    System.out.println(fields);
                    System.out.println(" ============================================================================= \n\n\n");
                    System.out.println(values);

                    System.out.println("========================================================================\n\n\n");
                    String query = String.format("insert into gateway %s values %s", fields, values);

                    System.out.println(query);

                    exchange.getIn().setBody(query);
                })
                .setHeader(Exchange.HTTP_RESPONSE_CODE, constant(200));
    }
}
