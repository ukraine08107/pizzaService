package whz.pti.eva.pizza_service_g11.payment.service;

import whz.pti.eva.pizza_service_g11.payment.domain.dto.AccountResponseDTO;

public interface PaymentService {
    AccountResponseDTO doPayAction(String from, int amount);
    AccountResponseDTO createAccount(String userIdFrom);
    AccountResponseDTO checkBalance(String userIdFrom);
}
