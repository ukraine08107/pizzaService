package whz.pti.eva.pizza_service_g11.payment.domain.dto;

public class AccountResponseDTO {

    private String code;

    public AccountResponseDTO() {

    }

    public AccountResponseDTO(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
