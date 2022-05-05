package disburse.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.jboss.logging.Logger;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggerAspect {
	private Logger logger = Logger.getLogger(getClass());
	
	@After("execution (* getHighestAmount())") //this is called "advice"
	public void logGetHighestAmount(JoinPoint jp) {
		logger.info("Before advice implementation - " + jp.getTarget().getClass() + " - Executing before " +
					 jp.getSignature().getName() + "() method");
		System.out.println("Executing Before");
		
	}
	
	@Before("execution(@org.springframework.web.bind.annotation.GetMapping * *.*(..)) || "
			+ "execution(@org.springframework.web.bind.annotation.PostMapping * *.*(..)) ||"
			+ "execution(@org.springframework.web.bind.annotation.RequestMapping * *.*(..))")
	public void logALLRESTattempts(JoinPoint joinPoint) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		String username;
		if(principal instanceof UserDetails) {
			username = ((UserDetails)principal).getUsername();
		} else {
			username = principal.toString();
		}
		logger.info(username + " accessing - " + joinPoint.getTarget().getClass() + 
				" ; Executing " + joinPoint.getSignature().getName() + "() method");
	}

}
