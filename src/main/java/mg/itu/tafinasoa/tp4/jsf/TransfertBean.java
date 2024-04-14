/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package mg.itu.tafinasoa.tp4.jsf;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import mg.itu.tafinasoa.tp4.service.GestionnaireCompte;

/**
 *
 * @author aceky
 */
@Named(value = "transfertBean")
@RequestScoped
public class TransfertBean {

    private Long idCompteSource;
    private Long idCompteDestination;
    private int montant;

    /**
     * Creates a new instance of TransfertBean
     */
    public TransfertBean() {
    }

    @Inject
    private GestionnaireCompte gestionnaireCompte;

    public String effectuerTransfert() {
        gestionnaireCompte.effectuerTransfert(idCompteSource, idCompteDestination, montant);
        return "listeComptes?faces-redirect=true";
    }

    public Long getIdCompteSource() {
        return idCompteSource;
    }

    public void setIdCompteSource(Long idCompteSource) {
        this.idCompteSource = idCompteSource;
    }

    public Long getIdCompteDestination() {
        return idCompteDestination;
    }

    public void setIdCompteDestination(Long idCompteDestination) {
        this.idCompteDestination = idCompteDestination;
    }

    public int getMontant() {
        return montant;
    }

    public void setMontant(int montant) {
        this.montant = montant;
    }

}
