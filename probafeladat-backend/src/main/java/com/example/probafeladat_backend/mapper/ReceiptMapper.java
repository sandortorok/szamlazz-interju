package com.example.probafeladat_backend.mapper;

import com.example.probafeladat_backend.dto.response.ReceiptResponse;
import com.example.probafeladat_backend.dto.response.Receipt;
import com.example.probafeladat_backend.dto.response.ResponseItem;
import com.example.probafeladat_backend.dto.response.ResponsePayment;
import com.example.probafeladat_backend.dto.response.VatRateSummary;
import com.example.probafeladat_backend.entity.ReceiptEntity;
import com.example.probafeladat_backend.entity.ReceiptItemEntity;
import com.example.probafeladat_backend.entity.PaymentEntity;
import com.example.probafeladat_backend.entity.VatRateSummaryEntity;
import org.springframework.stereotype.Component;

@Component
public class ReceiptMapper {

    public ReceiptEntity responseToEntity(ReceiptResponse response) {
        if (response == null) {
            return null;
        }

        ReceiptEntity entity = new ReceiptEntity();

        entity.setSuccess(response.isSuccess());
        entity.setErrorCode(response.getErrorCode());
        entity.setErrorMessage(response.getErrorMessage());
        entity.setReceiptPdf(response.getReceiptPdf());

        Receipt receipt = response.getReceipt();
        if (receipt != null) {
            if (receipt.getBase() != null) {
                entity.setReceiptId(receipt.getBase().getId());
                entity.setCallId(receipt.getBase().getCallId());
                entity.setReceiptNumber(receipt.getBase().getReceiptNumber());
                entity.setType(receipt.getBase().getType());
                entity.setCancelled(receipt.getBase().isCancelled());
                entity.setCancelledReceiptNumber(receipt.getBase().getCancelledReceiptNumber());
                entity.setDate(receipt.getBase().getDate());
                entity.setPaymentMethod(receipt.getBase().getPaymentMethod());
                entity.setCurrency(receipt.getBase().getCurrency());
                entity.setExchangeBank(receipt.getBase().getExchangeBank());
                entity.setExchangeRate(receipt.getBase().getExchangeRate());
                entity.setComment(receipt.getBase().getComment());
                entity.setLedgerCustomer(receipt.getBase().getLedgerCustomer());
                entity.setTest(receipt.getBase().isTest());
            }

            if (receipt.getTotals() != null && receipt.getTotals().getTotalSum() != null) {
                entity.setTotalNet(receipt.getTotals().getTotalSum().getNetTotal());
                entity.setTotalVat(receipt.getTotals().getTotalSum().getVatTotal());
                entity.setTotalGross(receipt.getTotals().getTotalSum().getGrossTotal());
            }

            if (receipt.getTotals() != null && receipt.getTotals().getVatRateSummaries() != null) {
                for (VatRateSummary vatSummary : receipt.getTotals().getVatRateSummaries()) {
                    entity.addVatRateSummary(toVatRateSummaryEntity(vatSummary));
                }
            }

            if (receipt.getItems() != null && receipt.getItems().getItems() != null) {
                for (ResponseItem item : receipt.getItems().getItems()) {
                    entity.addItem(toItemEntity(item));
                }
            }

            if (receipt.getPayments() != null && receipt.getPayments().getPayments() != null) {
                for (ResponsePayment payment : receipt.getPayments().getPayments()) {
                    entity.addPayment(toPaymentEntity(payment));
                }
            }
        }

        return entity;
    }

    private ReceiptItemEntity toItemEntity(ResponseItem item) {
        if (item == null) {
            return null;
        }

        ReceiptItemEntity entity = new ReceiptItemEntity();
        entity.setItemId(item.getItemId());
        entity.setName(item.getName());
        entity.setQuantity(item.getQuantity());
        entity.setUnit(item.getUnit());
        entity.setNetUnitPrice(item.getNetUnitPrice());
        entity.setNetTotal(item.getNetTotal());
        entity.setVatType(item.getVatType());
        entity.setVatRate(item.getVatRate());
        entity.setVatAmount(item.getVatAmount());
        entity.setGrossTotal(item.getGrossTotal());
        return entity;
    }

    private PaymentEntity toPaymentEntity(ResponsePayment payment) {
        if (payment == null) {
            return null;
        }

        PaymentEntity entity = new PaymentEntity();
        entity.setPaymentTool(payment.getPaymentTool());
        entity.setAmount(payment.getAmount());
        entity.setDescription(payment.getDescription());
        return entity;
    }

    private VatRateSummaryEntity toVatRateSummaryEntity(VatRateSummary vatSummary) {
        if (vatSummary == null) {
            return null;
        }

        VatRateSummaryEntity entity = new VatRateSummaryEntity();
        entity.setVatType(vatSummary.getVatType());
        entity.setVatRate(vatSummary.getVatRate());
        entity.setNetTotal(vatSummary.getNetTotal());
        entity.setVatAmount(vatSummary.getVatAmount());
        entity.setGrossTotal(vatSummary.getGrossTotal());
        return entity;
    }
}
