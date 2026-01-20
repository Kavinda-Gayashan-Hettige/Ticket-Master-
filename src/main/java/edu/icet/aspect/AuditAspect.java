package edu.icet.aspect;

import edu.icet.model.AuditLog;
import edu.icet.repository.AuditLogRepository;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Aspect
@Component
@RequiredArgsConstructor
public class AuditAspect {

    private final AuditLogRepository auditLogRepository;


    @AfterThrowing(pointcut = "@annotation(AuditFailure)", throwing = "ex")
    public void logFailure(JoinPoint joinPoint, Exception ex) {
        AuditLog log = new AuditLog();
        log.setAction(joinPoint.getSignature().getName());
        log.setDetails(ex.getMessage());
        log.setTimestamp(LocalDateTime.now());


        if (joinPoint.getArgs().length > 1) {
            log.setUserId((Long) joinPoint.getArgs()[1]);
        }

        auditLogRepository.save(log);
        System.out.println("Audit Log Saved: Failure in " + log.getAction());
    }
}