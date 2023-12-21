package business.order;

import api.ApiException;
import business.BookstoreDbException;
import business.JdbcUtils;
import business.book.Book;
import business.book.BookDao;
import business.cart.ShoppingCart;
import business.cart.ShoppingCartItem;
import business.customer.Customer;
import business.customer.CustomerDao;
import business.customer.CustomerForm;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.DateTimeException;
import java.time.YearMonth;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class DefaultOrderService implements OrderService {

	private BookDao bookDao;

	private OrderDao orderDao;

	private LineItemDao lineItemDao;

	private CustomerDao customerDao;

	public void setBookDao(BookDao bookDao) {
		this.bookDao = bookDao;
	}

	public void setOrderDao(OrderDao orderDao) {
		this.orderDao = orderDao;
	}

	public void setLineItemDao(LineItemDao lineItemDao){
		this.lineItemDao = lineItemDao;
	}
	public void setCustomerDao(CustomerDao customerDao){
		this.customerDao = customerDao;
	}


	@Override
	public OrderDetails getOrderDetails(long orderId) {
		Order order = orderDao.findByOrderId(orderId);
		Customer customer = customerDao.findByCustomerId(order.customerId());
		List<LineItem> lineItems = lineItemDao.findByOrderId(orderId);
		List<Book> books = lineItems
				.stream()
				.map(lineItem -> bookDao.findByBookId(lineItem.bookId()))
				.toList();
		return new OrderDetails(order, customer, lineItems, books);
	}

	@Override
    public long placeOrder(CustomerForm customerForm, ShoppingCart cart) {

		validateCustomer(customerForm);
		validateCart(cart);

		// NOTE: MORE CODE PROVIDED NEXT PROJECT
		try (Connection connection = JdbcUtils.getConnection()) {
			Date ccExpDate = getCardExpirationDate(
					customerForm.getCcExpiryMonth(),
					customerForm.getCcExpiryYear());
			return performPlaceOrderTransaction(
					customerForm.getName(),
					customerForm.getAddress(),
					customerForm.getPhone(),
					customerForm.getEmail(),
					customerForm.getCcNumber(),
					ccExpDate, cart, connection);
		} catch (SQLException e) {
			throw new BookstoreDbException("Error during close connection for customer order", e);
		}
	}

	private Date getCardExpirationDate(String monthString, String yearString) {
		return new Date();
	}

	private long performPlaceOrderTransaction(
			String name, String address, String phone,
			String email, String ccNumber, Date date,
			ShoppingCart cart, Connection connection) {
		try {
			connection.setAutoCommit(false);
			long customerId = customerDao.create(
					connection, name, address, phone, email,
					ccNumber, date);
			long customerOrderId = orderDao.create(
					connection,
					cart.getComputedSubtotal() + cart.getSurcharge(),
					generateConfirmationNumber(), customerId);
			for (ShoppingCartItem item : cart.getItems()) {
				lineItemDao.create(connection, customerOrderId,
						item.getBookId(), item.getQuantity());
			}
			connection.commit();
			return customerOrderId;
		} catch (Exception e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				throw new BookstoreDbException("Failed to roll back transaction", e1);
			}
			return 0;
		}
	}

	private int generateConfirmationNumber() {
		return ThreadLocalRandom.current().nextInt(999999999);
	}

	private void validateCustomer(CustomerForm customerForm) {

    	String name = customerForm.getName();
		String address = customerForm.getAddress();
		String phone = customerForm.getPhone().replaceAll("[^0-9]", "").trim();
		String email = customerForm.getEmail();
		String ccNumber = customerForm.getCcNumber().replaceAll("[^0-9]", "").trim();
		String ccExpiryMonth = customerForm.getCcExpiryMonth();
		String ccExpiryYear = customerForm.getCcExpiryYear();

		if (name == null || name.equals("") || name.length() > 45 || name.length() < 4) {
			throw new ApiException.ValidationFailure("Invalid name field");
		}

		if (address == null || address.equals("") || address.length() > 45 || address.length() < 4) {
			throw new ApiException.ValidationFailure("Invalid address field");
		}
		if (phone == null || phone.equals("")) {
			throw new ApiException.ValidationFailure("Missing phone field");} else {
			String digits = phone.replaceAll("\\D", "");
			if (digits.length() != 10) {
				throw new ApiException.ValidationFailure("Invalid phone field");
			}
		}
		if( email == null || email.equals("") || email.contains(" ") || !email.contains("@") || email.endsWith(".") ) {
			throw new ApiException.ValidationFailure("Invalid email field");
		}
		if( ccNumber == null || ccNumber.equals("") || ccNumber.length() > 16 || ccNumber.length() <14 ) {
			throw new ApiException.ValidationFailure("Invalid ccNumber field");
		}
		if( ccExpiryMonth == null || ccExpiryMonth.equals("")){
			throw new ApiException.ValidationFailure("Invalid ccExpiryMonth field");
		}
		if( ccExpiryYear == null || ccExpiryYear.equals("")){
			throw new ApiException.ValidationFailure("Invalid ccExpiryYear field");
		}
		if (expiryDateIsInvalid(ccExpiryMonth, ccExpiryYear)) {
			throw new ApiException.ValidationFailure("Invalid expiry date");
		}
	}

	private boolean expiryDateIsInvalid(String ccExpiryMonth, String ccExpiryYear) {
		try {
			int month = Integer.parseInt(ccExpiryMonth);
			int year = Integer.parseInt(ccExpiryYear);
			YearMonth ccExpiryDate = YearMonth.of(year, month);
			return YearMonth.now().isAfter(ccExpiryDate);
		} catch (NumberFormatException | NullPointerException | DateTimeException ex) {
			return true;
		}
	}

	private void validateCart(ShoppingCart cart) {

		if (cart.getItems().size() <= 0) {
			throw new ApiException.ValidationFailure("Cart is empty.");
		}

		cart.getItems().forEach(item-> {
			if (item.getQuantity() < 1 || item.getQuantity() > 99) {
				throw new ApiException.ValidationFailure("Invalid quantity");
			}
			Book databaseBook = bookDao.findByBookId(item.getBookId());

			if (databaseBook.price() != item.getBookForm().getPrice()) {
				throw new ApiException.ValidationFailure("Invalid book price");
			} else if (databaseBook.categoryId() != item.getBookForm().getCategoryId()) {
				throw new ApiException.ValidationFailure("Invalid book category");
			}
		});
	}

}
