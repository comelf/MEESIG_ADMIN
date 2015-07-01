package com.meesig.model;

import lombok.Data;

import org.apache.ibatis.type.Alias;

@Data
@Alias("orderPayment")
public class OrderPayment {
	private long payment_id;
	private long orders_order_id;
	private String payment_tid;
	private String payment_oid;
	private String payment_respcode;
	String payment_respmsg;
	String payment_amount;
	String payment_paydate;
	String payment_paytype;
	String payment_excrowyn;
	String payment_financename;
	String payment_financecode;
	String payment_financeauthnum;
	String payment_cashreceiptunm;
	String payment_cashreceiptselfyn;
	String payment_casamount;
	
	public OrderPayment() {
		
	}
	
	
}
