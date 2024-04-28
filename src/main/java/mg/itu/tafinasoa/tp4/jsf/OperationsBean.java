/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package mg.itu.tafinasoa.tp4.jsf;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.time.format.DateTimeFormatter;
import java.util.List;
import mg.itu.tafinasoa.tp4.entity.CompteBancaire;
import mg.itu.tafinasoa.tp4.entity.OperationBancaire;
import mg.itu.tafinasoa.tp4.service.GestionnaireCompte;

/**
 *
 * @author aceky
 */
@Named(value = "operationsBean")
@RequestScoped
public class OperationsBean {

    @Inject
    private GestionnaireCompte gestionnaireCompte;

    Integer idCompte;
    CompteBancaire cb;
    List<OperationBancaire> ob;

    DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    /**
     * Creates a new instance of OperationsBean
     */
    public OperationsBean() {
    }

    public void chargerCompte() {
        cb = gestionnaireCompte.trouverCompteParId(idCompte);
        for (OperationBancaire operationBancaire : cb.getOperations()) {
            System.out.println("data here");
        }

        ob = cb.getOperations();
    }

    public Integer getIdCompte() {
        return idCompte;
    }

    public void setIdCompte(Integer idCompte) {
        this.idCompte = idCompte;
    }

    public CompteBancaire getCb() {
        return cb;
    }

    public List<OperationBancaire> getOb() {
        return ob;
    }

    public DateTimeFormatter getOutputFormatter() {
        return outputFormatter;
    }

    public void setOutputFormatter(DateTimeFormatter outputFormatter) {
        this.outputFormatter = outputFormatter;
    }

}
