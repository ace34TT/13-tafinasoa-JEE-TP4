/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package mg.itu.tafinasoa.tp4.jsf;

import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serializable;
import mg.itu.tafinasoa.tp4.entity.CompteBancaire;
import mg.itu.tafinasoa.tp4.jsf.util.Util;
import mg.itu.tafinasoa.tp4.service.GestionnaireCompte;

/**
 *
 * @author aceky
 */
@Named(value = "deposerRetirerArgentBean")
@ViewScoped
public class DeposerRetirerArgentBean implements Serializable {

    @Inject
    private GestionnaireCompte gestionnaireCompte;

    private Long idCompte;
    private Integer montant;
    private CompteBancaire compte;

    /**
     * Creates a new instance of DeposerRetirerArgentBean
     */
    public DeposerRetirerArgentBean() {
    }

    public void chargerCompte() {

        compte = gestionnaireCompte.trouverCompteParId(idCompte);
        System.out.println("mg.itu.tafinasoa.tp4.jsf.DeposerRetirerArgentBean.chargerCompte()");
        System.out.println(compte.getNom());

    }

    public String deposer() {
        if (montant <= 0) {
            Util.messageErreur("Le montant doit être supérieur à zéro.");
            return null;
        }

        if (compte == null) {
            Util.messageErreur("Impossible de trouver le compte.");
            return null;
        }

        compte.deposer(montant);
        gestionnaireCompte.modifierCompte(compte);
        Util.addFlashInfoMessage("Dépôt effectué avec succès.");
        return "listeComptes?faces-redirect=true";
    }

    public String retirer() {
        if (montant <= 0) {
            Util.messageErreur("Le montant doit être supérieur à zéro.");
            return null;
        }

        if (compte == null) {
            Util.messageErreur("Impossible de trouver le compte.");
            return null;
        }

        if (compte.getSolde() < montant) {
            Util.messageErreur("Solde insuffisant.");
            return null;
        }
        compte.retirer(montant);
        gestionnaireCompte.modifierCompte(compte);
        Util.addFlashInfoMessage("Retrait effectué avec succès.");
        return "listeComptes?faces-redirect=true";
    }

    public Long getIdCompte() {
        return idCompte;
    }

    public void setIdCompte(Long idCompte) {
        this.idCompte = idCompte;
    }

    public Integer getMontant() {
        return montant;
    }

    public void setMontant(Integer montant) {
        this.montant = montant;
    }

}
