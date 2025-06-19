package com.example.Keycloak.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.mapstruct.Mapper;

import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "response_table")
public class Response {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "text")
    private String text;

    @Column(name = "createdAt")
    private LocalDate createdAt;

    @ManyToOne
    @JoinColumn(name = "responder_id")
    private User responder;

    @ManyToOne
    @JoinColumn(name = "complaint_id")
    private Complaint complaint;
}
