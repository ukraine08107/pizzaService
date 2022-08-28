package whz.pti.eva.pizza_service_g11.payment.domain.dto;

import java.io.Serializable;

public class TransferDTO implements Serializable{

    String to ;
    int amount;

    public TransferDTO() {
    }

    public TransferDTO(int amount) {
        this.amount = amount;
        this.to = "mainAccount";
    }

    public int getAmount() {
        return amount;
    }

    public String getTo() {
        return to;
    }
}
