package tacos;

import java.util.ArrayList;
import java.util.List;

public class TacoOrder {
    private String deliveryCity;
    private String deliveryStreet;
    private String deliveryName;
    private String deliveryState;
    private String deliveryZip;
    private String ccNumber;
    private String ccExpiration;
    private String ccCVV;

    private List<Taco> tacos = new ArrayList<>();

    public TacoOrder() {
    }

    public TacoOrder(
            String deliveryCity,
            String deliveryStreet,
            String deliveryName,
            String deliveryState,
            String deliveryZip,
            String ccNumber,
            String ccExpiration,
            String ccCVV,
            List<Taco> tacos
    ) {
        this.deliveryCity = deliveryCity;
        this.deliveryStreet = deliveryStreet;
        this.deliveryName = deliveryName;
        this.deliveryState = deliveryState;
        this.deliveryZip = deliveryZip;
        this.ccNumber = ccNumber;
        this.ccExpiration = ccExpiration;
        this.ccCVV = ccCVV;
        this.tacos = tacos;
    }

    public void addTaco(Taco taco) {
        this.tacos.add(taco);
    }

    public String getDeliveryCity() {
        return deliveryCity;
    }

    public void setDeliveryCity(String deliveryCity) {
        this.deliveryCity = deliveryCity;
    }

    public String getDeliveryStreet() {
        return deliveryStreet;
    }

    public void setDeliveryStreet(String deliveryStreet) {
        this.deliveryStreet = deliveryStreet;
    }

    public String getDeliveryName() {
        return deliveryName;
    }

    public void setDeliveryName(String deliveryName) {
        this.deliveryName = deliveryName;
    }

    public String getDeliveryState() {
        return deliveryState;
    }

    public void setDeliveryState(String deliveryState) {
        this.deliveryState = deliveryState;
    }

    public String getDeliveryZip() {
        return deliveryZip;
    }

    public void setDeliveryZip(String deliveryZip) {
        this.deliveryZip = deliveryZip;
    }

    public String getCcNumber() {
        return ccNumber;
    }

    public void setCcNumber(String ccNumber) {
        this.ccNumber = ccNumber;
    }

    public String getCcExpiration() {
        return ccExpiration;
    }

    public void setCcExpiration(String ccExpiration) {
        this.ccExpiration = ccExpiration;
    }

    public String getCcCVV() {
        return ccCVV;
    }

    public void setCcCVV(String ccCVV) {
        this.ccCVV = ccCVV;
    }

    public List<Taco> getTacos() {
        return tacos;
    }

    public void setTacos(List<Taco> tacos) {
        this.tacos = tacos;
    }
}
