package com.meesig.util;

public class BundleState {

	public static final int CANCELLED = 0;
	public static final int ON_HOLD = 1;
	public static final int PENDING = 3;
	public static final int PROCESSING = 4;
	
	public static final int SHOP_ORDER = 5;
	public static final int SHIPPED = 6;
	public static final int RIDE = 7;
	public static final int COMPLETED = 8;
	
	public static final int REFUNDED = 9;
	
	
	public static String convert(int bundle_state) {
		switch (bundle_state) {
		case CANCELLED:
			return "취소";
		case ON_HOLD:
			return "주문대기";
		case PENDING:
			return "입금대기";
		case PROCESSING:
			return "주문처리";
		case SHOP_ORDER:
			return "조리중";
		case SHIPPED:
			return "배송중";
		case RIDE:
			return "배달중";
		case COMPLETED:
			return "완료";
		case REFUNDED:
			return "환불";
		default:
			return "상태없음";
		}
	}
}
