package com.ssk.Observable;

import org.junit.Test;

public class NumServableTest {
	
	@Test
	public void test(){
		NumObservable numOable = new NumObservable();
		NumObserver numObserver =  new NumObserver();
		numOable.addObserver(numObserver);//被观察者绑定观察者
		numObserver.addObserver(numOable);//被观察者绑定观察者
		numOable.setData(1);
		numOable.setData(2);
		numOable.setData(3);
	}
	
}
