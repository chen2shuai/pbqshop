package com.ssk.snake;
/**
 * @author chenershuai
 *
 */
public class GreedSnake {
	public static void main(String[] args) {
		SnakeModel model = new SnakeModel(20, 30);
		SnakeControl control = new SnakeControl(model);
		SnakeView view = new SnakeView(model, control);
		// 添加一个观察者，让view成为model的观察者
		model.addObserver(view);

		(new Thread(model)).start();
	}
}
