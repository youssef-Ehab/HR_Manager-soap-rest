package org.example.Persistence.DAOS.Implementation;

import jakarta.persistence.EntityManager;
import org.example.Persistence.DAOS.Generic.dao;
import org.example.Persistence.Entities.Address;

public class AddressDAO extends dao<Address> {
    public AddressDAO(EntityManager entityManager) {
        super(entityManager);
        super.setType(Address.class);
    }
    public Address getAddressbyStreetAndCityAndCountry(String streetAddress , String city , String country) {
        return entityManager.createQuery("SELECT a FROM Address a WHERE a.streetAddress = :streetAddress AND a.city = :city AND a.country = :country", Address.class)
                .setParameter("streetAddress", streetAddress)
                .setParameter("city", city)
                .setParameter("country", country)
                .getSingleResult();
    }
}
