package com.example.probafeladat_backend.services;

import com.example.probafeladat_backend.dto.request.ReceiptRequest;
import com.example.probafeladat_backend.dto.response.ReceiptResponse;
import com.example.probafeladat_backend.dto.response.ReceiptSummaryDto;
import com.example.probafeladat_backend.entity.ReceiptEntity;
import com.example.probafeladat_backend.exception.ExternalApiException;
import com.example.probafeladat_backend.exception.ReceiptNotFoundException;
import com.example.probafeladat_backend.mapper.ReceiptMapper;
import com.example.probafeladat_backend.repository.ReceiptRepository;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.http.client.MultipartBodyBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Service
public class ReceiptService {

    private static final String BASE_URL = "https://www.szamlazz.hu/szamla/";
    private static final String FILE_NAME = "action-szamla_agent_nyugta_create";

    @Value("${szamlazz.api.key}")
    private String apiKey;

    private final WebClient webClient;
    private final JAXBContext requestJaxbContext;
    private final JAXBContext responseJaxbContext;
    private final ReceiptMapper receiptMapper;
    private final ReceiptRepository receiptRepository;

    public ReceiptService(WebClient webClient, ReceiptMapper receiptMapper, ReceiptRepository receiptRepository) throws JAXBException {
        this.webClient = webClient;
        this.receiptMapper = receiptMapper;
        this.receiptRepository = receiptRepository;
        this.requestJaxbContext = JAXBContext.newInstance(ReceiptRequest.class);
        this.responseJaxbContext = JAXBContext.newInstance(ReceiptResponse.class);
    }

    @Transactional
    public ReceiptResponse testWithSample() throws IOException, JAXBException {
        ClassPathResource resource = new ClassPathResource("samples/receipt-sample.xml");
        String xmlContent = resource.getContentAsString(StandardCharsets.UTF_8);
        xmlContent = xmlContent.replace("{{SZAMLAZZ_API_KEY}}", apiKey);
        return sendToSzamlazz(xmlContent);
    }

    @Transactional
    public ReceiptResponse createReceipt(ReceiptRequest request) throws JAXBException {
        String xmlContent = marshalRequest(request);
        return sendToSzamlazz(xmlContent);
    }

    private String marshalRequest(ReceiptRequest request) throws JAXBException {
        StringWriter writer = new StringWriter();
        Marshaller marshaller = requestJaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
        marshaller.marshal(request, writer);
        return writer.toString();
    }

    private ReceiptResponse sendToSzamlazz(String xmlContent) throws JAXBException {
        MultipartBodyBuilder builder = new MultipartBodyBuilder();
        builder.part(FILE_NAME, new ByteArrayResource(xmlContent.getBytes()) {
            @Override
            public String getFilename() {
                return "request.xml";
            }
        }).contentType(MediaType.APPLICATION_XML);

        String xmlResponse = webClient.post()
                .uri(BASE_URL)
                .contentType(MediaType.MULTIPART_FORM_DATA)
                .body(BodyInserters.fromMultipartData(builder.build()))
                .retrieve()
                .bodyToMono(String.class)
                .block();

        ReceiptResponse response = parseResponse(xmlResponse);
        
        if (!response.isSuccess()) {
            throw new ExternalApiException(response.getErrorMessage(), String.valueOf(response.getErrorCode()));
        }
        
        saveReceiptToDatabase(response);
        return response;
    }

    private ReceiptResponse parseResponse(String xmlResponse) throws JAXBException {
        Unmarshaller unmarshaller = responseJaxbContext.createUnmarshaller();
        return (ReceiptResponse) unmarshaller.unmarshal(new StringReader(xmlResponse));
    }

    private void saveReceiptToDatabase(ReceiptResponse response) {
        ReceiptEntity entity = receiptMapper.responseToEntity(response);
        receiptRepository.save(entity);
    }

    @Transactional(readOnly = true)
    public List<ReceiptSummaryDto> getAllReceipts() {
        return receiptRepository.findAll().stream()
                .map(entity -> new ReceiptSummaryDto(
                        entity.getId(),
                        entity.getReceiptNumber(),
                        entity.getDate(),
                        entity.getTotalNet(),
                        entity.getTotalGross()
                ))
                .toList();
    }

    @Transactional(readOnly = true)
    public ReceiptEntity getReceiptById(Long id) {
        return receiptRepository.findById(id)
                .orElseThrow(() -> new ReceiptNotFoundException(id));
    }
}
