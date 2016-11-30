package com.ssk.Observable;

import java.util.Observable;
import java.util.Observer;
//观察者
public class NumObserver extends Observable implements Observer {

	@Override
	public void update(Observable o, Object arg) {//有被观察者发生改变，自动调用对应观察者的的update方法
		NumObservable numObservable = (NumObservable) o;
		System.out.println("被观察者Numobservable已改变,data:" + numObservable.data);
		
		setChanged();
		notifyObservers();
	}

}
