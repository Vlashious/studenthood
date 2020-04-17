using System;

public class RandomCounter : ICounter
{
    static Random random = new Random();
    private int _value;
    public RandomCounter()
    {
        _value = random.Next(0, 1000000);
    }
    public int Value
    {
        get { return _value; }
    }
}