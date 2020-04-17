public class CounterService
{
    protected internal ICounter Counter { get; }
    public CounterService(ICounter counter)
    {
        Counter = counter;
    }
}