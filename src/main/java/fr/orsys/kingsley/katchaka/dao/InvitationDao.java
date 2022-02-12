package fr.orsys.kingsley.katchaka.dao;

import java.sql.SQLException;
import java.util.List;

import fr.orsys.kingsley.katchaka.business.Invitation;

public interface InvitationDao {
	Invitation create(Invitation invitation) throws SQLException;

	List<Invitation> findAll() throws SQLException;

	Invitation findOne(Long id) throws SQLException;

	Invitation update(Invitation invitation) throws SQLException;
	
	Invitation update_invitation_pendant_acceptation_ou_declinant(Invitation invitation) throws SQLException;

	boolean delete(Invitation invitation) throws SQLException;
	
	List<Invitation> findByExpediteurId(Long expediteur_id) throws SQLException;
	
	List<Invitation> findByDestinataireId(Long destinataire_id) throws SQLException;
	
	Invitation findByExpediteurIdAndDestinataireId(Long expediteur_id, Long destinataire_id) throws SQLException;
}
