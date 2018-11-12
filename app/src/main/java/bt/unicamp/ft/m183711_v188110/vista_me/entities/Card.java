package bt.unicamp.ft.m183711_v188110.vista_me.entities;

import bt.unicamp.ft.m183711_v188110.vista_me.enumerations.CardType;

public class Card {
    private String Number;
    private String NameHolder;
    private String documentNumber;
    private int month;
    private int year;

    public Card (){}

    public Card(String number, String nameHolder, String documentNumber, int month, int year) {
        Number = number;
        NameHolder = nameHolder;
        this.documentNumber = documentNumber;
        this.month = month;
        this.year = year;
    }

    public String getNumber() {
        return Number;
    }

    public String getNumber(boolean ocult) {

        if(ocult)
        {
            String r = "";
            for (int i =0; i < getNumber().length()-4;i++){
                if(i%4 == 3){
                    r += "* ";
                }else{
                    r += "*";
                }
            }

            r += getNumber().substring(getNumber().length() -4, getNumber().length());

            return r;
        }
        else
            return Number;

    }

    public String getCardTypeName(){
        switch (CardType.detect(getNumber())){
            case JCB:
                return "JCB";
            case VISA:
                return "Visa";
            case DISCOVER:
                return "Discover";
            case MASTERCARD:
                return "MasterCard";
            case DINERS_CLUB:
                return "Disner Club";
            case CHINA_UNION_PAY:
                return "China Union Pay";
            case AMERICAN_EXPRESS:
                return "American Express";
            default:
                return "Não detectado";
        }
    }


    public void setNumber(String number) {
        Number = number;
    }

    public String getNameHolder() {
        return NameHolder;
    }

    public void setNameHolder(String nameHolder) {
        NameHolder = nameHolder;
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }

    public boolean isValid() {
        int sum = 0;
        boolean alternate = false;
        for (int i = getNumber().length() - 1; i >= 0; i--) {
            int n = Integer.parseInt(getNumber().substring(i, i + 1));
            if (alternate) {
                n *= 2;
                if (n > 9) {
                    n = (n % 10) + 1;
                }
            }
            sum += n;
            alternate = !alternate;
        }
        return (sum % 10 == 0);
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
