package disburse.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.jboss.logging.Logger;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggerConfig {
	private Logger logger = Logger.getLogger(getClass());
	
	@After("execution (* getHighestAmount())") //this is called "advice"
	public void logGetHighestAmount(JoinPoint jp) {
		logger.info("Before advice implementation - " + jp.getTarget().getClass() + " - Executing before " +
					 jp.getSignature().getName() + "() method");
		System.out.println("Executing Before");
		
	}

}
