package org.example;

public class Reactor {
    private String type;
    private String reactorClass;
    private Double burnup;
    private Double kpd;
    private Double enrichment;
    private Double thermal_capacity;
    private Double electrical_capacity;
    private Double first_load;
    private Integer life_time;
    private String source;

    public Reactor(String type, String reactorClass, Double burnup, Double electrical_capacity,
                   Double enrichment, Double first_load, Double kpd, Integer life_time,
                   Double thermal_capacity, String source) {
        this.type = type;
        this.reactorClass = reactorClass;
        this.burnup = burnup;
        this.electrical_capacity = electrical_capacity;
        this.enrichment = enrichment;
        this.first_load = first_load;
        this.kpd = kpd;
        this.life_time = life_time;
        this.thermal_capacity = thermal_capacity;
        this.source = source;
    }

    public String getType() {
        return type;
    }

    public String getReactorClass() {
        return reactorClass;
    }

    public Integer getLife_time() {
        return life_time;
    }

    public Double getThermal_capacity() {
        return thermal_capacity;
    }

    public String getSource() {
        return source;
    }

    public Double getFirst_load() {
        return first_load;
    }

    public Double getElectrical_capacity() {
        return electrical_capacity;
    }

    public Double getBurnup() {
        return burnup;
    }

    public Double getEnrichment() {
        return enrichment;
    }

    public Double getKpd() {
        return kpd;
    }
}