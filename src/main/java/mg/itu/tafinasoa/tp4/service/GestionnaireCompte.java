/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package mg.itu.tafinasoa.tp4.service;

import jakarta.annotation.sql.DataSourceDefinition;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import java.util.List;
import mg.itu.tafinasoa.tp4.entity.CompteBancaire;

/**
 *
 * @author aceky
 */
@DataSourceDefinition(
        className = "com.mysql.cj.jdbc.MysqlDataSource",
        name = "java:app/jdbc/banque",
        serverName = "localhost",
        portNumber = 3306,
        user = "root",
        password = "jkl;",
        databaseName = "banque",
        properties = {
            "useSSL=false",
            "allowPublicKeyRetrieval=true",
            "driverClass=com.mysql.cj.jdbc.Driver"
        }
)

@Named(value = "gestionnaireCompte")
@ApplicationScoped
public class GestionnaireCompte {

    @PersistenceContext(unitName = "banquePU")
    private EntityManager em;

    /**
     * Creates a new instance of GestionnaireCompte
     */
    public GestionnaireCompte() {
    }

    @Transactional
    public void creerCompte(CompteBancaire compteBancaire) {
        em.persist(compteBancaire);
    }

    public List<CompteBancaire> getAllComptes() {
        TypedQuery<CompteBancaire> typedQuery = em.createNamedQuery("CompteBancaire.findAll", CompteBancaire.class);
        List<CompteBancaire> comptes = typedQuery.getResultList();
        for (CompteBancaire compte : comptes) {
            System.out.println(compte.toString());
        }
        return comptes;
    }

    public CompteBancaire trouverCompteParId(Long id) {
        return em.find(CompteBancaire.class, id);
    }

    @Transactional
    public void effectuerTransfert(Long idCompteSource, Long idCompteDestination, int montant) {
        CompteBancaire compteSource = trouverCompteParId(idCompteSource);
        CompteBancaire compteDestination = trouverCompteParId(idCompteDestination);

        if (compteSource == null || compteDestination == null) {
            throw new IllegalArgumentException("Un ou plusieurs comptes n'existent pas.");
        }

        if (compteSource.getSolde() < montant) {
            throw new IllegalArgumentException("Solde insuffisant dans le compte source.");
        }

        compteSource.retirer(montant);
        compteDestination.deposer(montant);

        em.merge(compteSource);
        em.merge(compteDestination);

        System.out.println("Transfert de " + montant + " effectué de " + compteSource.getId() + " vers " + compteDestination.getId());
    }

    @Transactional
    public void modifierCompte(CompteBancaire compte) {
        em.merge(compte);
    }

    @Transactional
    public void supprimerCompte(CompteBancaire compte) {
        CompteBancaire compteASupprimer = em.find(CompteBancaire.class, compte.getId());
        if (compteASupprimer != null) {
            em.remove(compteASupprimer);
        }
    }
}
