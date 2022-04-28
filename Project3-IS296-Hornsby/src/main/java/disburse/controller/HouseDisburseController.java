package disburse.controller;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import disburse.dao.HouseDisburseDAO;
import disburse.mapper.HouseDisburseMapper;
import disburse.repository.HouseDisburseRepository;
import disburse.vo.HouseDisburseDetail;

@Controller
public class HouseDisburseController {
	
	@Autowired
	private HouseDisburseDAO hdDAO;
	
	@Autowired
	private HouseDisburseRepository hdRepo;
	
	@Autowired
	private HouseDisburseMapper mapper;
	
	@GetMapping("/all")
	public String getAll(Model model) {
		List<HouseDisburseDetail> hdList = hdDAO.getAll();
		model.addAttribute("hdList", hdList);
		return "disburse";
	}
	
	@GetMapping("/bioGuideID/{bioGuideID}")
	public String getbyBioGuideID(Model model, @PathVariable String bioGuideID) {
		List<HouseDisburseDetail> hdList = hdDAO.findBybioGuideID(bioGuideID);
		model.addAttribute("hdList", hdList);
		return "disburse";
	}
	
	@PostMapping("/loadDisburse") 
	public @ResponseBody List<HouseDisburseDetail> loadDisburse(@RequestBody final List<HouseDisburseDetail> hdList) {
		hdDAO.saveAll(hdList);
		return hdRepo.findAll();
	}
	@GetMapping("/highestAmount")
	public @ResponseBody List<HouseDisburseDetail> getHighestAmount() {
		return hdDAO.getHighestAmount();
	}
	@GetMapping("/highestAmountJPA")
	public @ResponseBody List<HouseDisburseDetail> getHighestAmountJPA() {
		List<HouseDisburseDetail> allrecs = hdRepo.findByAmount();
		for (HouseDisburseDetail hd : allrecs) {
			System.out.println(hd.getBioGuideID());
		}
		return hdRepo.findByAmount();
	}
	
	@GetMapping("/highestAmountJPAJQL")
	public @ResponseBody Collection<HouseDisburseDetail> getHighestAmountJPAJQL() {
		Collection<HouseDisburseDetail> allrecs = hdRepo.getAmountJQL();
		for (HouseDisburseDetail hd : allrecs) {
			System.out.println(hd.getBioGuideID());
		}
		return hdRepo.getAmountJQL();
	}
	
	/*@GetMapping("/highestAmountByBioGuideID")
	public List<Map<String, Object>> getHABBID() {
		
	} */
	@PostMapping("/insertMyBatisList")
	public @ResponseBody List<HouseDisburseDetail> insertMyBatisList (@RequestBody final List<HouseDisburseDetail> hdList) {
		mapper.insertDisburseList(hdList);
		return hdRepo.findAll();
	}
	
	@PostMapping("/submitIncident")
	public void consumeIncident(@RequestBody String incidentText) {
		System.out.println("incident text is: "+ incidentText);
	}
	
	@GetMapping("/createIncident")
	public String getIncidentPage() {
		return "createIncident.html";
	}
}

