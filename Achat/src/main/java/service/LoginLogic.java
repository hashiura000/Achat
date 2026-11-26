package service;

import sql.LoginSql;

public class LoginLogic {
	
	public int LoginLogic (String name){
		
		LoginSql loginNameSelect = new LoginSql();
		int result = loginNameSelect.LoginJudgement(name);
		
		return result;
	}
	
	public void insertLogin (String name){
		
		LoginSql loginNameInsert = new LoginSql();
		loginNameInsert.insertLoginName(name);
		
	}
}
