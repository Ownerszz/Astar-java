package core.FunctionalTesting;

import core.Interfaces.FunctionalInterfaces.IFunc1;
import core.Interfaces.FunctionalInterfaces.IFunc2;
import core.Interfaces.FunctionalInterfaces.IFunc3;
import core.Interfaces.IAstarNode;
import core.Interfaces.IFunctionalTest;

public final class FunctionalTestFactory {
    public static IFunctionalTest createFunctionalTest(IFunc1<IAstarNode,Boolean> func1){
        IFunctionalTest functionalTest = new FunctionalTest();
        functionalTest.setFunc1(func1);
        return functionalTest;
    }
    public static IFunctionalTest createFunctionalTest(IFunc2<IAstarNode,IAstarNode,Boolean> func2){
        IFunctionalTest functionalTest = new FunctionalTest();
        functionalTest.setFunc2(func2);
        return functionalTest;
    }
    public static IFunctionalTest createFunctionalTest(IFunc3<IAstarNode,IAstarNode,IAstarNode,Boolean> func3){
        IFunctionalTest functionalTest = new FunctionalTest();
        functionalTest.setFunc3(func3);
        return functionalTest;
    }

}
