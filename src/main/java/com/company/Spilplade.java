package com.company;

import java.util.ArrayList;
import java.util.List;

public class Spilplade {
    private static final int SIZE = 40;
    private List Felter = new ArrayList(SIZE);

    public Spilplade()
    {
        bygFelter();
        linkFelter();
    }

    public Felt getFelt(com.company.Felter start, int distance)
    {
        int endIndex = (start.getIndex() + distance) % SIZE;
        return (Felt) Felter.get(endIndex);
    }

    public Felt getStartFelt()
    {
        return (Felt) Felter.get(0);
    }

    private void bygFelt()
    {
        for (int i = 1; i <= SIZE; i++)
        {
            build(i);
        }
    }

    private void build(int i)
    {
        Felt s = new Felt("Felt " + i, i - 1);
        Felter.add(s);
    }

    private void linkFelter()
    {
        for (int i = 0; i < (SIZE - 1); i++)
        {
            link(i);
        }

        Felt first = (Felt) Felter.get(0);
        Felt last = (Felt) Felter.get(SIZE - 1);
        last.setNextFelt(first);
    }

    private void link(int i)
    {
        Felt current = (Felt) Felter.get(i);
        Felt next = (Felt) Felter.get(i + 1);
        current.setNextFelt(next);
    }
}
