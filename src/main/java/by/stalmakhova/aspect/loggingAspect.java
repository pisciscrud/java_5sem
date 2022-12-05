package by.stalmakhova.aspect;


import by.stalmakhova.controller.ProcedureController;
import by.stalmakhova.controller.ScheduleController;
import by.stalmakhova.exception.RecordAlreadyExist;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


@Component
@Slf4j
@Aspect
public class loggingAspect {

    @AfterThrowing(value = "Pointcuts.createNotePointcut()",throwing = "exception")
    public void loginUserExceptionLog(RecordAlreadyExist exception){
        Logger logger = LoggerFactory.getLogger(ScheduleController.class);
        logger.warn("Note already exists");

    }

    @Before(value = "Pointcuts.createProcedurePointcut()")
   public void loginUserLog(){
        Logger logger = LoggerFactory.getLogger(ProcedureController.class);
        logger.info("create procedure method is called");
    }

    @After(value = "Pointcuts.createProcedurePointcut()")
    public void loginUserAfterLog(){
        Logger logger = LoggerFactory.getLogger(ProcedureController.class);
        logger.info("create procedure  is finished");
    }

    @Around(value = "Pointcuts.createProcedurePointcut()")
    public Object createNoteLog(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("Procedure  was created");
        Object result = joinPoint.proceed();
        return result;
    }



}
