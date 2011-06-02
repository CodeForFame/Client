package org.moparscape.client.util;

public class Util {
	/**
	 * returns a random number within the given bounds
	 */
	public static int random(int low, int high) {
		return DataConversions.random(low, high);
	}
	public final static int[] IRON = {6,5,7,8,2,3,9,28, 1075,1, 71, 83, 77, 12, 1258, 89, 0, 670, 1063}; 
	public final static int[] RUNE = {112, 399, 400, 401, 404, 403, 402, 396, 1080, 397, 75, 398, 81, 405, 1262, 93, 98, 674, 1067};
	public final static int[] ADDY = {111, 107, 116, 120, 131, 127, 123, 65, 1079, 69, 74, 86, 80, 204, 1261, 92, 97, 673, 1066};
	public final static int[] MITH = {110, 106, 115, 119, 130, 126, 122, 64, 1078, 68, 73, 85, 79, 203, 1260, 91, 96, 672, 1065};
	public final static int[] STEEL = {109, 105, 114, 118, 129, 125, 121, 63, 1077, 67, 72, 84, 78, 88, 1259, 90, 95, 671, 1064};
	public final static int[] BRONZE = {108, 104, 113, 117, 128, 124, 206, 62, 1076, 66, 70, 82, 76, 87, 156, 87, 205, 669, 1062};

}
