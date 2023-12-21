package business.order;

import api.ApiException;
import business.book.Book;
import business.book.BookDao;
import business.cart.ShoppingCart;
import business.customer.CustomerForm;

import java.time.DateTimeException;
import java.time.YearMonth;
import java.util.Date;

public class DefaultOrderService implements OrderService {

	private BookDao bookDao;

	public void setBookDao(BookDao bookDao) {
		this.bookDao = bookDao;
	}

	@Override
	public OrderDetails getOrderDetails(long orderId) {
		// NOTE: THIS METHOD PROVIDED NEXT PROJECT
		return null;
	}

	@Override
    public long placeOrder(CustomerForm customerForm, ShoppingCart cart) {

		validateCustomer(customerForm);
		validateCart(cart);

		// NOTE: MORE CODE PROVIDED NEXT PROJECT

		return -1;
	}


	private void validateCustomer(CustomerForm customerForm) {

    	String name = customerForm.getName();
		String address = customerForm.getAddress();
		String phone = customerForm.getPhone().replaceAll("[^0-9]", "").trim();
		String email = customerForm.getEmail();
		String ccNumber = customerForm.getCcNumber().replaceAll("[^0-9]", "").trim();

		if (name == null || name.equals("") || name.length() > 45 || name.length() < 4) {
			throw new ApiException.ValidationFailure("Invalid name field");
		}

		if (address == null || address.equals("") || address.length() > 45 || address.length() < 4) {
			throw new ApiException.ValidationFailure("Invalid address field");
		}
		if( phone == null || phone.equals("") || phone.length() != 10 ) {
			throw new ApiException.ValidationFailure("Invalid phone field");
		}
		if( email == null || email.equals("") || email.contains(" ") || !email.contains("@") || email.endsWith(".") ) {
			throw new ApiException.ValidationFailure("Invalid email field");
		}
		if( ccNumber == null || ccNumber.equals("") || ccNumber.length() > 16 || ccNumber.length() <14 ) {
			throw new ApiException.ValidationFailure("Invalid ccNumber field");
		}

		if (expiryDateIsInvalid(customerForm.getCcExpiryMonth(), customerForm.getCcExpiryYear())) {
			throw new ApiException.ValidationFailure("Invalid expiry date");
		}
	}

	private boolean expiryDateIsInvalid(String ccExpiryMonth, String ccExpiryYear) {
		YearMonth curr = YearMonth.now();
		YearMonth provided = YearMonth.of(Integer.parseInt(ccExpiryYear), Integer.parseInt(ccExpiryMonth));
		return provided.isBefore(curr);
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
