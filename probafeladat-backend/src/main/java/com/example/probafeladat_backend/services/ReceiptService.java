package com.example.probafeladat_backend.services;

import com.example.probafeladat_backend.dto.response.ReceiptResponse;
import com.example.probafeladat_backend.dto.response.ReceiptSummaryDto;
import com.example.probafeladat_backend.entity.ReceiptEntity;
import com.example.probafeladat_backend.mapper.ReceiptMapper;
import com.example.probafeladat_backend.repository.ReceiptRepository;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
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
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReceiptService {

    private static final String BASE_URL = "https://www.szamlazz.hu/szamla/";
    private static final String FILE_NAME = "action-szamla_agent_nyugta_create";

    private final WebClient webClient;
    private final JAXBContext jaxbContext;
    private final ReceiptMapper receiptMapper;
    private final ReceiptRepository receiptRepository;

    public ReceiptService(WebClient webClient, ReceiptMapper receiptMapper, ReceiptRepository receiptRepository) throws JAXBException {
        this.webClient = webClient;
        this.receiptMapper = receiptMapper;
        this.receiptRepository = receiptRepository;
        this.jaxbContext = JAXBContext.newInstance(ReceiptResponse.class);
    }

    @Transactional
    public ReceiptResponse testWithSample() throws IOException, JAXBException {
        ClassPathResource resource = new ClassPathResource("samples/receipt-sample.xml");
        String xmlContent = resource.getContentAsString(StandardCharsets.UTF_8);
        return createReceipt(xmlContent);
    }

    @Transactional
    public ReceiptResponse createReceipt(String xmlContent) throws JAXBException {
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
        
        if (response.isSuccess()) {
            saveReceiptToDatabase(response);
        }
        
        return response;
    }

    private ReceiptResponse parseResponse(String xmlResponse) throws JAXBException {
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        return (ReceiptResponse) unmarshaller.unmarshal(new StringReader(xmlResponse));
    }

    private void saveReceiptToDatabase(ReceiptResponse response) {
        if (response != null) {
            ReceiptEntity entity = receiptMapper.responseToEntity(response);
            receiptRepository.save(entity);
        }
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
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Optional<ReceiptEntity> getReceiptById(Long id) {
        return receiptRepository.findById(id);
    }
}
