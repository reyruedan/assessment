package com.assessment.adapter.starter;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

@Service
public class JsonService {


    private final JsonPaymentRepository jsonPaymentRepository;
    private final JsonProductRepository jsonProductRepository;
    private final JsonOrderRepository jsonOrderRepository;

    public JsonService(JsonPaymentRepository jsonPaymentRepository, JsonProductRepository jsonProductRepository, JsonOrderRepository jsonOrderRepository) {
        this.jsonPaymentRepository = jsonPaymentRepository;
        this.jsonProductRepository = jsonProductRepository;
        this.jsonOrderRepository = jsonOrderRepository;
    }

    private final ObjectMapper objectMapper = new ObjectMapper();


    public void saveJsonArrayAsIndividualFiles(String jsonArrayString, String fileName) throws Exception {
        JsonNode arrayNode = objectMapper.readTree(jsonArrayString);

        if (arrayNode.isArray()) {
            for (JsonNode jsonNode : arrayNode) {

                String singleJsonString = objectMapper.writeValueAsString(jsonNode);

                switch (fileName) {
                    case "orders.json":
                        JsonOrdersFile jsonOrdersFile = new JsonOrdersFile();
                        jsonOrdersFile.setContent(singleJsonString);
                        jsonOrderRepository.save(jsonOrdersFile);
                        break;
                    case "payments.json":
                        JsonPaymentsFile jsonPaymentsFile = new JsonPaymentsFile();
                        jsonPaymentsFile.setContent(singleJsonString);
                        jsonPaymentRepository.save(jsonPaymentsFile);
                        break;
                    default:
                        JsonProductsFile jsonProductsFile = new JsonProductsFile();
                        jsonProductsFile.setContent(singleJsonString);
                        jsonProductRepository.save(jsonProductsFile);
                        break;
                }
        }
        } else {
            throw new IllegalArgumentException("El JSON proporcionado no es un array.");
        }
    }
}
