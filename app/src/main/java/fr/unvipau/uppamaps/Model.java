package fr.unvipau.uppamaps;

public class Model {
    private String CLE_INTERNE_BATIMENT;
    private String CODE_BATIMENT;
    private String LIBELLE_BATIMENT;
    private String ADR_ORDRE;
    private String CODE_POSTAL;
    private String VILLE;
    private String ADR_ADRESSE1;
    private String ADR_ADRESSE2;
    private String ADR_GPS_LONGITUDE;
    private String ADR_GPS_LATITUDE;
    private double distance;

    public String getCLE_INTERNE_BATIMENT() {
        return CLE_INTERNE_BATIMENT;
    }

    public void setCLE_INTERNE_BATIMENT(String CLE_INTERNE_BATIMENT) {
        this.CLE_INTERNE_BATIMENT = CLE_INTERNE_BATIMENT;
    }

    public String getCODE_BATIMENT() {
        return CODE_BATIMENT;
    }

    public void setCODE_BATIMENT(String CODE_BATIMENT) {
        this.CODE_BATIMENT = CODE_BATIMENT;
    }

    public String getLIBELLE_BATIMENT() {
        return LIBELLE_BATIMENT;
    }

    public void setLIBELLE_BATIMENT(String LIBELLE_BATIMENT) {
        this.LIBELLE_BATIMENT = LIBELLE_BATIMENT;
    }

    public String getADR_ORDRE() {
        return ADR_ORDRE;
    }

    public void setADR_ORDRE(String ADR_ORDRE) {
        this.ADR_ORDRE = ADR_ORDRE;
    }

    public String getCODE_POSTAL() {
        return CODE_POSTAL;
    }

    public void setCODE_POSTAL(String CODE_POSTAL) {
        this.CODE_POSTAL = CODE_POSTAL;
    }

    public String getVILLE() {
        return VILLE;
    }

    public void setVILLE(String VILLE) {
        this.VILLE = VILLE;
    }

    public String getADR_ADRESSE1() {
        return ADR_ADRESSE1;
    }

    public void setADR_ADRESSE1(String ADR_ADRESSE1) {
        this.ADR_ADRESSE1 = ADR_ADRESSE1;
    }

    public String getADR_ADRESSE2() {
        return ADR_ADRESSE2;
    }

    public void setADR_ADRESSE2(String ADR_ADRESSE2) {
        this.ADR_ADRESSE2 = ADR_ADRESSE2;
    }

    public String getADR_GPS_LONGITUDE() {
        return ADR_GPS_LONGITUDE;
    }

    public void setADR_GPS_LONGITUDE(String ADR_GPS_LONGITUDE) {
        this.ADR_GPS_LONGITUDE = ADR_GPS_LONGITUDE;
    }

    public String getADR_GPS_LATITUDE() {
        return ADR_GPS_LATITUDE;
    }

    public void setADR_GPS_LATITUDE(String ADR_GPS_LATITUDE) {
        this.ADR_GPS_LATITUDE = ADR_GPS_LATITUDE;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }
}
