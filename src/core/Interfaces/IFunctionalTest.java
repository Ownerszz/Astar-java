package core.Interfaces;

import core.Interfaces.FunctionalInterfaces.IFunc1;
import core.Interfaces.FunctionalInterfaces.IFunc2;
import core.Interfaces.FunctionalInterfaces.IFunc3;
import core.Interfaces.IAstarNode;

public interface IFunctionalTest {
    //Test 1 IAstarNode
    void setFunc1(IFunc1<IAstarNode, Boolean> func1);

    //Test 2 IAstarNodes
    void setFunc2(IFunc2<IAstarNode, IAstarNode, Boolean> func2);

    //Test 3 IAstarNodes
    void setFunc3(IFunc3<IAstarNode, IAstarNode, IAstarNode, Boolean> func3);

    IFunc1<IAstarNode, Boolean> getFunc1();

    IFunc2<IAstarNode, IAstarNode, Boolean> getFunc2();

    IFunc3<IAstarNode, IAstarNode, IAstarNode, Boolean> getFunc3();

    int getArgumentCounter();
}
