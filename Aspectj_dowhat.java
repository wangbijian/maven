package cn.com.test;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect@Component
public class Aspectj_dowhat {
	@Pointcut("execution(* cn.com.test.dowhat.say*(..))")
	public void pointCut(){}
	
	@After("pointCut()")
    public void after(){
        System.out.println("after");
    }
    
    @Before("pointCut()")
    public void before(){
        System.out.println("before");
    }
    
    @Around("pointCut()")
    public Object around(ProceedingJoinPoint joinpoint){
    	//joinpoint.getArgs()能够得到传入到方法的参数
    	if (joinpoint.getArgs().length > 0) {
    		System.out.println(joinpoint.getArgs()[0]);
		}
        Object valu = null;
        try {
            System.out.println("around before");
            valu = joinpoint.proceed();
            //返回所代理的方法（有返回值的方法）返回值
            System.out.println("around after:"+valu);
            
        } catch (Throwable e) {
            e.printStackTrace();
        	/*try {
        		HttpServletRequest request = ServletActionContext.getRequest();
            	HttpServletResponse response = ServletActionContext.getResponse();
            	request.getRequestDispatcher("/error.html").forward(request, response);
			} catch (Exception e2) {
				e2.printStackTrace();
			}*/
        }
        return valu;
    }
    
    @AfterReturning("pointCut()")
    public void afteReturning(){
        System.out.println("afteReturning");
    }
    
    @AfterThrowing("pointCut()")
    public void afterThrowing(){
        System.out.println("afterThrowing");
    }
}
