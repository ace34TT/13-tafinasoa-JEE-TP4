/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package mg.itu.tafinasoa.tp4.config;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.Initialized;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.servlet.ServletContext;
import jakarta.transaction.Transactional;
import mg.itu.tafinasoa.tp4.entity.CompteBancaire;
import mg.itu.tafinasoa.tp4.service.GestionnaireCompte;

/**
 *
 * @author aceky
 */
@Named(value = "init")
@ApplicationScoped
public class Init {

    /**
     * Creates a new instance of Init
     */
    @Inject
    GestionnaireCompte gc;

    public Init() {

    }

    @PostConstruct
    @Transactional
    public void init() {
        System.out.println("mg.itu.tafinasoa.tp4.config.Init.<init>()");
        if (gc.getAllComptes().size() == 0) {
            gc.creerCompte(new CompteBancaire("John Lennon", 150000));
            gc.creerCompte(new CompteBancaire("Paul McCartney", 950000));
            gc.creerCompte(new CompteBancaire("Ringo Starr", 20000));
            gc.creerCompte(new CompteBancaire("Georges Harrisson", 100000));
        }
    }

    public void onStartup(@Observes @Initialized(ApplicationScoped.class) ServletContext context) {
        System.out.println("mg.itu.tafinasoa.tp4.config.Init.onStartup()");
        init();
    }
}
