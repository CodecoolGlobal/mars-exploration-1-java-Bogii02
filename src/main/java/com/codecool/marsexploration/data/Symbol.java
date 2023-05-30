package com.codecool.marsexploration.data;

public enum Symbol {
    MOUNTAIN("^"),
    PIT("#"),
    MINERAL("*"),
    WATER("~");

    private String symbol;

    Symbol(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }
}
