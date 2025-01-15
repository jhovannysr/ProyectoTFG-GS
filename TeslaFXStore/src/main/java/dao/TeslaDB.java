package dao;

import model.*;
import dao.*;
import util.*;

public class TeslaDB {
	
	private CrudCustomer crud;
	
	public TeslaDB(CrudCustomer crud)
	{
		this.crud = crud;
	}
	
	/**
	 * Consulta Cliente
	 * @param email
	 * @return
	 */
//	public Customer consulta(String email) {
//            System.err.println("consulta");
//		Customer c = crud.readCustomer(email );
//		return c == null ? null : c;
//	}
//	
//	/**
//	 * Consulta Vehicle
//	 * @param email
//	 * @return
//	 */
//	public Stock consultaStock(String model) {
//		Stock s = crud.readStock(model);
//		return s == null ? null : s;
//	}
//	
}
