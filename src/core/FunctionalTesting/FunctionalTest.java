package core.FunctionalTesting;

import core.Interfaces.FunctionalInterfaces.IFunc1;
import core.Interfaces.FunctionalInterfaces.IFunc2;
import core.Interfaces.FunctionalInterfaces.IFunc3;
import core.Interfaces.IAstarNode;
import core.Interfaces.IFunctionalTest;

public class FunctionalTest implements IFunctionalTest {
    private IFunc1<IAstarNode,Boolean> func1;
    private IFunc2<IAstarNode,IAstarNode,Boolean> func2;
    private IFunc3<IAstarNode,IAstarNode,IAstarNode,Boolean> func3;
    private int argumentCounter = 0;

    FunctionalTest(){

    }

    //Test the neighbor
    @Override
    public void setFunc1(IFunc1<IAstarNode, Boolean> func1) {
        argumentCounter = 1;
        this.func1 = func1;
    }
    //Test the current node and its currently selected neighbor
    @Override
    public void setFunc2(IFunc2<IAstarNode, IAstarNode, Boolean> func2) {
        argumentCounter = 2;
        this.func2 = func2;
    }
    //Test the previous node of the current node and the current node and the neighbor
    @Override
    public void setFunc3(IFunc3<IAstarNode, IAstarNode, IAstarNode, Boolean> func3) {
        argumentCounter = 3;
        this.func3 = func3;
    }

    @Override
    public IFunc1<IAstarNode, Boolean> getFunc1() {
        return func1;
    }

    @Override
    public IFunc2<IAstarNode, IAstarNode, Boolean> getFunc2() {
        return func2;
    }

    @Override
    public IFunc3<IAstarNode, IAstarNode, IAstarNode, Boolean> getFunc3() {
        return func3;
    }

    @Override
    public int getArgumentCounter() {
        return argumentCounter;
    }
}
