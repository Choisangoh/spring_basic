package com.ict.controller.di.classfile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Broadcast {

	// 방송은 무대에 의존
	@Autowired
	private Stage stage;
	
	public Broadcast(Stage stage) {
		this.stage = stage;
	}
	public void onAir() {
		System.out.print("방송 송출중인 ");
		this.stage.perform();
	}
}
