package pl.sda.currencyexchanger.exchanger;

public enum NbpExchangeRateExecutionStatus {
    POSITIVE("pozytywny"),
    NEGATIVE("negatywny");

    private String status;

    NbpExchangeRateExecutionStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
