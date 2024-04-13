/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package mg.itu.tafinasoa.tp4.jsf;

import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.List;
import mg.itu.tafinasoa.tp4.entity.CompteBancaire;
import mg.itu.tafinasoa.tp4.service.GestionnaireCompte;

/**
 *
 * @author aceky
 */
@Named(value = "listeComptes")
@ViewScoped
public class ListeComptes implements Serializable {

    private List<CompteBancaire> listComptesBancaires;

    @Inject
    private GestionnaireCompte gc;

    /**
     * Creates a new instance of ListeComptes
     */
    public ListeComptes() {
    }

    public List<CompteBancaire> getComptes() {
        if (listComptesBancaires == null) {
            listComptesBancaires = gc.getAllComptes();
        }
        return listComptesBancaires;
    }
}
