package ca.lucschulz.kelvinator;

enum Units {
    C,
    F,
    K
}

class UnitConverter {

    private Units inputUnit;
    private double inputValue;

    private double outputC;
    private double outputF;
    private double outputK;

    public String getOutputC() {
        return String.valueOf(outputC);
    }

    public String getOutputF() {
        return String.valueOf(outputF);
    }

    public String getOutputK() {
        return String.valueOf(outputK);
    }

    UnitConverter(Units inputUnit, double inputValue) {
        this.inputUnit = inputUnit;
        this.inputValue = inputValue;

        convertTemps();
    }

    private void convertTemps() {
        switch (inputUnit)
        {
            case C:
                outputK = convertCtoK(inputValue);
                outputF = convertCtoF(inputValue);
                break;

            case F:
                outputK = convertFtoK(inputValue);
                outputC = convertFtoC(inputValue);
                break;

            case K:
                outputF = convertKtoF(inputValue);
                outputC = convertKtoC(inputValue);
                break;
        }
    }

    private double convertCtoF(double c) {
        return(1.8 * c) + 32;
    }

    private double convertFtoC(double f) {
        return (f - 32) / 1.8;
    }

    private double convertCtoK(double c) {
        return c + 273.15;
    }

    private double convertKtoC(double k) {
        return k - 273.15;
    }

    private double convertKtoF(double k) {
        double c = convertKtoC(k);
        return convertCtoF(c);
    }

    private double convertFtoK(double f) {
        double c = convertFtoC(f);
        return convertCtoK(c);
    }
}
