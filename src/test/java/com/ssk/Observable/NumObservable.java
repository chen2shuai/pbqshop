package com.ssk.Observable;

import java.util.Observable;
import java.util.Observer;
//被观察者
public class NumObservable extends Observable implements Observer{
	int data = 0;
	public void setData(int data){
		this.data = data;
		setChanged();//Marks this <tt>Observable</tt> object as having been changed;
		notifyObservers();//通知所有观察者
	}
	@Override
	public void update(Observable o, Object arg) {
		System.out.println("观察者和被观察者互相被观察！！");
	}
}
