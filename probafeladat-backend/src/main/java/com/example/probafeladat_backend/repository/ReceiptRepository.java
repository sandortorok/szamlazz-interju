package com.example.probafeladat_backend.repository;

import com.example.probafeladat_backend.entity.ReceiptEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReceiptRepository extends JpaRepository<ReceiptEntity, Long> {

    Optional<ReceiptEntity> findByReceiptNumber(String receiptNumber);

    List<ReceiptEntity> findBySuccessTrue();

    List<ReceiptEntity> findByCancelledFalse();
}
