package com.chl.test.spring.declaration;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.DeclareParents;

@Aspect
@Component
public class AdditionalInterfaceDeclaration {

    @DeclareParents(value = "com.chl.test.spring.declaration.TargetClass",
            defaultImpl = com.chl.test.spring.declaration.AdditionalFunctionality.class)
    private AdditionalInterface additionalInterface;
}
