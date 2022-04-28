package disburse.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import disburse.vo.HouseDisburseDetail;

@Mapper
public interface HouseDisburseMapper {
	
	@Select("SELECT * from T_2013Q4_HOUSE_DISBURSE")
	public List<HouseDisburseDetail> getAll();
	
	@Select("SELECT * from T_2013Q4_HOUSE_DISBURSE where AMOUNT = (SELECT MAX(AMOUNT) from T_2013Q4_HOUSE_DISBURSE)")
	public List<HouseDisburseDetail> getHighestAmmount();
	
	
	@Insert({"<script>",  
	     "INSERT into T_2013Q4_HOUSE_DISBURSE", 
	      "(BIOGUIDE_ID, OFFICE, CATEGORY, PAYEE, START_DATE, END_DATE, PURPOSE, AMOUNT, YEAR)",  
	      "VALUES", 
	        "<foreach collection='hdList' item='hd' open='' separator=',' close=''>", 
	          "(", 
	            "#{hd.bioGuideID},", 
	            "#{hd.office},", 
	            "#{hd.category},",
	            "#{hd.payee},",
	            "#{hd.startDate},",
	            "#{hd.endDate},",
	            "#{hd.purpose},",
	            "#{hd.amount},",
	            "#{hd.year}",
	           ")", 
	         "</foreach>",
	      "</script>"})
	public void insertDisburseList(@Param("hdList") List<HouseDisburseDetail> hd);
}
