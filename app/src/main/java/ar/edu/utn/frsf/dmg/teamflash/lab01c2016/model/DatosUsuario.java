package ar.edu.utn.frsf.dmg.teamflash.lab01c2016.model;

/**
 * Created by AdminUser on 26/08/2016.
 */
public class DatosUsuario {

    private String mail;
    private Long cuit;
    private Double monto;
    private Integer cantDias;
    private Double tasaInteres;
    private Boolean renovarPlazoFijo;

    public DatosUsuario() {
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public Long getCuit() {
        return cuit;
    }

    public void setCuit(Long cuit) {
        this.cuit = cuit;
    }

    public Double getMonto() {
        return monto;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }

    public Integer getCantDias() {
        return cantDias;
    }

    public void setCantDias(Integer cantDias) {
        this.cantDias = cantDias;
    }

    public Double getTasaInteres() {
        return tasaInteres;
    }

    public void setTasaInteres(Double tasaInteres) {
        this.tasaInteres = tasaInteres;
    }

    public Boolean getRenovarPlazoFijo() {
        return renovarPlazoFijo;
    }

    public void setRenovarPlazoFijo(Boolean renovarPlazoFijo) {
        this.renovarPlazoFijo = renovarPlazoFijo;
    }
}
