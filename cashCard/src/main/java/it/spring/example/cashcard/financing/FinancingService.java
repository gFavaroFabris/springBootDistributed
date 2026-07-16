package it.spring.example.cashcard.financing;

import it.spring.example.cashcard.cards.CashCardService;
import it.spring.example.cashcard.customers.CustomerService;
import it.spring.example.cashcard.financing.dto.FinancingPlanCreateRequest;
import it.spring.example.cashcard.financing.dto.FinancingPlanResponse;
import it.spring.example.cashcard.shared.exception.BusinessException;
import it.spring.example.cashcard.shared.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Servizio per la gestione dei finanziamenti
 */
@Service
@RequiredArgsConstructor
@Transactional
public class FinancingService {

    private final FinancingPlanRepository financingPlanRepository;
    private final InstallmentRepository installmentRepository;
    private final CashCardService cardService;
    private final CustomerService customerService;
    private final ModelMapper modelMapper;

    /**
     * Crea un nuovo piano di finanziamento
     */
    public FinancingPlanResponse createFinancingPlan(FinancingPlanCreateRequest request) {
        // Validazioni
//    	TODO
//        cardService.getCardById(request.getCardId());
        customerService.getCustomerById(request.getCustomerId());

        if (request.getNumberOfInstallments() < 2 || request.getNumberOfInstallments() > 60) {
            throw new BusinessException("Numero di rate deve essere tra 2 e 60", "INVALID_INSTALLMENTS");
        }

        if (request.getInterestRate().compareTo(BigDecimal.ZERO) < 0) {
            throw new BusinessException("Tasso d'interesse non può essere negativo", "INVALID_RATE");
        }

        // Calcola gli importi
        BigDecimal monthlyRate = request.getInterestRate().divide(new BigDecimal("12"), 6, RoundingMode.HALF_UP);
        BigDecimal monthlyRate1 = monthlyRate.add(BigDecimal.ONE);
        
        int n = request.getNumberOfInstallments();
        BigDecimal divisor = monthlyRate1.pow(n).subtract(BigDecimal.ONE).divide(monthlyRate, 6, RoundingMode.HALF_UP);
        BigDecimal monthlyPayment = request.getAmount().divide(divisor, 2, RoundingMode.HALF_UP);
        BigDecimal totalAmount = monthlyPayment.multiply(new BigDecimal(n));

        FinancingPlan plan = FinancingPlan.builder()
                .cardId(request.getCardId())
                .customerId(request.getCustomerId())
                .transactionId(request.getTransactionId())
                .originalAmount(request.getAmount())
                .totalAmount(totalAmount)
                .remainingAmount(totalAmount)
                .monthlyPayment(monthlyPayment)
                .interestRate(request.getInterestRate())
                .numberOfInstallments(n)
                .paidInstallments(0)
                .status("ACTIVE")
                .startDate(LocalDate.now())
                .nextPaymentDate(LocalDate.now().plusMonths(1))
                .build();

        FinancingPlan saved = financingPlanRepository.save(plan);

        // Crea le rate
        createInstallments(saved, monthlyPayment, request.getAmount());

        return modelMapper.map(saved, FinancingPlanResponse.class);
    }

    /**
     * Crea le singole rate
     */
    private void createInstallments(FinancingPlan plan, BigDecimal monthlyPayment, BigDecimal originalAmount) {
        BigDecimal monthlyRate = plan.getInterestRate().divide(new BigDecimal("12"), 6, RoundingMode.HALF_UP);
        BigDecimal remainingBalance = originalAmount;

        for (int i = 1; i <= plan.getNumberOfInstallments(); i++) {
            BigDecimal interest = remainingBalance.multiply(monthlyRate).setScale(2, RoundingMode.HALF_UP);
            BigDecimal principal = monthlyPayment.subtract(interest).setScale(2, RoundingMode.HALF_UP);

            Installment installment = Installment.builder()
                    .financingPlanId(plan.getId())
                    .installmentNumber(i)
                    .amount(monthlyPayment)
                    .principal(principal)
                    .interest(interest)
                    .status("PENDING")
                    .dueDate(LocalDate.now().plusMonths(i))
                    .build();

            installmentRepository.save(installment);
            remainingBalance = remainingBalance.subtract(principal);
        }
    }

    /**
     * Recupera un piano di finanziamento
     */
    public FinancingPlanResponse getFinancingPlan(Long planId) {
        FinancingPlan plan = financingPlanRepository.findById(planId)
                .orElseThrow(() -> new ResourceNotFoundException("Piano finanziamento", "id", planId));
        return modelMapper.map(plan, FinancingPlanResponse.class);
    }

    /**
     * Recupera i piani di finanziamento di una carta
     */
    public List<FinancingPlanResponse> getCardFinancingPlans(Long cardId) {
//    	TODO
//        cardService.getCardById(cardId);
        return financingPlanRepository.findByCardId(cardId).stream()
                .map(p -> modelMapper.map(p, FinancingPlanResponse.class))
                .collect(Collectors.toList());
    }

    /**
     * Recupera i piani attivi di un cliente
     */
    public List<FinancingPlanResponse> getActiveFinancingPlans(Long customerId) {
        return financingPlanRepository.findByCustomerIdAndStatus(customerId, "ACTIVE").stream()
                .map(p -> modelMapper.map(p, FinancingPlanResponse.class))
                .collect(Collectors.toList());
    }

    /**
     * Registra il pagamento di una rata
     */
    public void payInstallment(Long installmentId, String paymentMethod) {
        Installment installment = installmentRepository.findById(installmentId)
                .orElseThrow(() -> new ResourceNotFoundException("Rata", "id", installmentId));

        if ("PAID".equals(installment.getStatus())) {
            throw new BusinessException("Rata già pagata", "ALREADY_PAID");
        }

        installment.setStatus("PAID");
        installment.setPaymentDate(LocalDate.now());
        installment.setPaymentMethod(paymentMethod);
        installmentRepository.save(installment);

        // Aggiorna il piano
        FinancingPlan plan = financingPlanRepository.findById(installment.getFinancingPlanId())
                .orElseThrow(() -> new ResourceNotFoundException("Piano", "id", installment.getFinancingPlanId()));

        plan.setPaidInstallments(plan.getPaidInstallments() + 1);
        plan.setRemainingAmount(plan.getRemainingAmount().subtract(installment.getAmount()));
        plan.setNextPaymentDate(LocalDate.now().plusMonths(1));

        if (plan.getPaidInstallments() == plan.getNumberOfInstallments()) {
            plan.setStatus("COMPLETED");
            plan.setEndDate(LocalDate.now());
        }

        financingPlanRepository.save(plan);
    }

    /**
     * Cancella un piano di finanziamento
     */
    public void cancelFinancingPlan(Long planId, String reason) {
        FinancingPlan plan = financingPlanRepository.findById(planId)
                .orElseThrow(() -> new ResourceNotFoundException("Piano", "id", planId));

        if ("COMPLETED".equals(plan.getStatus())) {
            throw new BusinessException("Non è possibile annullare un piano completato", "CANNOT_CANCEL");
        }

        plan.setStatus("CANCELLED");
        financingPlanRepository.save(plan);

        // Annulla le rate non pagate
        List<Installment> pendingInstallments = installmentRepository.findByFinancingPlanId(planId).stream()
                .filter(i -> "PENDING".equals(i.getStatus()))
                .collect(Collectors.toList());

        for (Installment installment : pendingInstallments) {
            installment.setStatus("CANCELLED");
            installmentRepository.save(installment);
        }
    }
}
