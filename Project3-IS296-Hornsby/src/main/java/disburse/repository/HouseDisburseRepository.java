package disburse.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import disburse.vo.HouseDisburseDetail;


public interface HouseDisburseRepository extends JpaRepository<HouseDisburseDetail, Integer>{

	public List<HouseDisburseDetail> findBybioGuideID(String bioGuideID);
	
	@Query(value= "SELECT * from T_2013Q4_HOUSE_DISBURSE where AMOUNT = (SELECT MAX(AMOUNT) from T_2013Q4_HOUSE_DISBURSE)", 
			 nativeQuery = true)
	List<HouseDisburseDetail> findByAmount();
	

	@Query(value= "SELECT bioGuideID, office, category FROM HouseDisburseDetail")
	Collection<HouseDisburseDetail> getAmountJQL();
}
