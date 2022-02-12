package fr.orsys.kingsley.katchaka.service;

import java.util.List;

import fr.orsys.kingsley.katchaka.business.Invitation;

public interface InvitationService {
	Invitation create(Invitation invitation);

	List<Invitation> findAll();

	Invitation findOne(Long id);

	Invitation update(Invitation invitation);
	
	Invitation update_invitation_pendant_acceptation_ou_declinant(Invitation invitation);

	boolean delete(Invitation invitation);
	
	List<Invitation> findByExpediteurId(Long expediteur_id);
	
	List<Invitation> findByDestinataireId(Long destinataire_id);
	
	Invitation findByExpediteurIdAndDestinataireId(Long expediteur_id, Long destinataire_id);

}
