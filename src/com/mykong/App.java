package com.mykong;

import java.util.Date;

import org.hibernate.Session;

import com.mykong.stock.Stock;
import com.mykong.stock.StockDailyRecord;
import com.mykong.util.HibernateUtil;

public class App {
	public static void main(String[] args) {
		
        System.out.println("Hibernate one to many (Annotation)");
	Session session = HibernateUtil.getSessionFactory().openSession();

	session.beginTransaction();

	Stock stock = new Stock();
        stock.setStockCode("7053");
        stock.setStockName("PADINIS");
        session.save(stock);
        
        StockDailyRecord stockDailyRecords = new StockDailyRecord();
        stockDailyRecords.setPriceOpen(new Float("1.3"));
        stockDailyRecords.setPriceClose(new Float("1.1"));
        stockDailyRecords.setPriceChange(new Float("11.0"));
        stockDailyRecords.setVolume(3000000L);
        stockDailyRecords.setDate(new Date());
        
        stockDailyRecords.setStock(stock);        
        stock.getStockDailyRecords().add(stockDailyRecords);

        session.save(stockDailyRecords);

	session.getTransaction().commit();
	System.out.println("Done");
	}
}
