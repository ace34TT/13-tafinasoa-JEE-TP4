/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package mg.itu.tafinasoa.tp4.jsf;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import mg.itu.tafinasoa.tp4.entity.CompteBancaire;
import mg.itu.tafinasoa.tp4.jsf.util.Util;
import mg.itu.tafinasoa.tp4.service.GestionnaireCompte;

/**
 *
 * @author aceky
 */
@Named(value = "ajouterCompteBean")
@RequestScoped
public class AjouterCompteBean {

    private String nomTitulaire;
    private Integer solde;

    /**
     * Creates a new instance of AjouterCompteBean
     */
    public AjouterCompteBean() {
    }
    @Inject
    private GestionnaireCompte gestionnaireCompte;

    public String getNomTitulaire() {
        return nomTitulaire;
    }

    public void setNomTitulaire(String nomTitulaire) {
        this.nomTitulaire = nomTitulaire;
    }

    public Integer getSolde() {
        return solde;
    }

    public void setSolde(Integer solde) {
        this.solde = solde;
    }

    public String ajouterCompte() {
        if (nomTitulaire == null || nomTitulaire.isEmpty()) {
            Util.messageErreur("Le nom du titulaire est requis.");
            return null; // Restez sur la même page pour corriger l'erreur
        }

        if (solde <= 0) {
            Util.messageErreur("Le solde du compte doit être supérieur à zéro.");
            return null; // Restez sur la même page pour corriger l'erreur
        }

        gestionnaireCompte.creerCompte(new CompteBancaire(nomTitulaire, solde));
        Util.addFlashInfoMessage("Compte ajouté avec succès.");
        return "listeComptes?faces-redirect=true";
    }
}
