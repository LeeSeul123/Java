package com.yedam.inter;

public interface RemoteControl extends Searchable {
	//0516 2번
	
	//상수 => static final 생략
	public int MAX_VOLUME = 10;
	public static final int MIN_VOLUME = 0;
	
	//추상 메소드 => abstract 생략
	public void turnOn();
	public abstract void turnOff();
	public void setVolume(int volume);
	
	//void search(String url) --> searchable상속해서 생김
}
