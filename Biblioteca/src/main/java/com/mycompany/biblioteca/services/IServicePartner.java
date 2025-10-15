
package com.mycompany.biblioteca.services;

import com.mycompany.biblioteca.model.Partner;
import java.util.List;

public interface IServicePartner {
    
     Partner createPartner(Partner partner);

    // Buscar socio por ID
    Partner searchPartnerById(int id);

    // Listar todos los socios
    List<Partner> listPartners();

    // Actualizar un socio
    Partner updatePartner(Partner partner);

    // Eliminar un socio por ID
    void deletePartner(int id);
    
}
