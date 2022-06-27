package com.supermarket.Exceptions;

public class NotEnoughChangeException extends Exception {
    public NotEnoughChangeException () {
        super("Cash register does not have enough change:(");
    }
}
