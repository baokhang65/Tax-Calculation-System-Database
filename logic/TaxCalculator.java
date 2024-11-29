package logic;

public class TaxCalculator {
    private long grossIncome;

    public TaxCalculator(long grossIncome) {
        this.grossIncome = grossIncome;
    }

    public long getSocialInsurance() {
        return (long) (grossIncome * 0.08);
    }

    public long getHealthInsurance() {
        return (long) (grossIncome * 0.015);
    }

    public long getUnemploymentInsurance() {
        return (long) (grossIncome * 0.01);
    }

    public long getPreTaxIncome() {
        return grossIncome - getSocialInsurance() - getHealthInsurance() - getUnemploymentInsurance();
    }

    public long getPersonalDeductions() {
        return 11000000;
    }

    public long getTaxableIncome() {
        long preTaxIncome = getPreTaxIncome();
        return Math.max(preTaxIncome - getPersonalDeductions(), 0);
    }

    public long[] calculateTaxBrackets() {
        long taxableIncome = getTaxableIncome();
        long[] taxBrackets = {5000000, 5000000, 8000000, 14000000, 20000000, 28000000, Long.MAX_VALUE};
        double[] taxRates = {0.05, 0.10, 0.15, 0.20, 0.25, 0.30, 0.35};
        long[] bracketTaxValues = new long[taxRates.length];
        long remainingTaxableIncome = taxableIncome;

        for (int i = 0; i < taxBrackets.length && remainingTaxableIncome > 0; i++) {
            long incomeInBracket = Math.min(remainingTaxableIncome, taxBrackets[i]);
            bracketTaxValues[i] = (long) (incomeInBracket * taxRates[i]);
            remainingTaxableIncome -= incomeInBracket;
        }

        return bracketTaxValues;
    }

    public long getFinalTax() {
        long[] bracketTaxValues = calculateTaxBrackets();
        long finalTax = 0;
        for (long taxValue : bracketTaxValues) {
            finalTax += taxValue;
        }
        return finalTax;
    }
}
