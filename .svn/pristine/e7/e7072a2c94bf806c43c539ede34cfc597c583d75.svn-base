package kr.or.pms.service;

import kr.or.pms.dao.PrjUserDAO;
import kr.or.pms.dto.PrjUserVO;

public class PrjUserServiceImpl implements PrjUserService {

	private PrjUserDAO prjUserDAO;
	public void setPrjUserDAO(PrjUserDAO prjUserDAO) {
		this.prjUserDAO = prjUserDAO;
	}
	
	@Override
	public void enabled(PrjUserVO prjUser) throws Exception {
		prjUserDAO.enabled(prjUser);
	}
	
}
