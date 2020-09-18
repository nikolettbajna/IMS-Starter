package com.qa.ims.controller;

import java.util.List;

public interface UpdateController<T> {
	
	List<T> view();

	T add();
	
	double sum();

	int remove();
}
