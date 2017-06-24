package camelinaction;

public abstract class AbstractObserver {
	protected Broker broker;
	public abstract void update();
}
