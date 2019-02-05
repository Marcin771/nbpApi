package pl.sda.currencyexchanger.model;

public enum Currency {
    EURO("EUR");

    private String code;

    Currency(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
