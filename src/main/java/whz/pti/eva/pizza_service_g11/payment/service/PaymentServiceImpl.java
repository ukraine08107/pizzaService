package whz.pti.eva.pizza_service_g11.payment.service;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;
import whz.pti.eva.pizza_service_g11.payment.domain.dto.AccountResponseDTO;
import whz.pti.eva.pizza_service_g11.payment.domain.dto.TransferDTO;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Value("${my.payment.url}")
    String MyUrl;

    @Value("${my.payment.plainCreds}")
    String plainCreds;

    @Override
    public AccountResponseDTO doPayAction(String from, int amount) {
        String uriReturn;
        ResponseEntity<?> response = null;
        RestTemplate restTemplate = new RestTemplate();
        byte[] plainCredsBytes = plainCreds.getBytes();
        byte[] base64CredsBytes = Base64.encodeBase64(plainCredsBytes);
        String base64Creds = new String(base64CredsBytes);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Basic " + base64Creds);
        HttpEntity<String> request = new HttpEntity<String>(headers);
        try {
            headers.setContentType(MediaType.APPLICATION_JSON);
            TransferDTO transferDTO = new TransferDTO(amount);
            HttpEntity<TransferDTO> requestPost = new HttpEntity<>(transferDTO, headers);
            uriReturn = MyUrl + from + "/payment";
            response = restTemplate.exchange(uriReturn, HttpMethod.POST, requestPost, AccountResponseDTO.class);
        } catch (
                ResourceAccessException e) {
            response = new ResponseEntity<Object>(new AccountResponseDTO("ist nicht erfolgreich gewesen :: vlt. smmp-Dienst nicht erreichbar"), HttpStatus.OK);
        }
        AccountResponseDTO accountResponseDTO = (AccountResponseDTO) response.getBody();
        return accountResponseDTO;
    }

    @Override
    public AccountResponseDTO createAccount(String from) {
        String uriReturn;
        ResponseEntity<?> response = null;
        RestTemplate restTemplate = new RestTemplate();
        byte[] plainCredsBytes = plainCreds.getBytes();
        byte[] base64CredsBytes = Base64.encodeBase64(plainCredsBytes);
        String base64Creds = new String(base64CredsBytes);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Basic " + base64Creds);
        HttpEntity<String> request = new HttpEntity<String>(headers);
        uriReturn = MyUrl + from + "/opened";
        try {
            response = restTemplate.exchange(uriReturn, HttpMethod.PUT, request, AccountResponseDTO.class);
        } catch (
                ResourceAccessException e) {
            response = new ResponseEntity<Object>(new AccountResponseDTO("ist nicht erfolgreich gewesen :: vlt. smmp-Dienst nicht erreichbar"), HttpStatus.OK);
        }
        AccountResponseDTO accountResponseDTO = (AccountResponseDTO) response.getBody();
        return accountResponseDTO;
    }


    @Override
    public AccountResponseDTO checkBalance(String userIdFrom) {
        String uriReturn;
        ResponseEntity<?> response = null;
        RestTemplate restTemplate = new RestTemplate();
        byte[] plainCredsBytes = plainCreds.getBytes();
        byte[] base64CredsBytes = Base64.encodeBase64(plainCredsBytes);
        String base64Creds = new String(base64CredsBytes);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Basic " + base64Creds);
        HttpEntity<String> request = new HttpEntity<String>(headers);
        uriReturn = MyUrl + userIdFrom + "/account";
        try {
            response = restTemplate.exchange(uriReturn, HttpMethod.GET, request, AccountResponseDTO.class);
        } catch (
                ResourceAccessException e) {
            response = new ResponseEntity<Object>(new AccountResponseDTO("ist nicht erfolgreich gewesen :: vlt. smmp-Dienst nicht erreichbar"), HttpStatus.OK);
        }
        AccountResponseDTO accountResponseDTO = (AccountResponseDTO) response.getBody();
        return accountResponseDTO;
    }
}
