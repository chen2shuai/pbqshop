package com.uuid.test;

import java.util.UUID;

import org.junit.Test;

public class UuidTest{

	@Test
	public void getUuid(){
		System.out.println(UUID.randomUUID().toString());
	}
}
