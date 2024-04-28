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
@Named(value = "modifierNomCompteBean")
@ViewScoped
public class ModifierNomCompteBean implements Serializable {

    @Inject
    private GestionnaireCompte gestionnaireCompte;

    private Integer idCompte;
    private CompteBancaire compte;
    private String currentName;

    /**
     * Creates a new instance of ModifierNomCompteBean
     */
    public ModifierNomCompteBean() {
    }

    public void chargerCompte() {
        compte = gestionnaireCompte.trouverCompteParId(idCompte);
        this.currentName = compte.getNom();
    }

    public String modifierNom() {
        if (compte != null) {
            gestionnaireCompte.modifierCompte(compte);
            Util.addFlashInfoMessage("Nom du compte modifié avec succès : Ancien nom : "
                    + currentName + ", Nouveau nom : " + compte.getNom());
            return "listeComptes?faces-redirect=true";
        } else {
            Util.messageErreur("Impossible de trouver le compte.");
            return null;
        }
    }

    public Integer getIdCompte() {
        return idCompte;
    }

    public void setIdCompte(Integer idCompte) {
        this.idCompte = idCompte;
    }

    public CompteBancaire getCompte() {
        return compte;
    }

    public void setCompte(CompteBancaire compte) {
        this.compte = compte;
    }

}
