package org.example.dao;

import java.util.List;
public interface GenericDao <Entity,PK>{
    Entity create(Entity entity); //  insère un tuple dans la base de données
    List<Entity> readAll(); // méthode qui retourne toutes les données de  la table
    Entity readById(PK id); // retourne un seul tuple selon l'identifiant
    Entity update(Entity entity); // modifie un tuple d'une table
    boolean delete (PK id); // supprime un tuple de la table selon l'identifiant

}