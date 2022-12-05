package by.stalmakhova.aspect;

import org.aspectj.lang.annotation.Pointcut;

public class Pointcuts {

    @Pointcut("execution(* by.stalmakhova.controller.ScheduleController.add(..))")
    public void createNotePointcut(){

    }

    @Pointcut("execution(* by.stalmakhova.services.ProcedureServiceImpl.CreateProcedure(..))")
    public void createProcedurePointcut(){

    }

 }
