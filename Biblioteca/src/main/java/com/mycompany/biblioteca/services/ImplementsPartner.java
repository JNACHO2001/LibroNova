package com.mycompany.biblioteca.services;

import com.mycompany.biblioteca.exeptions.DuplicateExceptionRecord;
import com.mycompany.biblioteca.exeptions.ErrorSystemException;
import com.mycompany.biblioteca.exeptions.noExistentResourceException;
import com.mycompany.biblioteca.model.Partner;
import com.mycompany.biblioteca.repository.partner.IPartner;

import java.util.List;

public class ImplementsPartner implements IServicePartner {

    private final IPartner partnerRepository;

    public ImplementsPartner(IPartner partnerRepository) {
        this.partnerRepository = partnerRepository;
    }

    @Override
    public Partner createPartner(Partner partner) {
        // Validar duplicados según criterio (ej: documento o email)
        try {
            return partnerRepository.create(partner);
        } catch (DuplicateExceptionRecord e) {
            throw new DuplicateExceptionRecord("A partner with this identifier already exists.");
        } catch (Exception e) {
            throw new ErrorSystemException("Error creating the partner: " + e.getMessage());
        }
    }

    @Override
    public Partner searchPartnerById(int id) {
        Partner partner = partnerRepository.searchById(id);
        if (partner == null) {
            throw new noExistentResourceException("The partner does not exist.");
        }
        return partner;
    }

    @Override
    public List<Partner> listPartners() {
        return partnerRepository.searchAll();
    }

    @Override
    public Partner updatePartner(Partner partner) {
        // Validar existencia
        Partner existingPartner = partnerRepository.searchById(partner.getId());
        if (existingPartner == null) {
            throw new noExistentResourceException("The partner does not exist.");
        }
        return partnerRepository.update(partner);
    }

    @Override
    public void deletePartner(int id) {
        Partner partner = partnerRepository.searchById(id);
        if (partner == null) {
            throw new noExistentResourceException("The partner does not exist.");
        }
        partnerRepository.delete(id);
    }
}
