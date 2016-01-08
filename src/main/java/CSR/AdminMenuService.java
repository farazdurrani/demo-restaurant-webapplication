package CSR;

import java.util.List;

import CSR.ENTITY.Admin;
import CSR.ENTITY.Customer;
import CSR.ENTITY.SingleOrder;

public interface AdminMenuService {
	Admin AdminAuthentication(String username, String password);
	List<Customer> getOpenOrders();
	Customer getOpenOrder(int id);
	void setThisOrderClosed(long parameter);
	List<SingleOrder> getOpenSingleOrders();
	SingleOrder getOpenSingleOrder(int id);
	void setThisSingleOrderClosed(long parseLong);
	List<Customer> getClosedOrders();
	List<SingleOrder> getClosedSingleOrders();
	Customer getClosedOrder(int id);
	SingleOrder getClosedSingleOrder(int id);

	
}
