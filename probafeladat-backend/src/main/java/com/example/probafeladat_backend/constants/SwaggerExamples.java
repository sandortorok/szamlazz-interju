package com.example.probafeladat_backend.constants;

public class SwaggerExamples {

    public static final String RECEIPT_REQUEST_MINIMAL = """
        {
          "settings": {
            "apiKey": "your_api_key_here",
            "pdfDownload": false
          },
          "header": {
            "prefix": "PQRST",
            "paymentMethod": "készpénz",
            "currency": "Ft",
            "comment": "Teszt nyugta"
          },
          "items": {
            "items": [
              {
                "name": "Teszt termék",
                "itemId": "PROD-001",
                "quantity": 1,
                "unit": "db",
                "netUnitPrice": 10000,
                "vatRate": "10",
                "netTotal": 10000,
                "vatAmount": 1000,
                "grossTotal": 11000
              }
            ]
          }
        }
        """;

    public static final String RECEIPT_RESPONSE_SUCCESS = """
        {
          "errorCode": null,
          "errorMessage": null,
          "receipt": {
            "base": {
              "callId": null,
              "cancelled": false,
              "cancelledReceiptNumber": null,
              "comment": null,
              "currency": "Ft",
              "date": "2026-01-21",
              "exchangeBank": null,
              "exchangeRate": null,
              "id": 73091893,
              "ledgerCustomer": null,
              "paymentMethod": "készpénz",
              "receiptNumber": "PQRST-2026-11",
              "test": true,
              "type": "NY"
            },
            "items": {
              "items": [
                {
                  "grossTotal": 25400,
                  "itemId": null,
                  "ledger": {
                    "revenue": "...",
                    "vat": "..."
                  },
                  "name": "Kitten doormat",
                  "netTotal": 20000,
                  "netUnitPrice": 10000,
                  "quantity": 2,
                  "unit": "db",
                  "vatAmount": 5400,
                  "vatRate": 27,
                  "vatType": null
                },
                {
                  "grossTotal": 25400,
                  "itemId": null,
                  "ledger": null,
                  "name": "Puppy doormat",
                  "netTotal": 20000,
                  "netUnitPrice": 10000,
                  "quantity": 2,
                  "unit": "db",
                  "vatAmount": 5400,
                  "vatRate": 27,
                  "vatType": null
                }
              ]
            },
            "payments": {
              "payments": [
                {
                  "amount": 30000,
                  "description": "OTP SZÉP kártya",
                  "paymentTool": "voucher"
                },
                {
                  "amount": 20800,
                  "description": null,
                  "paymentTool": "debit card"
                }
              ]
            },
            "totals": {
              "totalSum": {
                "grossTotal": 50800,
                "netTotal": 40000,
                "vatTotal": 10800
              },
              "vatRateSummaries": [
                {
                  "grossTotal": 50800,
                  "netTotal": 40000,
                  "vatAmount": 10800,
                  "vatRate": 27,
                  "vatType": null
                }
              ]
            }
          },
          "receiptPdf": null,
          "success": true
        }
        """;

    private SwaggerExamples() {
        throw new UnsupportedOperationException("Utility class");
    }
}
