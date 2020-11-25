package de.stvehb.loki.core.util;

import java.util.ArrayList;
import java.util.List;

public class Null {

	public static <T> List<T> orEmpty(List<T> list) {
		return list == null ? new ArrayList<>() : list;
	}

}
