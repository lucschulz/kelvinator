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

    String getOutputC() {
        double output = Math.round(outputC * 100.0) / 100.0;
        return String.valueOf(output);
    }

    String getOutputF() {
        double output = Math.round(outputF * 100.0) / 100.0;
        return String.valueOf(output);
    }

    String getOutputK() {
        double output = Math.round(outputK * 100.0) / 100.0;
        return String.valueOf(output);
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
                outputC = inputValue;
                outputK = convertCtoK(inputValue);
                outputF = convertCtoF(inputValue);
                break;

            case F:
                outputF = inputValue;
                outputK = convertFtoK(inputValue);
                outputC = convertFtoC(inputValue);
                break;

            case K:
                outputK = inputValue;
                outputF = convertKtoF(inputValue);
                outputC = convertKtoC(inputValue);
                break;
        }
    }

    private double convertCtoF(double c) {
        return (1.8 * c) + 32;
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
