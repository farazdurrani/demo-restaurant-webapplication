package CSR;



import java.util.List;

import CSR.ENTITY.Admin;
import CSR.ENTITY.Customer;
import CSR.ENTITY.Menu;
import CSR.ENTITY.MenuForShow;
import CSR.ENTITY.SingleOrder;

public interface OrderRepository
{
	List<MenuForShow> getAllMenusForShow();
	long saveUser(Customer customer);
	void removeSingleOrder(long id);
	Customer getUserFromDatabase(long id);
	List<Menu> getAllMenusOfThisPersonFromSaveSingleOrdersDatabase(long id);
	String getNameForThatDaysOrder(String today, long id);
	void saveSingleOrder(SingleOrder so);
	List<Menu> getAllMenusOfThisPerson(String userid);
	List<SingleOrder> getRecordOfThisPersonWhoIsNotLoggedInFrom(long id);
	/*
	 * THESE METHODS below belong to Administrator
	 */
	Admin AdminAuthentication(String username, String password);
	List<Customer> getOpenOrders();
	void setThisOrderClose(long id);
	List<SingleOrder> getOpenSingleOrders();
	void setThisSingleOrderClose(long id);
	List<SingleOrder> getClosedSingleOrders();
	List<Customer> getClosedOrders();
	Customer getCustomerFromDatabase(String userid, String password);
	String getNameForTodayOrder(long id);

	
	
	

}
