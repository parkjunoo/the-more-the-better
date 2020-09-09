package io.playdata.themorethebetter.exception;

public class StoreNotFoundException extends RuntimeException {
	public StoreNotFoundException(Long no) {
		super("해당 스토어의 정보를 찾을 수 없습니다.");
	}
}