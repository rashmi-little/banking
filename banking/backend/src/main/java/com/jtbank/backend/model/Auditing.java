package com.jtbank.backend.model;

import jakarta.persistence.Embeddable;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
@Data
//@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class Auditing {
//    @CreationTimestamp
    @CreatedDate
    private LocalDateTime createdDate;
    @CreatedBy
    private String createdBy;
//    @UpdateTimestamp
    @LastModifiedDate
    private LocalDateTime updatedDate;
    @LastModifiedBy
    private String updatedBy;
}
