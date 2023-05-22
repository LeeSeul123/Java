package com.yedam.account;

import java.sql.Date;


import lombok.Data;
@Data

public class Account {
	private String accountId;
	private int accountBalance;
	private Date accountCredate;
	private String memberId;
}
