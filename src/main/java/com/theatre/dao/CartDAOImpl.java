package com.theatre.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.theatre.mappers.TicketsMapper;
import com.theatre.models.CartDTO;
import com.theatre.models.TicketsDTO;

@Repository
public class CartDAOImpl implements CartDAO {
	private JdbcTemplate jdbcTemplate;

	public CartDAOImpl(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public boolean addToCart(String userName, TicketsDTO ticket) {
		List<Long> res = this.jdbcTemplate.query(
				"select cart_id from carts where account_id = (select id from accounts where username=?)",
				new Object[] { userName }, new RowMapper<Long>() {

					@Override
					public Long mapRow(ResultSet rs, int rowNum) throws SQLException {
						return rs.getLong("cart_id");
					}
				});
		long cartId = -1;
		int i = -1;
		// корзина у этого юзера еще не существует
		if (res == null || res.isEmpty()) {
			List<Long> accountIds = this.jdbcTemplate.query("select id from accounts where username=?",
					new Object[] { userName }, new RowMapper<Long>() {

						@Override
						public Long mapRow(ResultSet rs, int rowNum) throws SQLException {
							return rs.getLong("id");
						}
					});
			if (accountIds != null && !accountIds.isEmpty()) {

				long ids = accountIds.get(0).longValue();

				KeyHolder keyHolder = new GeneratedKeyHolder();
				jdbcTemplate.update(connection -> {
					PreparedStatement ps = connection.prepareStatement("insert into carts (account_id) values (?)");
					ps.setLong(1, ids);

					return ps;
				}, keyHolder);

				cartId = keyHolder.getKey().longValue();

			}

		} else {
			cartId = res.get(0).longValue();
		}
		if (cartId > 0) {
			i = this.jdbcTemplate.update(
					"insert into cart_items (cart_id,ticket_id,session_id,seat_id,price) values (?,?,?,?,?)",
					new Object[] { cartId, ticket.getId(), ticket.getSession().getId(), ticket.getSeats().getId(),
							ticket.getPrice() });

		}

		return i > 0;
	}

	@Override
	public CartDTO getCartForUser(String userName) {
		List<TicketsDTO> cartItems = this.jdbcTemplate.query("select * from accounts ac inner join carts c on ac.id = c.account_id"
				+ " inner join cart_items cit on c.cart_id = cit.cart_id inner join sessions ss on cit.session_id = ss.session_id "
				+ " inner join performances p on ss.performance_id = p.performance_id "
				+ " inner join seats s on cit.seat_id = s.seat_id "
				+ " inner join halls h on s.hall_id = h.hall_id where ac.username = ?", new Object[] {userName}, new TicketsMapper());
		CartDTO cart  =new CartDTO();
		cart.setUser(userName);
		cart.setCartItems(cartItems);
		return cart;
	}

	@Override
	public boolean removeTicketFromCart(long ticketId) {
		TicketsDTO ticket =  getTicketFromCart(ticketId);
		int i = -1;
		if(ticket!=null) {
			i = this.jdbcTemplate.update("delete from cart_items where ticket_id = ?", new Object[] {ticketId} );
		}
		return i>0;
	}

	@Override
	public TicketsDTO getTicketFromCart(long ticketId) {
		int i = -1;
		List<TicketsDTO> cartItems = this.jdbcTemplate.query("select * from accounts ac inner join carts c on ac.id = c.account_id"
				+ " inner join cart_items cit on c.cart_id = cit.cart_id inner join sessions ss on cit.session_id = ss.session_id "
				+ " inner join performances p on ss.performance_id = p.performance_id "
				+ " inner join seats s on cit.seat_id = s.seat_id "
				+ " inner join halls h on s.hall_id = h.hall_id where cit.ticket_id = ?", new Object[] {ticketId}, new TicketsMapper());
		TicketsDTO ticket =  cartItems==null || cartItems.isEmpty()?null : cartItems.get(0);
		ticket.setHallId(ticket.getHall().getId());		
		ticket.setRowNumber(ticket.getSeats().getRowNumber());
		ticket.setSeatNumber(ticket.getSeats().getSeatNumber());
		ticket.setSessionId(ticket.getSession().getId());
		ticket.setSeatId(ticket.getSeats().getId());
		return ticket;
	}

	@Override
	public boolean doesTicketExistsInCart(long seatId) {
		List<Long> ticketId = this.jdbcTemplate.query("select * from cart_items where seat_id = ?",
				new Object[] { seatId }, new RowMapper<Long>() {

					@Override
					public Long mapRow(ResultSet rs, int rowNum) throws SQLException {

						return rs.getLong("seat_id");
					}
				});
		return ticketId!=null&&ticketId.isEmpty();
	}

}
