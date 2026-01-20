package edu.icet.controller;

import edu.icet.model.AuditLog;
import edu.icet.repository.AuditLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/audit")
@RequiredArgsConstructor
public class AuditController {

    private final AuditLogRepository auditLogRepository;

    @GetMapping
    public List<AuditLog> getAllLogs() {
        return auditLogRepository.findAll();
    }
}