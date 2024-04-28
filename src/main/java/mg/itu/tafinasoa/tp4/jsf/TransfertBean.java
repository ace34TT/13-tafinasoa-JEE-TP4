/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package mg.itu.tafinasoa.tp4.jsf;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.util.ArrayList;
import java.util.List;
import mg.itu.tafinasoa.tp4.entity.CompteBancaire;
import mg.itu.tafinasoa.tp4.jsf.util.Util;
import mg.itu.tafinasoa.tp4.service.GestionnaireCompte;

/**
 *
 * @author aceky
 */
@Named(value = "transfertBean")
@RequestScoped
public class TransfertBean {

    private Integer idCompteSource;
    private Integer idCompteDestination;
    private Integer montant;

    List<String> erreurs = new ArrayList<>();

    /**
     * Creates a new instance of TransfertBean
     */
    public TransfertBean() {

    }

    @Inject
    private GestionnaireCompte gestionnaireCompte;

    public String effectuerTransfert() {
        CompteBancaire compteSource = gestionnaireCompte.trouverCompteParId(idCompteSource);
        CompteBancaire compteDestination = gestionnaireCompte.trouverCompteParId(idCompteDestination);

        if (compteSource == null) {
            erreurs.add("Le compte source n'existe pas.");
        }

        if (compteDestination == null) {
            erreurs.add("Le compte destination n'existe pas.");
        }

        if (compteSource != null && compteDestination != null && compteSource.getSolde() < montant) {
            erreurs.add("Solde insuffisant dans le compte source.");
        }

        if (!erreurs.isEmpty()) {
            for (String erreur : erreurs) {
                Util.messageErreur(erreur);
            }
            return null;
        }

        gestionnaireCompte.effectuerTransfert(idCompteSource, idCompteDestination, montant);
        Util.addFlashInfoMessage("Transfert effectué avec succès  " + montant + " " + compteSource.getNom() + " vers " + compteDestination.getNom());
        return "listeComptes?faces-redirect=true";
    }

    public Integer getIdCompteSource() {
        return idCompteSource;
    }

    public void setIdCompteSource(Integer idCompteSource) {
        this.idCompteSource = idCompteSource;
    }

    public Integer getIdCompteDestination() {
        return idCompteDestination;
    }

    public void setIdCompteDestination(Integer idCompteDestination) {
        this.idCompteDestination = idCompteDestination;
    }

    public Integer getMontant() {
        return montant;
    }

    public void setMontant(Integer montant) {
        this.montant = montant;
    }

}
