package fr.orsys.kingsley.katchaka.service.impl;

import java.sql.SQLException;
import java.util.List;

import fr.orsys.kingsley.katchaka.business.Invitation;
import fr.orsys.kingsley.katchaka.dao.InvitationDao;
import fr.orsys.kingsley.katchaka.dao.impl.InvitationDaoImpl;
import fr.orsys.kingsley.katchaka.service.InvitationService;

public class InvitationServiceImpl implements InvitationService {
	private InvitationDao invitationDao = new InvitationDaoImpl();

	@Override
	public Invitation create(Invitation invitation) {
		try {
			return invitationDao.create(invitation);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Invitation> findAll() {
		try {
			return invitationDao.findAll();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Invitation findOne(Long id) {
		try {
			return invitationDao.findOne(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Invitation update(Invitation invitation) {
		try {
			return invitationDao.update(invitation);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean delete(Invitation invitation) {
		try {
			return invitationDao.delete(invitation);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<Invitation> findByExpediteurId(Long expediteur_id) {
		try {
			return invitationDao.findByExpediteurId(expediteur_id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Invitation> findByDestinataireId(Long destinataire_id) {
		try {
			return invitationDao.findByDestinataireId(destinataire_id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Invitation update_invitation_pendant_acceptation_ou_declinant(Invitation invitation) {
		try {
			return invitationDao.update_invitation_pendant_acceptation_ou_declinant(invitation);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Invitation findByExpediteurIdAndDestinataireId(Long expediteur_id, Long destinataire_id) {
		try {
			return invitationDao.findByExpediteurIdAndDestinataireId(expediteur_id, destinataire_id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
