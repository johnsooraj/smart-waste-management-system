package com.cleancity.util;

public enum BinConstants {

	BinMaxValue(100), BinMinValue(0);

	private int value;

	private BinConstants(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}

}
