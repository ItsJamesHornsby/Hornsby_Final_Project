package disburse.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import disburse.mapper.HouseDisburseMapper;
import disburse.repository.HouseDisburseRepository;
import disburse.vo.HouseDisburseDetail;

@Repository
public class HouseDisburseDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private HouseDisburseMapper mapper;
	
	@Autowired
	private HouseDisburseRepository repo;
	
	public List<HouseDisburseDetail> getAll() {
		List<HouseDisburseDetail> hdList = mapper.getAll();
		for (HouseDisburseDetail hd : hdList) {
			hd.setOffice(hd.getOffice().toLowerCase());
		}
		return hdList;
	}
	
	public List<HouseDisburseDetail> findBybioGuideID(String bioGuideID) {
		return repo.findBybioGuideID(bioGuideID);
	}
	
	public void saveAll(List<HouseDisburseDetail> recs) {
		repo.saveAll(recs);
	}
	
	public List<HouseDisburseDetail> getHighestAmount() {
		return mapper.getHighestAmmount();
	}
	
}
