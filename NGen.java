// Jake Buchanan: 5450753
// Poojan Patel: 5453845

// NGen.java
// A *simplified* generic node class for use with the Stack1Gen class
// and other data structures as desired; uses generics for the data

// Credit: Received from CSCI 1933 U of MN Spring 2019

public class NGen <T> {

    // constructors

    public NGen () {}

    public NGen (T o, NGen <T> link) {
        data = o;
        next = link;
    }

    // selectors

    public T getData() {
        return data;
    }

    public void setData(T o) {
        data = o;
    }

    public NGen <T> getNext() {
        return next;
    }

    public void setNext(NGen <T> link) {
        next = link;
    }

    // instance variables

    private T data;
    private NGen <T> next;

}  // NGen class
