package Interfaces.FunctionalInterfaces;

import Interfaces.IAstarNode;

public class FunctionalTest  {
    private IFunc1<IAstarNode,Boolean> func1;
    private IFunc2<IAstarNode,IAstarNode,Boolean> func2;
    private IFunc3<IAstarNode,IAstarNode,IAstarNode,Boolean> func3;
    private int argumentCounter = 0;
    //Test the neighbor
    public void setFunc1(IFunc1<IAstarNode, Boolean> func1) {
        argumentCounter = 1;
        this.func1 = func1;
    }
    //Test the current node and its currently selected neighbor
    public void setFunc2(IFunc2<IAstarNode, IAstarNode, Boolean> func2) {
        argumentCounter = 2;
        this.func2 = func2;
    }
    //Test the previous node of the current node and the current node and the neighbor
    public void setFunc3(IFunc3<IAstarNode, IAstarNode, IAstarNode, Boolean> func3) {
        argumentCounter = 3;
        this.func3 = func3;
    }

    public IFunc1<IAstarNode, Boolean> getFunc1() {
        return func1;
    }

    public IFunc2<IAstarNode, IAstarNode, Boolean> getFunc2() {
        return func2;
    }

    public IFunc3<IAstarNode, IAstarNode, IAstarNode, Boolean> getFunc3() {
        return func3;
    }

    public int getArgumentCounter() {
        return argumentCounter;
    }
}
